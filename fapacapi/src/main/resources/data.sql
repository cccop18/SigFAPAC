-- Inserir áreas de conhecimento
SET SESSION FOREIGN_KEY_CHECKS=0;

INSERT INTO area_conhecimento (id_area_conhecimento, nome_area_conhecimento) VALUES 
(1, 'Ciências Exatas'),
(2, 'Ciências Humanas'),
(3, 'Engenharia'),
(4, 'Ciências Biológicas'),
(5, 'Ciências da Saúde'),
(6, 'Ciências Agrárias'),
(7, 'Ciências Sociais Aplicadas');




-- Inserir primeira subárea
INSERT INTO primeira_sub_area (id_prieira_sub_area, nome_primeira_sub, area_conhecimento_id) VALUES
(1, 'Matemática', 1),
(2, 'Filosofia', 2),
(3, 'Engenharia Civil', 3),
(4, 'Biologia Geral', 4),
(5, 'Medicina', 5),
(6, 'Agronomia', 6),
(7, 'Administração', 7),
(8, 'Economia', 7);

INSERT INTO primeira_sub_area (id, nome_primeira_sub, area_conhecimento_id_area_conhecimento) VALUES
(1, 'Matemática', 1),
(2, 'Filosofia', 2),
(3, 'Engenharia Civil', 3),
(4, 'Biologia Geral', 4),
(5, 'Medicina', 5),
(6, 'Agronomia', 6),
(7, 'Administração', 7),
(8, 'Economia', 7);
-- ddddd





-- Inserir segunda subárea
INSERT INTO segunda_sub_area (id_segunda_sub_area, nome_segunda_sub, primeira_sub_area_id_primeira_sub_area) VALUES
(1, 'Álgebra', 1),
(2, 'Lógica', 2),
(3, 'Hidráulica', 3),
(4, 'Genética', 4),
(5, 'Cirurgia Geral', 5),
(6, 'Fitotecnia', 6),
(7, 'Gestão de Pessoas', 7),
(8, 'Macroeconomia', 8);

INSERT INTO segunda_sub_area (id_segunda_sub_area, nome_segunda_sub, primeira_sub_area_id) VALUES
(1, 'Álgebra', 1),
(2, 'Lógica', 2),
(3, 'Hidráulica', 3),
(4, 'Genética', 4),
(5, 'Cirurgia Geral', 5),
(6, 'Fitotecnia', 6),
(7, 'Gestão de Pessoas', 7),
(8, 'Macroeconomia', 8);






-- Inserir terceira subárea
INSERT INTO terceira_sub_area (id_terceira_sub_area, nome_terceira_sub, segunda_sub_area_id) VALUES
(1, 'Álgebra Linear', 1),
(2, 'Lógica Formal', 2),
(3, 'Hidrologia', 3),
(4, 'Genética Molecular', 4),
(5, 'Cirurgia Vascular', 5),
(6, 'Fitossanidade', 6),
(7, 'Desenvolvimento Organizacional', 7),
(8, 'Teoria Monetária', 8);

INSERT INTO terceira_sub_area (id, nome_terceira_sub, segunda_sub_area_id_segunda_sub_area) VALUES
(1, 'Álgebra Linear', 1),
(2, 'Lógica Formal', 2),
(3, 'Hidrologia', 3),
(4, 'Genética Molecular', 4),
(5, 'Cirurgia Vascular', 5),
(6, 'Fitossanidade', 6),
(7, 'Desenvolvimento Organizacional', 7),
(8, 'Teoria Monetária', 8);







-- Inserir tipos de pesquisador
INSERT INTO tipo_pesquisador (id_tipo_pesquisador, descricao_tipo_pesquisador) VALUES
(1, 'Pesquisador'),
(2, 'Pesquisador Estrangeiro');

INSERT INTO instituicao (id_Instituicao, nome_instituicao, sigla_instituicao, cnpj_instituicao, estrangeira_instituicao, pais_instituicao, dependencia_adm_instituicao, ensino_superior_instituicao, fins_lucrativos_lnstituicao) VALUES 
(1, 'Instituto Nacional de Pesquisa', 'INP', '12.345.678/0001-90', false, 'Brasil', 'Federal', true, false),
(2, 'Universidade Estadual de Tecnologia', 'UET', '98.765.432/0001-12', false, 'Brasil', 'Estadual', true, false),
(3, 'Tech Research International', 'TRI', '11.223.344/0001-55', true, 'Estados Unidos', 'Privada', true, true);

INSERT INTO pesquisador (
    id_pesquisador,
    nome_completo_pesquisador,
    foto_pesquisador,
    email_pesquisador,
    sexo_pesquisador,
    data_nascimento_pesquisador,
    cor_raca,
    nome_mae_pesquisador,
    nome_pai_pesquisador,
    curriculo_lattes_pesquisador,
    nivel_academico_pesquisador,
    cpf_pesquisador,
    senha_pesquisador,
    rg_pesquisador,
    orgao_emissor_pesquisador,
    uf_pesquisador,
    data_emissao_pesquisador,
    passaporte_pesquisador,
    passaporte_file_pesquisador,
    ativo,
    telefone_pesquisador,
    tipo_pesquisador_id_tipo_pesquisador
) VALUES (
    1, 
    'Nome Completo do Pesquisador',
    'foto_pesquisador.jpg',
    'email@pesquisador.com',
    'Masculino',
    '1985-08-20',
    'Parda',
    'Nome da Mãe',
    'Nome do Pai',
    'http://lattes.cnpq.br/1234567890123456',
    'Doutorado',
    '123.456.789-00', 
    'senhaSegura123',
    '12.345.678-9',
    'SSP',
    'SP',
    '2010-05-10', 
    'AB1234567', 
    'passaporte_pesquisador.pdf', 
    TRUE,
    '(11) 98765-4321',
    2
);

INSERT INTO endereco_pesquisador (id_endereco_pesquisador, id_pesquisador, bairro_endereco_pesquisador, cep_endereco_pesquisador, estado_endereco_pesquisador, logradouro_endereco_pesquisador, numero_endereco_pesquisador, pais_endereco_pesquisador, telefone_endereco_pesquisador, tipo_endereco_pesquisador) VALUES
(1, 1, 'Centro', '69900-000', 'AC', 'Rua Principal', '123', 'Brasil', '(68) 99999-9999', 'Residencial'),
(2, 2, 'Jardim Paulista', '01311-000', 'SP', 'Avenida Paulista', '2000', 'Brasil', '(11) 98888-8888', 'Residencial'),
(3, 3, 'Copacabana', '22060-001', 'RJ', 'Rua Atlântica', '4500', 'Brasil', '(21) 97777-7777', 'Comercial'),
(4, 4, 'Moinhos de Vento', '90570-002', 'RS', 'Rua Dona Laura', '101', 'Brasil', '(51) 96666-6666', 'Residencial'),
(5, 5, 'Vila Mariana', '04110-030', 'SP', 'Rua Vergueiro', '1500', 'Brasil', '(11) 95555-5555', 'Residencial'),
(6, 6, 'Batel', '80240-000', 'PR', 'Avenida Batel', '3000', 'Brasil', '(41) 94444-4444', 'Comercial'),
(7, 7, 'Lourdes', '30180-001', 'MG', 'Rua da Bahia', '1234', 'Brasil', '(31) 93333-3333', 'Residencial'),
(8, 8, 'Praia do Canto', '29055-180', 'ES', 'Rua Aleixo Neto', '500', 'Brasil', '(27) 92222-2222', 'Residencial');

INSERT INTO pesquisador_conhecimento (area_conhecimento_id_area_conhecimento, id_pesquisador_conhecimento, pesquisador_id_pesquisador, primeira_sub_area_id, segunda_sub_area_id_segunda_sub_area, terceira_sub_area_id) 
VALUES
(1, 1, 1, 1, 1, 1), -- João da Silva: Ciências Exatas > Matemática > Álgebra > Álgebra Linear
(2, 2, 2, 2, 2, 2), -- Maria Oliveira: Ciências Humanas > Filosofia > Lógica > Lógica Formal
(3, 3, 3, 3, 3, 3), -- Carlos Souza: Engenharia > Engenharia Civil > Hidráulica > Hidrologia
(4, 4, 4, 4, 4, 4), -- Fernanda Lima: Ciências Biológicas > Biologia Geral > Genética > Genética Molecular
(5, 5, 5, 5, 5, 5), -- Lucas Pereira: Ciências da Saúde > Medicina > Cirurgia Geral > Cirurgia Vascular
(6, 6, 6, 6, 6, 6), -- Gabriela Ramos: Ciências Agrárias > Agronomia > Fitotecnia > Fitossanidade
(7, 7, 7, 7, 7, 7), -- Ricardo Costa: Ciências Sociais Aplicadas > Administração > Gestão de Pessoas > Desenvolvimento Organizacional
(7, 8, 8, 8, 8, 8); -- Aline Martins: Ciências Sociais Aplicadas > Economia > Macroeconomia > Teoria Monetária

INSERT INTO EnderecoUnidade (idEnderecoUnidade, cepEnderecoUnidade, logradouroEnderecoUnidade, numeroEnderecoUnidade, bairroEnderecoUnidade, estadoEnderecoUnidade, paisEnderecoUnidade) VALUES
(1, '70040-010', 'Rua da Ciência', '100', 'Centro', 'DF', 'Brasil'),
(2, '01030-020', 'Avenida da Inovação', '200', 'Vila Nova', 'SP', 'Brasil'),
(3, '10001', '5th Avenue', '500', 'Manhattan', 'NY', 'Estados Unidos');

-- INSERT INTO UnidadeInstituicao (idUnidadeInstituicao, nomeUnidade, emailUnidade, telefoneUnidade, idEnderecoUnidade, instituicao_id) VALUES
-- (1, 'Laboratório de Biotecnologia', 'lab.biotecnologia@inp.br', '(61) 3333-3333', 1, 1),
-- (2, 'Centro de Pesquisa Tecnológica', 'centro.pesquisa@uet.br', '(11) 4444-4444', 2, 2),
-- (3, 'International Research Center', 'contact@tri.org', '(212) 555-1234', 3, 3);

INSERT INTO unidade_instituicao (id_unidade_instituicao, nome_unidade, email_unidade, telefone_unidade, id_endereco_unidade, id_Instituicao) VALUES
(1, 'Laboratório de Biotecnologia', 'lab.biotecnologia@inp.br', '(61) 3333-3333', 1, 1),
(2, 'Centro de Pesquisa Tecnológica', 'centro.pesquisa@uet.br', '(11) 4444-4444', 2, 2),
(3, 'International Research Center', 'contact@tri.org', '(212) 555-1234', 3, 3);

INSERT INTO vinculo_institucional (vinculo_institucional, vinculo_empregaticio, tempo_servico, regime_trabalho, funcao_cargo, tempo_da_funcao, pesquisador_id_pesquisador, id_Instituicao, id_unidade_instituicao) VALUES 
('Professor', TRUE, '5 anos', '40 horas semanais', 'Docente', '3 anos', 1, 1, 1),
('Programador', TRUE, '5 anos', '40 horas semanais', 'Desenvolvedor', '3 anos', 1, 1, 1);

INSERT INTO endereco_unidade (id_endereco_unidade, bairro_endereco_unidade, cep_endereco_unidade, estado_endereco_unidade, logradouro_endereco_unidade, numero_endereco_unidade, pais_endereco_unidade) 
VALUES (1, 'Centro', '69900-000', 'AC', 'Rua 1', '123', 'Brasil');

INSERT INTO endereco_unidade (id_endereco_unidade, bairro_endereco_unidade, cep_endereco_unidade, estado_endereco_unidade, logradouro_endereco_unidade, numero_endereco_unidade, pais_endereco_unidade) 
VALUES (2, 'Floresta', '69910-001', 'AC', 'Av. Brasil', '456', 'Brasil');

INSERT INTO endereco_unidade (id_endereco_unidade, bairro_endereco_unidade, cep_endereco_unidade, estado_endereco_unidade, logradouro_endereco_unidade, numero_endereco_unidade, pais_endereco_unidade) 
VALUES (3, 'Bosque', '69920-002', 'AC', 'Rua das Palmeiras', '789', 'Brasil');

INSERT INTO funcao (ativo_funcao, id_funcao, nome_funcao) VALUES (true, 1, 'Administrador');
INSERT INTO funcao (ativo_funcao, id_funcao, nome_funcao) VALUES (true, 2, 'Coordenador');
INSERT INTO funcao (ativo_funcao, id_funcao, nome_funcao) VALUES (true, 3, 'Aluno');
INSERT INTO funcao (ativo_funcao, id_funcao, nome_funcao) VALUES (false, 4, 'Visitante');
INSERT INTO funcao (ativo_funcao, id_funcao, nome_funcao) VALUES (true, 5, 'Pesquisador');

INSERT INTO Edital (
    id_edital,
    titulo_edital,
    file_edital,
    identificacao_edital,
    numero_ano_edital,
    nome_formulario_edital,
    cod_unidade_adm_edital,
    texto_chamada_edital,
    duracao_proposta_edital,
    multiplas_proposta_edital,
    cordenador_participa_edital,
    carga_horaria_edital,
    escolhe_duracao_edital,
    obg_orientador_edital,
    cordenador_bolsa_edital,
    edital_especial,
    proponente_bolsa_edital,
    patente_edital,
    inovacao_tec_edital,
    auto_etica_edital,
    termo_aceite_boolean_edital,
    termo_aceite_texto_edital,
    data_abertura_edital,
    hora_abertura_edital,
    data_fechamento_edital,
    hora_fechamento_edital,
    data_enquadramento_edital,
    data_recurso_edital,
    data_resultado_edital,
    data_recurso_final_edital,
    data_bolsa_edital,
    informacao_chamadas_edital,
    cordenador_config_orcamento_edital,
    tipo_moeda_edital,
    proponente_curriculo_edital,
    membro_curriculo_edital,
    habilita_vinculo_emprega_edital,
    proponente_vinculo_emprega_edital,
    habilita_vinculo_instituicao_edital,
    proponente_vinculo_instituicao_edital,
    proponente_nivel_academico_edital,
    experiencia_cordenador_edital,
    objetivo_geral_edital,
    objetivo_especifico_edital,
    metodologia_edital,
    metodo_mat_edital,
    resultados_edital,
    impacto_esperado_edital,
    impacto_discriminado_edital,
    risco_atividade_edital,
    referencia_blib_edital,
    estado_arte_edital,
    just_cop_inter_edital,
    quali_parcerias_edital,
    obras_instal_edital,
    prod_blib_edital,
    prod_cultura_edital,
    prod_tec_edital,
    prod_difu_edital,
    situacao_edital,
    id_programa
) VALUES (
    1, -- idEdital
    'Edital para Bolsas de Pesquisa', -- tituloEdital
    'edital_2024.pdf', -- fileEdital
    'EDITAL-001', -- identificacaoEdital
    '2024', -- numeroAnoEdital
    'Formulário de Inscrição 2024', -- nomeFormularioEdital
    101, -- codUnidadeAdmEdital
    'Chamada pública para concessão de bolsas de pesquisa.', -- textoChamadaEdital
    '24 meses', -- duracaoPropostaEdital
    true, -- multiplasPropostaEdital
    true, -- cordenadorParticipaEdital
    false, -- cargaHorariaEdital
    true, -- escolheDuracaoEdital
    true, -- obgOrientadorEdital
    false, -- cordenadorBolsaEdital
    true, -- editalEspecial
    true, -- proponenteBolsaEdital
    false, -- patenteEdital
    true, -- inovacaoTecEdital
    true, -- autoEticaEdital
    false, -- termoAceiteBooleanEdital
    'Termo de aceite obrigatório para submissão de propostas.', -- termoAceiteTextoEdital
    '2024-01-10', -- dataAberturaEdital
    '08:00:00', -- horaAberturaEdital
    '2024-03-31', -- dataFechamentoEdital
    '17:00:00', -- horaFechamentoEdital
    '2024-04-10', -- dataEnquadramentoEdital
    '2024-04-20', -- dataRecursoEdital
    '2024-05-15', -- dataResultadoEdital
    '2024-05-25', -- dataRecursoFinalEdital
    '2024-06-01', -- dataBolsaEdital
    'Informações adicionais sobre chamadas e submissão de propostas.', -- informacaoChamadasEdital
    true, -- cordenadorConfigOrcamentoEdital
    'BRL', -- tipoMoedaEdital
    true, -- proponenteCurriculoEdital
    true, -- membroCurriculoEdital
    false, -- habilitaVinculoEmpregaEdital
    true, -- proponenteVinculoEmpregaEdital
    false, -- habilitaVinculoInstituicaoEdital
    true, -- proponenteVinculoInstituicaoEdital
    'Doutorado', -- proponenteNivelAcademicoEdital
    true, -- experienciaCordenadorEdital
    true, -- objetivoGeralEdital
    true, -- objetivoEspecificoEdital
    true, -- metodologiaEdital
    true, -- metodoMatEdital
    true, -- resultadosEdital
    true, -- impactoEsperadoEdital
    true, -- impactoDiscriminadoEdital
    true, -- riscoAtividadeEdital
    true, -- referenciaBlibEdital
    true, -- estadoArteEdital
    true, -- justCopInterEdital
    true, -- QualiParceriasEdital
    true, -- obrasInstalEdital
    true, -- prodBlibEdital
    true, -- prodCulturaEdital
    true, -- prodTecEdital
    true, -- prodDifuEdital
    'Aberto', -- situacaoEdital
    1 -- idPrograma (referência a um programa existente)
);
INSERT INTO edital (
    id_edital,
    titulo_edital,
    file_edital,
    data_criacao_edital,
    identificacao_edital,
    numero_ano_edital,
    nome_formulario_edital,
    cod_unidade_adm_edital,
    texto_chamada_edital,
    duracao_proposta_edital,
    multiplas_proposta_edital,
    cordenador_participa_edital,
    carga_horaria_edital,
    escolhe_duracao_edital,
    obg_orientador_edital,
    cordenador_bolsa_edital,
    edital_especial,
    proponente_bolsa_edital,
    patente_edital,
    inovacao_tec_edital,
    auto_etica_edital,
    termo_aceite_boolean_edital,
    termo_aceite_texto_edital,
    data_abertura_edital,
    hora_abertura_edital,
    data_fechamento_edital,
    hora_fechamento_edital,
    data_enquadramento_edital,
    data_recurso_edital,
    data_resultado_edital,
    data_recurso_final_edital,
    data_bolsa_edital,
    informacao_chamadas_edital,
    cordenador_config_orcamento_edital,
    id_moeda,
    proponente_curriculo_edital,
    membro_curriculo_edital,
    habilita_vinculo_emprega_edital,
    proponente_vinculo_emprega_edital,
    habilita_vinculo_instituicao_edital,
    proponente_vinculo_instituicao_edital,
    proponente_nivel_academico_edital,
    experiencia_cordenador_edital,
    objetivo_geral_edital,
    objetivo_especifico_edital,
    metodologia_edital,
    metodo_mat_edital,
    resultados_edital,
    impacto_esperado_edital,
    impacto_discriminado_edital,
    risco_atividade_edital,
    referencia_blib_edital,
    estado_arte_edital,
    just_cop_inter_edital,
    quali_parcerias_edital,
    obras_instal_edital,
    prod_blib_edital,
    prod_cultura_edital,
    prod_tec_edital,
    prod_difu_edital,
    situacao_edital,
    id_programa
) VALUES (
    null,                                      -- id_edital
    'Edital de Pesquisa 2024',               -- titulo_edital
    'edital_2024.pdf',                       -- file_edital
    '2024-10-08T12:00:00',                   -- data_criacao_edital (LocalDateTime)
    '12345/2024',                            -- identificacao_edital
    '2024',                                  -- numero_ano_edital
    'Formulário Padrão 2024',                -- nome_formulario_edital
    1001,                                    -- cod_unidade_adm_edital
    'Chamada para projetos de pesquisa.',    -- texto_chamada_edital
    '12 meses',                              -- duracao_proposta_edital
    true,                                    -- multiplas_proposta_edital
    false,                                   -- cordenador_participa_edital
    true,                                    -- carga_horaria_edital
    true,                                    -- escolhe_duracao_edital
    false,                                   -- obg_orientador_edital
    true,                                    -- cordenador_bolsa_edital
    false,                                   -- edital_especial
    true,                                    -- proponente_bolsa_edital
    false,                                   -- patente_edital
    true,                                    -- inovacao_tec_edital
    false,                                   -- auto_etica_edital
    true,                                    -- termo_aceite_boolean_edital
    'Aceito os termos e condições.',         -- termo_aceite_texto_edital
    '2024-11-01',                            -- data_abertura_edital (LocalDate)
    '09:00:00',                              -- hora_abertura_edital (LocalTime)
    '2024-11-30',                            -- data_fechamento_edital (LocalDate)
    '18:00:00',                              -- hora_fechamento_edital (LocalTime)
    '2024-11-15',                            -- data_enquadramento_edital (LocalDate)
    '2024-12-05',                            -- data_recurso_edital (LocalDate)
    '2024-12-15',                            -- data_resultado_edital (LocalDate)
    '2024-12-20',                            -- data_recurso_final_edital (LocalDate)
    '2025-01-10',                            -- data_bolsa_edital (LocalDate)
    'Informações adicionais sobre chamadas', -- informacao_chamadas_edital
    false,                                   -- cordenador_config_orcamento_edital
    null,                                    -- id_moeda (supondo que não há moeda relacionada)
    true,                                    -- proponente_curriculo_edital
    false,                                   -- membro_curriculo_edital
    true,                                    -- habilita_vinculo_emprega_edital
    false,                                   -- proponente_vinculo_emprega_edital
    true,                                    -- habilita_vinculo_instituicao_edital
    false,                                   -- proponente_vinculo_instituicao_edital
    'Doutorado',                             -- proponente_nivel_academico_edital
    true,                                    -- experiencia_cordenador_edital
    true,                                    -- objetivo_geral_edital
    true,                                    -- objetivo_especifico_edital
    true,                                    -- metodologia_edital
    false,                                   -- metodo_mat_edital
    true,                                    -- resultados_edital
    false,                                   -- impacto_esperado_edital
    true,                                    -- impacto_discriminado_edital
    true,                                    -- risco_atividade_edital
    true,                                    -- referencia_blib_edital
    true,                                    -- estado_arte_edital
    true,                                    -- just_cop_inter_edital
    true,                                    -- quali_parcerias_edital
    false,                                   -- obras_instal_edital
    true,                                    -- prod_blib_edital
    false,                                   -- prod_cultura_edital
    true,                                    -- prod_tec_edital
    false,                                   -- prod_difu_edital
    'Em andamento',                          -- situacao_edital
    1                                     -- id_programa (supondo que não há programa relacionado)
);

INSERT INTO estado (id_estado, nome_estado) VALUES
(1, 'Acre'),
(2, 'Alagoas'),
(3, 'Amapá'),
(4, 'Amazonas'),
(5, 'Bahia'),
(6, 'Ceará'),
(7, 'Distrito Federal'),
(8, 'Espírito Santo'),
(9, 'Goiás'),
(10, 'Maranhão'),
(11, 'Mato Grosso'),
(12, 'Mato Grosso do Sul'),
(13, 'Minas Gerais'),
(14, 'Pará'),
(15, 'Paraíba'),
(16, 'Paraná'),
(17, 'Pernambuco'),
(18, 'Piauí'),
(19, 'Rio de Janeiro'),
(20, 'Rio Grande do Norte'),
(21, 'Rio Grande do Sul'),
(22, 'Rondônia'),
(23, 'Roraima'),
(24, 'Santa Catarina'),
(25, 'São Paulo'),
(26, 'Sergipe'),
(27, 'Tocantins');

INSERT INTO moeda (ativa_moeda, nome_moeda) VALUES
(true, 'Real Brasileiro'),
(true, 'Dólar Americano'),
(true, 'Euro'),
(true, 'Iene Japonês'),
(true, 'Yuan Chinês'),
(true, 'Peso Argentino');

INSERT INTO Banco (id_banco, nome_banco, numero_banco, ativo_banco) 
VALUES (1, 'Bradesco', 237, true),
(2, 'Banco do Brasil', 001, true),
(3, 'Itaú', 341, true),
(4, 'Santander', 033, true),
(5, 'Caixa Econômica Federal', 104, true),
(6, 'Banco Safra', 422, true),
(7, 'Banco Inter', 077, true),
(8, 'Banco Original', 212, true),
(9, 'Banco Neon', 735, true),
(10, 'Banco Pan', 623, true);

INSERT INTO rubrica (ativo_rubrica, codigo_rubrica, nome_rubrica, tipo_rubrica) VALUES
(true, 101, 'Diárias', 'diarias'),
(true, 102, 'Diárias sem limite de data', 'diariasSemLimite'),
(true, 103, 'Hospedagem e Alimentação', 'hospedagem'),
(true, 104, 'Terceiros', 'terceiros'),
(true, 105, 'Terceiros sem limite de data', 'terceirosSemLimite'),
(true, 106, 'Material de Consumo', 'materialConsumo'),
(true, 107, 'Material de Consumo sem limite de data', 'materialConsumoSemLimite'),
(true, 108, 'Material Permanente e Equipamentos', 'materialPermanente'),
(true, 109, 'Passagens', 'passagens'),
(true, 110, 'Passagens sem limite de data', 'passagensSemLimite'),
(true, 111, 'Contrapartida', 'contrapartida'),
(true, 112, 'Pessoal', 'pessoal'),
(true, 113, 'Pessoal sem limite de data', 'pessoalSemLimite'),
(true, 114, 'Encargos', 'encargos'),
(true, 115, 'Bolsas', 'bolsas');

INSERT INTO programa (ativo_programa, nome_programa) VALUES
(true, 'Programa de Pesquisa e Desenvolvimento'),
(true, 'Programa de Inovação Tecnológica'),
(true, 'Programa de Formação Acadêmica'),
(true, 'Programa de Capacitação Profissional'),
(true, 'Programa de Internacionalização'),
(true, 'Programa de Extensão Universitária'),
(true, 'Programa de Apoio a Eventos Científicos'),
(true, 'Programa de Bolsas de Estudo'),
(true, 'Programa de Iniciação Científica'),
(true, 'Programa de Parcerias Empresariais');


INSERT INTO doc_pessoal (nome_doc_pessoal, ativo_doc_pessoal) 
VALUES 
    ('Documento de Identidade', true),
    ('Carteira de Trabalho', true),
    ('Passaporte', true);

INSERT INTO proposta (
    titulo_proposta,
    grupo_pesquisa,
    data_inicio_pesquisa,
    duracao_proposta,
    termo_aceite_proposta,
    patente_proposta,
    inovacao_proposta,
    etica_proposta,
    resumo_proposta,
    palavra_chave_proposta,
    sintese_proposta,
    exp_cord_proposta,
    objetivo_geral_proposta,
    objetivo_esp_proposta,
    metodologia_proposta,
    metodos_mat_proposta,
    results_proposta,
    impactos_proposta,
    impactos_cien_proposta,
    impactos_tech_proposta,
    impactos_social_proposta,
    impactos_ambiental_proposta,
    ref_blib_proposta,
    estado_arte_proposta,
    just_cop_inter_proposta,
    quali_parcerias_proposta,
    obras_instal_proposta,
    estado_proposta,
    situacao,
    faixa_financiamento_id_faixa_financiamento,
    edital_id_edital,
    pesquisador_id_pesquisador,
    orcamento_proposta_id_orcamento_proposta
) VALUES (
    'Titulo da Proposta',
    'Grupo de Pesquisa A',
    '2024-01-01',
    '12 meses',
    true,
    false,
    true,
    true,
    'Resumo da proposta',
    'palavra-chave',
    'Síntese da proposta',
    'Experiência do coordenador',
    'Objetivo geral da proposta',
    'Objetivo específico da proposta',
    'Metodologia da proposta',
    'Métodos e materiais',
    'Resultados esperados',
    'Impactos da proposta',
    'Impactos científicos',
    'Impactos tecnológicos',
    'Impactos sociais',
    'Impactos ambientais',
    'Referência bibliográfica',
    'Estado da arte da proposta',
    'Justificativa da cooperação',
    'Qualidade das parcerias',
    'Obras e instalações',
    'Estado da proposta',
    'situação',
    1, -- ID da faixa de financiamento
    1, -- ID do edital
    1, -- ID do pesquisador
    1  -- ID do orçamento
);

INSERT INTO orcamento_proposta (
    id_orcamento_proposta
) VALUES (
    1
);
    
SET SESSION FOREIGN_KEY_CHECKS=1;
