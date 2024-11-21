package fapacapi.controller.dto;

import java.time.LocalDate;
import java.util.List;

import fapacapi.model.PesquisadorConhecimento;
import fapacapi.model.VinculoInstitucional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PesquisadorDto(
        @NotNull Long idPesquisador,
        @NotBlank String nomeCompletoPesquisador,
        @NotBlank String fotoPesquisador,
        @NotBlank String emailPesquisador,
        @NotBlank String sexoPesquisador,
        @NotNull LocalDate dataNascimentoPesquisador,
        @NotBlank String corRaca,
        @NotBlank String nomeMaePesquisador,
        @NotBlank String nomePaiPesquisador,
        @NotBlank String curriculoLattesPesquisador,
        @NotBlank String nivelAcademicoPesquisador,
        @NotBlank String areaConhecimentoumPesquisador,
        @NotBlank String areaConhecimentodoisPesquisador,
        @NotBlank String areaConhecimentotresPesquisador,
        @NotBlank String cpfPesquisador,
        @NotBlank String cpfFilePesquisador,
        @NotBlank String senhaPesquisador,
        @NotBlank String rgPesquisador,
        @NotBlank String rgFilePesquisador,
        @NotBlank String orgaoEmissorPesquisador,
        @NotBlank String ufPesquisador,
        @NotBlank String dataEmissaoPesquisador,
        @NotBlank String passaportePesquisador,
        @NotBlank String passaporteFilePesquisador,
        @NotBlank String telefonePesquisador,
        @NotNull Long idTipoPesquisador,

        // Endereço Residencial
        @NotNull Long idEnderecoResidencial, 
        @NotBlank String tipoEnderecoResidencial,
        @NotBlank String cepEnderecoResidencial,
        @NotBlank String logradouroEnderecoResidencial,
        @NotBlank String numeroEnderecoResidencial,
        @NotBlank String bairroEnderecoResidencial,
        @NotBlank String paisEnderecoResidencial,
        @NotBlank String estadoEnderecoResidencial,
        @NotBlank String municipioEnderecoPesquisador,
        @NotBlank String telefoneEnderecoPesquisador,
        
        // vinculo institucional
        // @NotNull Long idvinculoInstitucional,
        // @NotBlank String vinculoInstitucional,
        // @NotNull Boolean vinculoEmpregaticio,
        // @NotBlank String tempoServico,
        // @NotBlank String funcaoCargo,
        // @NotBlank String tempoFuncao,
        
        //ENDEREÇO PROFISSIONAL
        // @NotNull Long idUnidade,
        // @NotBlank String cepEnderecoUnidade,
        // @NotBlank String logradouroEnderecoUnidade,
        // @NotBlank String numeroEnderecoUnidade,
        // @NotBlank String bairroEnderecoUnidade,
        // @NotBlank String estadoEnderecoUnidade,
        // @NotBlank String paisEnderecoUnidade

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
        @NotNull List<VinculoInstitucional> vinculosInstitucionais,

        // PesquisadorConhecimento
        @NotNull List<PesquisadorConhecimento> pesquisadorConhecimento

) {

}
