package fapacapi.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fapacapi.model.Arquivo;
import fapacapi.service.ArquivoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/arquivo", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Arquivo", description = "Endpoints para gerenciar os Arquivos")
public class ArquivoController implements IController<Arquivo> {

    // Variáveis de instância do caminho da imagem
    private static final String caminhoFile = "uploads/arquivos";

    private final ArquivoService servico;

    public ArquivoController(ArquivoService servico) {
        this.servico = servico;
    }

    @Override
    @GetMapping("/")
    @Operation(summary = "Obtém todos os arquivos ou filtra por termo de busca", description = "Obtém uma lista paginada de todos os arquivos cadastrados ou que contenham o termo de busca informado")
    public ResponseEntity<Page<Arquivo>> get(
            @RequestParam(required = false) String termoBusca,
            @RequestParam(required = false, defaultValue = "false") boolean unpaged,
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "nomeArquivo", direction = Sort.Direction.ASC)
            }) @ParameterObject Pageable page) {
        Page<Arquivo> registros = servico.get(termoBusca, page);
        return ResponseEntity.ok(registros);
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Arquivo encontrado"),
            @ApiResponse(responseCode = "404", description = "Arquivo não encontrado", content = @Content(examples = @ExampleObject("")))
    })
    @Operation(summary = "Obtém o arquivo por ID", description = "Obtém o arquivo com o ID informado")
    public ResponseEntity<Arquivo> get(@PathVariable("id") Long id) {
        Arquivo registro = servico.get(id);
        if (registro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        try {
            Path caminho = Paths.get(caminhoFile).resolve(filename).normalize();
            Resource resource = new UrlResource(caminho.toUri());
            if (resource.exists()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/view/{filename}")
    public ResponseEntity<Resource> viewFile(@PathVariable String filename) {
        try {
            // Caminho do diretório onde os arquivos são armazenados
            Path caminho = Paths.get(caminhoFile).resolve(filename).normalize();
            Resource resource = new UrlResource(caminho.toUri());

            // Verifica se o arquivo existe e é legível
            if (resource.exists() && resource.isReadable()) {
                // Define o tipo de mídia com base no tipo do arquivo
                String contentType = Files.probeContentType(caminho);

                // Retorna o arquivo para visualização inline (exibição no navegador)
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Transactional
    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Arquivo> insert(
            @RequestPart("objeto") Arquivo objeto,
            @RequestPart("file") MultipartFile file) {
        Arquivo registro = servico.save(objeto);
        try {
            if (!file.isEmpty()) {
                // Verifica se o diretório existe e, se não existir, cria
                Path diretorio = Paths.get(caminhoFile);
                if (!Files.exists(diretorio)) {
                    Files.createDirectories(diretorio);
                }

                // Cria o caminho completo para o arquivo
                Path caminho = Paths.get(caminhoFile,
                        String.valueOf(registro.getIdArquivo()) + " " + file.getOriginalFilename());

                // Salva o arquivo na localização especificada
                Files.write(caminho, file.getBytes());

                // Define o nome do arquivo salvo no objeto Arquivo
                registro.setFileArquivo(String.valueOf(registro.getIdArquivo()) + " " + file.getOriginalFilename());

                // Salvar novamente o objeto com o nome do arquivo atualizado
                registro = servico.save(registro);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @Override
    @PutMapping("/")
    @Operation(summary = "Atualizar um Banco", description = "Atualiza um Banco")
    public ResponseEntity<Arquivo> update(@RequestBody Arquivo objeto) {
        Arquivo registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar arquivo", description = "Deleta o arquivo com o ID informado")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @Override
    public ResponseEntity<?> insert(Arquivo objeto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

}
