package fapacapi.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import fapacapi.config.FileStorageProperties;
import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
@RequestMapping("/fapacapi/files")
public class FileStorageController {

    private final Path fileStorageLocation;

    public FileStorageController(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation); // Garante que a pasta seja criada se não existir
        } catch (Exception ex) {
            throw new RuntimeException("Não foi possível criar o diretório de upload.", ex);
        }
    }

    // Upload de arquivos
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        // Garante que o nome original do arquivo seja limpo
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
    
        // Extrai a extensão do arquivo original
        String fileExtension = "";
        if (originalFileName.lastIndexOf('.') > 0) {
            fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.'));
        }
    
        // Gera um novo nome para o arquivo usando UUID e adiciona a extensão
        String newFileName = UUID.randomUUID().toString() + fileExtension; 
    
        try {
            // Salva o arquivo no diretório de upload com o novo nome
            Path targetLocation = fileStorageLocation.resolve(newFileName);
            file.transferTo(targetLocation);
    
            // Gera a URL de download da imagem
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/fapacapi/files/view/")
                .path(newFileName) // Usa apenas o novo nome
                .toUriString();
    
            return ResponseEntity.ok("Upload Completo! Link da imagem: " + fileDownloadUri);
    
        } catch (IOException ex) {
            return ResponseEntity.badRequest().body("Falha no upload: " + ex.getMessage());
        }
    }        

    // Exibe a imagem no navegador
    @GetMapping("/view/{fileName:.+}")
    public ResponseEntity<Resource> viewFile(@PathVariable String fileName, HttpServletRequest request) {
        try {
            // Resolve o caminho do arquivo
            Path filePath = fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }

            // Tenta determinar o tipo de conteúdo do arquivo
            String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

            if (contentType == null) {
                contentType = "image/jpeg/pdf"; // Defina o tipo de conteúdo padrão
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);

        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    // Lista todos os arquivos no diretório de uploads
    @GetMapping("/list")
    public ResponseEntity<List<String>> listFiles() {
        try {
            List<String> fileNames = Files.list(this.fileStorageLocation)
                .map(Path::getFileName)
                .map(Path::toString)
                .collect(Collectors.toList());

            return ResponseEntity.ok(fileNames);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}