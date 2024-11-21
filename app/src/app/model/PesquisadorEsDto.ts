import { PesquisadorConhecimento } from "./PesquisadorConhecimento";
import { VinculoInstitucional } from "./VinculoInstitucional";

export interface PesquisadorEsDto {
    idPesquisador?: number;
    nomeCompletoPesquisador: string;
    fotoPesquisador?: string; // Opcional
    emailPesquisador: string;
    sexoPesquisador: string;
    dataNascimentoPesquisador: string; // Usando string para datas
    corRaca?: string; // Opcional
    nomeMaePesquisador: string;
    nomePaiPesquisador?: string; // Opcional
    curriculoLattesPesquisador: string;
    nivelAcademicoPesquisador: string;
    senhaPesquisador: string;
    rgPesquisador: string;
    passaportePesquisador?: string; // Opcional
    passaporteFilePesquisador?: string; // Opcional
    telefonePesquisador: string;
    idTipoPesquisador: number;
    ativo?: boolean; // Opcional

    // Endereço Residencial
    idEnderecoResidencial?: number;
    tipoEnderecoResidencial: string;
    cepEnderecoResidencial: string;
    logradouroEnderecoResidencial: string;
    numeroEnderecoResidencial: string;
    bairroEnderecoResidencial: string;
    paisEnderecoResidencial: string;
    estadoEnderecoResidencial: string;
    municipioEnderecoPesquisador: string;
    telefoneEnderecoPesquisador: string;

    // Endereço Profissional
    idEnderecoProfissional?: number; // Opcional
    tipoEnderecoProfissional?: string; // Opcional
    cepEnderecoProfissional?: string; // Opcional
    logradouroEnderecoProfissional?: string; // Opcional
    numeroEnderecoProfissional?: string; // Opcional
    bairroEnderecoProfissional?: string; // Opcional
    paisEnderecoProfissional?: string; // Opcional
    estadoEnderecoProfissional?: string; // Opcional
    municipioEnderecoProfissional?: string; // Opcional
    telefoneEnderecoProfissional?: string; // Opcional

    // Vínculos Institucionais
    vinculosInstitucionais?: VinculoInstitucional[];

    // Conhecimentos do Pesquisador
    pesquisadorConhecimento: PesquisadorConhecimento[];
}
