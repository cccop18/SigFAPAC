export interface PesquisadorEsDto {
    idPesquisador?: number;  // Campo opcional
    idTipoPesquisador: number;
    nomeCompletoPesquisador: string;
    emailPesquisador: string;
    sexoPesquisador: string;
    dataNascimentoPesquisador: string;  // LocalDate -> string (ISO 8601 format)
    corRaca?: string;  // Campo opcional
    nomeMaePesquisador: string;
    nomePaiPesquisador?: string;  // Campo opcional
    curriculoLattesPesquisador: string;
    nivelAcademicoPesquisador: string;
    passaportePesquisador?: string;  // Campo opcional
    passaporteFilePesquisador?: string;  // Campo opcional
    rgPesquisador: string;
    rgFilePesquisador: string;
    senhaPesquisador: string;

    // Endereço Residencial
    idEnderecoResidencial: number;
    tipoEnderecoResidencial: string;
    telefoneEnderecoResidencial: string;
    cepEnderecoResidencial: string;
    logradouroEnderecoResidencial: string;
    numeroEnderecoResidencial: string;
    bairroEnderecoResidencial: string;
    estadoEnderecoResidencial: string;
    paisEnderecoResidencial: string;

    // Endereço Profissional
    idEnderecoProfissional: number;
    tipoEnderecoProfissional?: string;  // Campo opcional
    telefoneEnderecoProfissional?: string;  // Campo opcional
    cepEnderecoProfissional?: string;  // Campo opcional
    logradouroEnderecoProfissional?: string;  // Campo opcional
    numeroEnderecoProfissional?: string;  // Campo opcional
    bairroEnderecoProfissional?: string;  // Campo opcional
    estadoEnderecoProfissional?: string;  // Campo opcional
    paisEnderecoProfissional?: string;  // Campo opcional

    // Vínculo Institucional
    idVinculoInstitucional: number;
    vinculoInstitucional: string;
    vinculoEmpregaticio: boolean;
    tempoServico?: string;  // Campo opcional
    regimeTrabalho?: string;  // Campo opcional
    funcaoCargo?: string;  // Campo opcional
    tempoDaFuncao?: string;  // Campo opcional
    idIntituicao: number;

    // Áreas do Conhecimento
    areaConhecimento: number;
    primeiraSubArea: number;
    segundaSubArea?: number;  // Campo opcional
    terceiraSubArea?: number;  // Campo opcional
}
