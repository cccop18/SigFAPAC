package fapacapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fapacapi.controller.dto.EditalDadosDto;
import fapacapi.controller.mapper.EditalDadosMapper;
import fapacapi.model.Edital;
import fapacapi.service.EditalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/editalDados", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "EditalDados", description = "Endpoints para cadastro dos dados do Edital")
public class EditalDadosController {

    private static final String caminhoFile = "uploads/editais";

    private final EditalService servico;
    private final EditalDadosMapper mapper;

    public EditalDadosController(EditalService servico, EditalDadosMapper mapper) {
        this.servico = servico;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados do edital não encontrado"),
            @ApiResponse(responseCode = "404", description = "Dados do edital não encontrado", content = @Content(examples = @ExampleObject("")))
    })
    @Operation(summary = "Obeter um editalDados por ID", description = "Obtém um editalDados com o ID infromado")
    public ResponseEntity<EditalDadosDto> get(@PathVariable("id") Long id) {
        Edital registro = servico.get(id);
        if (registro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        EditalDadosDto dto = mapper.toDto(registro);
        return ResponseEntity.ok(dto);
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

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Cadastrar dos dados do edital", description = "Cadastra os dados de um novo edital")
    public ResponseEntity<EditalDadosDto> insertDados(@RequestPart @Valid EditalDadosDto objeto,
            @RequestPart("file") MultipartFile file) {
        Edital objetoConvertido = mapper.toEntity(objeto);
        Edital registro = servico.save(objetoConvertido);
        try {
            if (!file.isEmpty()) {
                // Verifica se o diretório existe e, se não existir, cria
                Path diretorio = Paths.get(caminhoFile);
                if (!Files.exists(diretorio)) {
                    Files.createDirectories(diretorio);
                }

                // Cria o caminho completo para o arquivo
                Path caminho = Paths.get(caminhoFile,
                        String.valueOf(registro.getIdEdital()) + "" + file.getOriginalFilename());
                

                // Salva o arquivo na localização especificada
                Files.write(caminho, file.getBytes());

                // Define o nome do arquivo salvo no objeto Arquivo
                registro.setFileEdital(String.valueOf(registro.getIdEdital()) + " " + file.getOriginalFilename());

                // Salvar novamente o objeto com o nome do arquivo atualizado
                registro = servico.save(registro);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        EditalDadosDto dto = mapper.toDto(registro);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Atualiza os dados do edital", 
                description = "Atualiza os dados de um edital existente")
    public ResponseEntity<EditalDadosDto> update(@RequestPart @Valid EditalDadosDto objeto,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        // Chama o serviço para atualizar os dados e processar o arquivo, se fornecido
        Edital registroAtualizado = servico.updateDados(objeto, file);

        // Converte a entidade atualizada para DTO e retorna a resposta
        EditalDadosDto dto = mapper.toDto(registroAtualizado);
        return ResponseEntity.ok(dto);
    }
}
