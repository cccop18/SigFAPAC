import { TipoPesquisador } from './TipoPesquisador';  

export interface PesquisadorDto {
    idPesquisador: number;
    nomeCompletoPesquisador: string;
    fotoPesquisador: string;
    emailPesquisador: string;
    sexoPesquisador: string;
    dataNascimentoPesquisador: string;  
    corRaca: string;
    nomeMaePesquisador: string;
    nomePaiPesquisador: string;
    curriculoLattesPesquisador: string;
    nivelAcademicoPesquisador: string;
    areaConhecimentoumPesquisador: string;
    areaConhecimentodoisPesquisador: string;
    areaConhecimentotresPesquisador: string;
    cpfPesquisador: string;
    cpfFilePesquisador: string;
    senhaPesquisador: string;
    rgPesquisador: string;
    rgFilePesquisador: string;
    orgaoEmissorPesquisador: string;
    ufPesquisador: string;
    dataEmissaoPesquisador: string;
    passaportePesquisador: string;
    passaporteFilePesquisador: string;
    telefonePesquisador: string;
    tipoPesquisador: TipoPesquisador;

    // Endereço Residencial
    idEnderecoPesquisador: number;
    tipoEnderecoPesquisador: string;
    cepEnderecoPesquisador: string;
    logradouroEnderecoPesquisador: string;
    numeroEnderecoPesquisador: string;
    bairroEnderecoPesquisador: string;
    paisEnderecoPesquisador: string;
    estadoEnderecoPesquisador: string;
    municipioEnderecoPesquisador: string;
    telefoneEnderecoPesquisador: string;

    // Vínculo Institucional
    idvinculoInstitucional: number;
    vinculoInstitucional: string;
    vinculoEmpregaticio: boolean;
    tempoServico: string;
    funcaoCargo: string;
    tempoFuncao: string;

    // Endereço Profissional
    idunidade: number;
    cepEnderecoUnidade: string;
    logradouroEnderecoUnidade: string;
    numeroEnderecoUnidade: string;
    bairroEnderecoUnidade: string;
    estadoEnderecoUnidade: string;
    paisEnderecoUnidade: string;
}
