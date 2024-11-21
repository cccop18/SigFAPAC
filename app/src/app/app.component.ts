import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './component/login/login.component';
import { MenuLateralComponent } from './component/menu-lateral/menu-lateral.component';
import { CadastroDadosBrComponent } from './component/cadastro-dados-br/cadastro-dados-br.component';
import { CadastroDadosEsComponent } from './component/cadastro-dados-es/cadastro-dados-es.component';
import { CadastroEnderecoPesquisadoresComponent } from './component/cadastro-endereco-pesquisadores/cadastro-endereco-pesquisadores.component';
import { CadastroSenhaPesquisadorComponent } from './component/cadastro-senha-pesquisador/cadastro-senha-pesquisador.component';
import { CadastroAreaconhecimentoPesquisadorComponent } from './component/cadastro-areaconhecimento-pesquisador/cadastro-areaconhecimento-pesquisador.component';
import { EnderecoUnidadeFormComponent } from './component/endereco-unidade/endereco-unidade-form/endereco-unidade-form.component';
import { CadastroUnidadeComponent } from './component/cadastro-unidade/cadastro-unidade.component';
import { PesquisadorEsFormComponent } from './component/pesquisador-es/pesquisador-es-form/pesquisador-es-form.component';
import { AlertaComponent } from './component/alerta/alerta.component';
import { BancoListComponent } from './component/banco/banco-list/banco-list.component';
import { DiariaFormComponent } from './component/diaria/diaria-form/diaria-form/diaria-form.component';
import { DiariaListComponent } from './component/diaria/diaria-list/diaria-list/diaria-list.component';
import { ArquivoFormComponent } from './component/arquivo/arquivo-form/arquivo-form.component';
import { ArquivoListComponent } from './component/arquivo/arquivo-list/arquivo-list.component';
import { EditaldadosListComponent } from './component/editalDados/editaldados-list/editaldados-list.component';
import { EditaldadosFormComponent } from './component/editalDados/editaldados-form/editaldados-form.component';
import { EditalComponent } from './component/edital/edital.component';
import { MenuEditalComponent } from './component/menu-edital/menu-edital.component';
import { PesquisadorBrFormComponent } from './component/pesquisador-br/pesquisador-br-form/pesquisador-br-form.component';
import { EditalInfoListComponent } from './component/editalInfo/edital-info-list/edital-info-list.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, 
            FormsModule, CommonModule, 
            ReactiveFormsModule, LoginComponent, 
            MenuLateralComponent, CadastroDadosBrComponent, 
            CadastroDadosEsComponent, CadastroEnderecoPesquisadoresComponent, 
            CadastroSenhaPesquisadorComponent, CadastroAreaconhecimentoPesquisadorComponent, 
            EnderecoUnidadeFormComponent,CadastroUnidadeComponent,
            PesquisadorEsFormComponent,CadastroUnidadeComponent, 
            AlertaComponent, BancoListComponent,
            DiariaFormComponent, DiariaListComponent,
            ArquivoFormComponent, ArquivoListComponent,
            EditaldadosListComponent, EditaldadosFormComponent,
            EditalComponent, MenuEditalComponent, PesquisadorBrFormComponent,
            EditalInfoListComponent,],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})


export class AppComponent {
  title = 'fapac-app';

  isMenuOpen: boolean = false;

  abrirIcon: string = 'assets/img/menu-abrir.png';
  fecharIcon: string = 'assets/img/menu-fechar.png';

  // Alterna o estado do menu (aberto ou fechado)
  toggleMenu(): void {
    this.isMenuOpen = !this.isMenuOpen;
  }

  // Retorna o ícone apropriado com base no estado do menu
  getIcon(): string {
    return this.isMenuOpen ? this.fecharIcon : this.abrirIcon;
  }
}
