import { Routes } from '@angular/router';
import { LoginComponent } from './component/login/login.component';
import { CadastroDadosBrComponent } from './component/cadastro-dados-br/cadastro-dados-br.component';
import { EnderecoUnidadeFormComponent } from './component/endereco-unidade/endereco-unidade-form/endereco-unidade-form.component';
import { CadastroDadosEsComponent } from './component/cadastro-dados-es/cadastro-dados-es.component';
import { CadastroEnderecoPesquisadoresComponent } from './component/cadastro-endereco-pesquisadores/cadastro-endereco-pesquisadores.component';
import { CadastroSenhaPesquisadorComponent } from './component/cadastro-senha-pesquisador/cadastro-senha-pesquisador.component';
import { CadastroAreaconhecimentoPesquisadorComponent } from './component/cadastro-areaconhecimento-pesquisador/cadastro-areaconhecimento-pesquisador.component';


export const routes: Routes = [
    { path: '', children: [
        { path: 'login', component: LoginComponent },
        { path: '.cadastro/brasileiro', component:CadastroDadosBrComponent },
        { path: 'enderecoUnidade/form', component:EnderecoUnidadeFormComponent },
        { path: '.cadastro/estrangeiro', component:CadastroDadosEsComponent},
        { path: 'cadastro/endereco', component:CadastroEnderecoPesquisadoresComponent},
        { path: 'cadastro/senha', component: CadastroSenhaPesquisadorComponent},
        { path: 'cadastro/area/conhecimento', component: CadastroAreaconhecimentoPesquisadorComponent}
        /*
        { path: 'pesquisador/proposta',},
        { path: 'pesquisador/edital', },
        { path: 'admin', },
        { path: 'admin/edital', },
        { path: 'config', children: [

        ]},*/

    ]},
    { path: 'enderecoUnidade/form', component:EnderecoUnidadeFormComponent },
];
