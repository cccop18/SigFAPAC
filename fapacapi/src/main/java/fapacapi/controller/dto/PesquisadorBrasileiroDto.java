package fapacapi.controller.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PesquisadorBrasileiroDto(
    Long idPesquisador,
    @NotBlank String nomeCompletoPesquisador,

    //Novos campos
    @NotBlank String cpfPesquisador,
    @NotBlank String rgPesquisador,
    @NotBlank String orgaoEmissorPesquisador,
    @NotBlank String ufPesquisador,
    @NotBlank String dataEmissaoPesquisador,


    
    String fotoPesquisador,
    @Email String emailPesquisador,
    @NotBlank String sexoPesquisador,
    @NotNull String dataNascimentoPesquisador,
    String corRaca,
    @NotBlank String nomeMaePesquisador,
    String nomePaiPesquisador,
    @NotBlank String curriculoLattesPesquisador,
    @NotBlank String nivelAcademicoPesquisador,
    @NotBlank String senhaPesquisador,
    @NotNull String telefonePesquisador,
    @NotNull Long idTipoPesquisador,
    Boolean ativo, // Usando o ID do TipoPesquisador
    
    // Endereço Residencial
    Long idEnderecoResidencial, 
    @NotBlank String tipoEnderecoResidencial,
    @NotBlank String cepEnderecoResidencial,
    @NotBlank String logradouroEnderecoResidencial,
    @NotBlank String numeroEnderecoResidencial,
    @NotBlank String bairroEnderecoResidencial,
    @NotBlank String paisEnderecoResidencial,
    @NotBlank String estadoEnderecoResidencial,
    @NotBlank String municipioEnderecoPesquisador,
    @NotBlank String telefoneEnderecoPesquisador,

    //ENDEREÇO Profissional
    Long idEnderecoProfissional, 
    String tipoEnderecoProfissional,
    String cepEnderecoProfissional,
    String logradouroEnderecoProfissional,
    String numeroEnderecoProfissional,
    String bairroEnderecoProfissional,
    String paisEnderecoProfissional,
    String estadoEnderecoProfissional,
    String municipioEnderecoProfissional,
    String telefoneEnderecoProfissional,

    // Vinculo Institucional
    // Lista de Vínculos Institucionais
    List<VinculoInstitucionalDto> vinculosInstitucionais,

    // PesquisadorConhecimento
    List<PesquisadorConhecimentoDto> pesquisadorConhecimento
) {

}
