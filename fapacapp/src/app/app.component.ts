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


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, FormsModule, CommonModule, ReactiveFormsModule, LoginComponent, MenuLateralComponent, CadastroDadosBrComponent, CadastroDadosEsComponent, CadastroEnderecoPesquisadoresComponent, CadastroSenhaPesquisadorComponent, CadastroAreaconhecimentoPesquisadorComponent],
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
