import { Routes } from '@angular/router';
import { LoginComponent } from './component/login/login.component';
import { CadastroDadosBrComponent } from './component/cadastro-dados-br/cadastro-dados-br.component';
import { EnderecoUnidadeFormComponent } from './component/endereco-unidade/endereco-unidade-form/endereco-unidade-form.component';
import { CadastroDadosEsComponent } from './component/cadastro-dados-es/cadastro-dados-es.component';
import { CadastroEnderecoPesquisadoresComponent } from './component/cadastro-endereco-pesquisadores/cadastro-endereco-pesquisadores.component';
import { CadastroSenhaPesquisadorComponent } from './component/cadastro-senha-pesquisador/cadastro-senha-pesquisador.component';
import { CadastroAreaconhecimentoPesquisadorComponent } from './component/cadastro-areaconhecimento-pesquisador/cadastro-areaconhecimento-pesquisador.component';
import { CadastroUnidadeComponent } from './component/cadastro-unidade/cadastro-unidade.component';
import { CadastroDadosProfissionaisComponent } from './component/cadastro-dados-profissionais/cadastro-dados-profissionais.component';
import { CadastroInstituicaoComponent } from './component/cadastro-instituicao/cadastro-instituicao.component';
import { UploadComponent } from './component/upload/upload.component';
import { PesquisadorEsFormComponent } from './component/pesquisador-es/pesquisador-es-form/pesquisador-es-form.component';
import { BancoFormComponent } from './component/banco/banco-form/banco-form.component';
import { DiariaFormComponent } from './component/diaria/diaria-form/diaria-form/diaria-form.component';
import { DiariaListComponent } from './component/diaria/diaria-list/diaria-list/diaria-list.component';
import { BancoListComponent } from './component/banco/banco-list/banco-list.component';
import { ArquivoListComponent } from './component/arquivo/arquivo-list/arquivo-list.component';
import { ArquivoFormComponent } from './component/arquivo/arquivo-form/arquivo-form.component';
import { ProgramaFormComponent } from './component/programa/programa-form/programa-form/programa-form.component';
import { ProgramaListComponent } from './component/programa/programa-list/programa-list/programa-list.component';
import { RetificacaoFormComponent } from './component/retificacao/retificacao-form/retificacao-form/retificacao-form.component';
import { EditaldadosListComponent } from './component/editalDados/editaldados-list/editaldados-list.component';
import { EditaldadosFormComponent } from './component/editalDados/editaldados-form/editaldados-form.component';
import { EditalComponent } from './component/edital/edital.component';
import { EditalChamadasFormComponent } from './component/editalChamadas/edital-chamadas-form/edital-chamadas-form.component';
import { FuncaoFormComponent } from './component/funcao/funcao-form/funcao-form.component';
import { FuncaoListComponent } from './component/funcao/funcao-list/funcao-list.component';
import { AbrangenciaFormComponent } from './component/abrangencia/abrangencia-form/abrangencia-form/abrangencia-form.component';
import { EditalOrcamentoFormComponent } from './component/editalOrcamento/edital-orcamento-form/edital-orcamento-form.component';
import { EditalModeloFormComponent } from './component/editalmodelo/edital-modelo-form/edital-modelo-form.component';
import { EditalPlanoApresentacaoFormComponent } from './component/editalPlanoApresentacao/edital-plano-apresentacao/edital-plano-apresentacao.component';
import { DocPessoaisFormComponent } from './component/doc-pessoais/doc-pessoais-form/doc-pessoais-form/doc-pessoais-form.component';
import { MoedaFormComponent } from './component/moeda/moeda-form/moeda-form.component';
import { MoedaListComponent } from './component/moeda/moeda-list/moeda-list.component';
import { FaixafinanciamentoFormComponent } from './component/faixa-financiamento/faixafinanciamento-form/faixafinanciamento-form.component';
import { EditalRestricoesFormComponent } from './component/editalRestricoes/edital-restricoes-form/edital-restricoes-form.component';
import { EditalPrestacaoFormComponent } from './component/editalPrestacao/edital-prestacao-form/edital-prestacao-form.component';
import { PesquisadorMainComponent } from './component/pesquisadorMain/pesquisador-main/pesquisador-main.component';
import { AbrangenciaPropostaFormComponent } from './component/abrangenciaProposta/abrangencia-proposta-form/abrangencia-proposta-form.component';
import { TituloPropostaFormComponent } from './component/tituloProposta/titulo-proposta-form/titulo-proposta-form.component';
import { PesquisadorBrFormComponent } from './component/pesquisador-br/pesquisador-br-form/pesquisador-br-form.component';
import { DiariaPropostaFormComponent } from './component/diariaProposta/diaria-proposta-form/diaria-proposta-form.component';
import { MaterialPropostaFormComponent } from './component/materialProposta/material-proposta-form/material-proposta-form.component';
import { ServicoPropostaFormComponent } from './component/servicoProposta/servico-proposta-form/servico-proposta-form.component';
import { MaterialPermPropostaFormComponent } from './component/materialPermProposta/material-perm-proposta-form/material-perm-proposta-form.component';
import { EncargoPropostaFormComponent } from './component/encargo-proposta/encargo-proposta.component';
import { PassagemPropostaFormComponent } from './component/passagemProposta/passagem-proposta-form/passagem-proposta-form.component';
import { ParceriasPropostaFormComponent } from './component/parceriasProposta/parcerias-proposta-form/parcerias-proposta-form.component';
import { PropostaConhecimentoFormComponent } from './component/propostaConhecimento/proposta-conhecimento-form/proposta-conhecimento-form.component';
import { PessoalPropostaFormComponent } from './component/pessoalProposta/pessoal-proposta-form/pessoal-proposta-form.component';
import { PropostaMembrosFormComponent } from './component/propostaMembros/proposta-membros-form/proposta-membros-form.component';
import { BolsaPropostaFormComponent } from './component/bolsaproposta/bolsa-proposta-form/bolsa-proposta-form.component';
import { EditalInfoListComponent } from './component/editalInfo/edital-info-list/edital-info-list.component';
import { AtividadePropostaFormComponent } from './component/atividadeProposta/atividade-proposta-form/atividade-proposta-form.component';
import { HospedagemAlimentacaoPropostaComponent } from './component/hospedagemAlimentacaoProposta/hospedagem-alimentacao-proposta/hospedagem-alimentacao-proposta.component';

export const routes: Routes = [
    { path: '', children: [
        { path: 'login', component: LoginComponent },
        { path: 'cadastro/brasileiro', component:CadastroDadosBrComponent },
        { path: 'cadastro/endereco/unidade', component:EnderecoUnidadeFormComponent },
        { path: 'cadastro/unidade', component: CadastroUnidadeComponent},
        { path: 'cadastro/estrangeiro', component:CadastroDadosEsComponent},
        { path: 'cadastro/endereco', component:CadastroEnderecoPesquisadoresComponent},
        { path: 'cadastro/senha', component: CadastroSenhaPesquisadorComponent},
        { path: 'cadastro/area/conhecimento', component: CadastroAreaconhecimentoPesquisadorComponent},
        { path: 'cadastro/vinculo', component: CadastroDadosProfissionaisComponent},
        { path: 'cadastro/instituicao', component: CadastroInstituicaoComponent},
        { path: 'upload/imagem', component: UploadComponent},
        { path: 'pesquisador/estrangeiro', component: PesquisadorEsFormComponent},
        { path: 'diarias', component: DiariaListComponent},
        { path: 'diarias/form', component: DiariaFormComponent},
        { path: 'bancos', component: BancoListComponent},
        { path: 'bancos/form', component: BancoFormComponent},
        { path: 'arquivos', component: ArquivoListComponent},
        { path: 'arquivos/form', component: ArquivoFormComponent},
        { path: 'programa/form', component: ProgramaFormComponent},
        { path: 'programa', component: ProgramaListComponent},
        { path: 'retificacao/form' , component: RetificacaoFormComponent},
        { path: 'editaldados', component: EditaldadosListComponent},
        { path: 'editais/form', component: EditaldadosFormComponent},
        { path: 'editalChamadas/form', component: EditalChamadasFormComponent},
        { path: 'editais', component: EditalComponent},
        { path: 'funcao/form', component: FuncaoFormComponent},
        { path: 'funcao', component: FuncaoListComponent},
        { path: 'editalabrangencia/form', component: AbrangenciaFormComponent},
        { path: 'editalorcamento/form', component: EditalOrcamentoFormComponent},
        { path: 'editalmodelo/form', component: EditalModeloFormComponent},
        { path: 'editalrestricao/form', component: EditalRestricoesFormComponent},
        { path: 'editalplanoapresentacao/form', component: EditalPlanoApresentacaoFormComponent},
        { path: 'docpessoais/form', component: DocPessoaisFormComponent},
        { path: 'moeda/form', component: MoedaFormComponent},
        { path: 'moeda', component: MoedaListComponent},
        { path: 'editalfaixa/form', component: FaixafinanciamentoFormComponent},
        { path: 'editatinfs', component: EditalInfoListComponent},
        { path: 'editalprestacao/form', component: EditalPrestacaoFormComponent},
        { path: 'pesquisador/main', component: PesquisadorMainComponent},
        { path: 'propostaabrangencia/form' , component: AbrangenciaPropostaFormComponent},
        { path: 'propostatitulo/form', component: TituloPropostaFormComponent},
        { path: 'pesquisador/brasileiro', component: PesquisadorBrFormComponent},
        { path: 'propostadiaria/form', component: DiariaPropostaFormComponent},
        { path: 'propostamaterial/form', component: MaterialPropostaFormComponent},
        { path: 'propostaservico/form', component: ServicoPropostaFormComponent},
        { path: 'propostamaterialperm/form' , component: MaterialPermPropostaFormComponent},
        { path: 'propostaencargo/form' ,component: EncargoPropostaFormComponent},
        { path: 'propostapassagem/form', component: PassagemPropostaFormComponent},
        { path: 'propostaparcerias/form', component: ParceriasPropostaFormComponent},
        { path: 'propostaconhecimento/form', component: PropostaConhecimentoFormComponent},
        { path: 'propostapessoal/form', component: PessoalPropostaFormComponent},
        { path: 'propostamembros/form', component: PropostaMembrosFormComponent},
        { path: 'propostabolsa/form', component: BolsaPropostaFormComponent},
        { path: 'propostaatividade/form', component: AtividadePropostaFormComponent},
        { path: 'propostahospedagem/form', component: HospedagemAlimentacaoPropostaComponent}


        /*
        { path: 'pesquisador/proposta',},
        { path: 'pesquisador/edital', },
        { path: 'admin', },
        { path: 'admin/edital', },
        { path: 'config', children: [

        ]},*/

    ]},
];
