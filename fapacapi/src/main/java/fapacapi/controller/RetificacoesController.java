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
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.data.domain.Sort;

import fapacapi.model.Retificacoes;
import fapacapi.service.RetificacoesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/retificacao", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
    name = "retificacao",
    description = "Endipoinst para gerenciar as Retificacoes"
)
public class RetificacoesController implements IController<Retificacoes>{

    private static final String caminhoFile = "uploads/Retificacoes";
    private final RetificacoesService servico;

    public RetificacoesController(RetificacoesService servico){
        this.servico = servico;
    }

    @Override
    @GetMapping("/")
    @Operation(
        summary = "Obtém todas as retificacoes ou filtrado por termo busca",
        description = "Obtém uma lista paginada de todas as retificacoes cadastradas ou que tenham o termo de busca informado"
    )
    public ResponseEntity<Page<Retificacoes>> get(
        @RequestParam(required = false) String termoBusca, 
        @RequestParam(required = false) boolean unpaged, 
        @SortDefault.SortDefaults({
            @SortDefault(sort = "nomeRetificacoes", direction = Sort.Direction.ASC)
        }) 
        @ParameterObject Pageable page) {
        Page<Retificacoes> registros = servico.get(termoBusca, page);
        return ResponseEntity.ok(registros);
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retificacao encontrada"),
        @ApiResponse(
            responseCode = "404",
            description = "Retificacao não encontrada",
            content = @Content(
                examples = @ExampleObject("")
            )
        )
    })
    @Operation(
        summary = "Obtém a retificacao por ID",
        description = "Obtém a retificacao com o ID informado"
    )
    public ResponseEntity<Retificacoes> get(@PathVariable("id") Long id) {
        Retificacoes registro = servico.get(id);
        if(registro == null) {
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
    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
        summary = "Cadastrar uma nova retificacao",
        description = "Cadastra uma nova retificacao"
    )
    public ResponseEntity<Retificacoes> insert(@RequestPart @Valid Retificacoes objeto,
        @RequestPart("file") MultipartFile file) {
            
        Retificacoes registro = servico.save(objeto);
        // Verifica se o arquivo foi enviado
        try {
            if (!file.isEmpty()) {
                // Verifica se o diretório existe e, se não existir, cria
                Path diretorio = Paths.get(caminhoFile);
                if (!Files.exists(diretorio)) {
                    Files.createDirectories(diretorio);
                }

                // Cria o caminho completo para o arquivo
                Path caminho = Paths.get(caminhoFile,
                        String.valueOf(registro.getIdRetificacoes()) + " " + file.getOriginalFilename());

                // Salva o arquivo na localização especificada
                Files.write(caminho, file.getBytes());

                // Define o nome do arquivo salvo no objeto Arquivo
                registro.setFileRetificacoes(String.valueOf(registro.getIdRetificacoes()) + " " + file.getOriginalFilename());

                // Salvar novamente o objeto com o nome do arquivo atualizado
                registro = servico.save(registro);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @PutMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
        summary = "Atualizar uma retificacao",
        description = "Atualiza uma Retificacao"
    )
    public ResponseEntity<Retificacoes> update(@PathVariable @Valid Retificacoes objeto,
    @RequestPart(value = "file", required = false) MultipartFile file) {
        
        Retificacoes registro = servico.save(objeto);
        try {
            if (file != null && !file.isEmpty()) {
                // Verifica se o diretório existe e, se não existir, cria
                Path diretorio = Paths.get(caminhoFile);
                if (!Files.exists(diretorio)) {
                    Files.createDirectories(diretorio);
                }

                // Cria o caminho completo para o arquivo
                Path caminho = Paths.get(caminhoFile,
                        String.valueOf(registro.getIdRetificacoes()) + " " + file.getOriginalFilename());

                // Salva o arquivo na localização especificada
                Files.write(caminho, file.getBytes());

                // Define o nome do arquivo salvo no objeto Arquivo
                registro.setFileRetificacoes(String.valueOf(registro.getIdRetificacoes()) + " " + file.getOriginalFilename());

                // Salvar novamente o objeto com o nome do arquivo atualizado
                registro = servico.save(registro);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar uma retificacao",
        description = "Deleta a retificacao com  Id informado"
    )
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @Override
    public ResponseEntity<?> insert(Retificacoes objeto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public ResponseEntity<?> update(Retificacoes objeto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    
}
