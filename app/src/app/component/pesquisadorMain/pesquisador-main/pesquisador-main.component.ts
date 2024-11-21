import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EditalService } from '../../../service/edital/edital.service';
import { AlertaService } from '../../../service/alerta/alerta.service';
import { RespostaPaginada } from '../../../model/RespostaPaginada';
import { Edital } from '../../../model/Edital';


@Component({
  selector: 'app-pesquisador-main',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './pesquisador-main.component.html',
  styleUrls: ['./pesquisador-main.component.css']
})
export class PesquisadorMainComponent {

  menuIcon: string = '../../../../assets/img/sinal-de-seta-para-cima-para-navegar.png';
  mostrarEditais: boolean = true; // Variável para controlar a exibição dos editais
  editais: Edital[] = [];

  constructor(
    private servicoEdital: EditalService,
    private router: Router,
    private route: ActivatedRoute,
    private servicoAlerta: AlertaService,
  ){}

  ngOnInit(): void {
    // Carregar todos os editais disponíveis
    this.servicoEdital.get().subscribe({
      next: (resposta: RespostaPaginada<Edital>) => {
        this.editais = resposta.content;
        console.log(resposta.content)
      }
    });
  }

  // Função para alternar a exibição dos editais
  toggleEditais(): void {
    this.mostrarEditais = !this.mostrarEditais;
    this.menuIcon = this.menuIcon.includes('sinal-de-seta-para-baixo-para-navegar.png')
      ? '../../../../assets/img/sinal-de-seta-para-cima-para-navegar.png'
      : '../../../../assets/img/sinal-de-seta-para-baixo-para-navegar.png';

  }

  visualizarEdital(idEdital: number): void {
    // Redireciona para a rota de visualização do edital
    this.router.navigate(['/editais/', idEdital]);
  }
}
