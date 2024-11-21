import { PesquisadorConhecimento } from "./PesquisadorConhecimento";
import { VinculoInstitucional } from "./VinculoInstitucional";

export type PesquisadorBrasileiroDto = {
    idPesquisador: number;
    nomeCompletoPesquisador: string;
    
    cpfPesquisador: string;
    rgPesquisador: string;
    orgaoEmissorPesquisador: string;
    ufPesquisador: string;
    dataEmissaoPesquisador: string;
    fotoPesquisador?: string;
    emailPesquisador: string;
    sexoPesquisador: string;
    dataNascimentoPesquisador: string;
    corRaca?: string;
    nomeMaePesquisador: string;
    nomePaiPesquisador?: string;
    curriculoLattesPesquisador: string;
    nivelAcademicoPesquisador: string;
    senhaPesquisador: string;
    telefonePesquisador: string;
    idTipoPesquisador: number;
    ativo?: boolean;
  
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
    idEnderecoProfissional?: number;
    tipoEnderecoProfissional?: string;
    cepEnderecoProfissional?: string;
    logradouroEnderecoProfissional?: string;
    numeroEnderecoProfissional?: string;
    bairroEnderecoProfissional?: string;
    paisEnderecoProfissional?: string;
    estadoEnderecoProfissional?: string;
    municipioEnderecoProfissional?: string;
    telefoneEnderecoProfissional?: string;
  
    // Vínculos Institucionais
    vinculosInstitucionais: VinculoInstitucional[];
  
    // Conhecimentos do Pesquisador
    pesquisadorConhecimento: PesquisadorConhecimento[];
  };
  