import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { BarraComandosComponent } from '../../barra-comandos/barra-comandos.component';
import { RouterLink } from '@angular/router';
import { NgbPaginationModule, NgbTooltipModule } from '@ng-bootstrap/ng-bootstrap';
import { TheadOrdenacaoComponent } from '../../thead-ordenacao/thead-ordenacao.component';
import { FormsModule } from '@angular/forms';
import { Moeda } from '../../../model/Moeda';
import { IList } from '../../I-list';
import { MoedaService } from '../../../service/moeda/moeda.service';
import { AlertaService } from '../../../service/alerta/alerta.service';
import { RespostaPaginada } from '../../../model/RespostaPaginada';
import { RequisicaoPaginada } from '../../../model/RequisicaoPaginada';
import { TheadOrdenacao } from '../../thead-ordenacao/thead-ordenacao';
import { ETipoAlerta } from '../../../model/ETipoAlerta';

@Component({
  selector: 'app-moeda-list',
  standalone: true,
  imports: [
    CommonModule,
    BarraComandosComponent,
    RouterLink,
    NgbTooltipModule,
    NgbPaginationModule,
    TheadOrdenacaoComponent,
    FormsModule
  ],
  templateUrl: './moeda-list.component.html',
  styleUrl: './moeda-list.component.css'
})
export class MoedaListComponent implements OnInit, IList<Moeda> {

  constructor(
    private servico: MoedaService,
    private servicoAlerta: AlertaService
  ) {}

  ngOnInit(): void {
    this.requisicaoPaginada.size = parseInt(localStorage.getItem('tamanhoPagina') || '5');
    this.get();
  }

  registros: Moeda[] = Array<Moeda>();
  respostaPaginada: RespostaPaginada<Moeda> = <RespostaPaginada<Moeda>>{};
  requisicaoPaginada: RequisicaoPaginada = new RequisicaoPaginada();
  termoBusca: string | undefined = '';

  colunas: TheadOrdenacao = [
    { campo: 'nomeFuncao', descricao: 'Nome da Moeda' },
    { campo: 'ativaMoeda', descricao: 'Ativa' },
    { campo: '', descricao: 'Ações' }
  ];

  mudarPagina(pagina: number): void {
    this.requisicaoPaginada.page = pagina - 1;
    this.get(this.termoBusca);
  }

  ordenar(ordenacao: string[]): void {
    this.requisicaoPaginada.sort = ordenacao;
    this.requisicaoPaginada.page = 0;
    this.get(this.termoBusca);
  }

  mudarTamanhoPagina() {
    localStorage.setItem('tamanhoPagina', this.requisicaoPaginada.size.toString());
    this.get(this.termoBusca);
  }

  get(termoBusca?: string): void {
    this.termoBusca = termoBusca;
    this.servico.get(termoBusca, this.requisicaoPaginada).subscribe({
      next: (resposta: RespostaPaginada<Moeda>) => {
        this.registros = resposta.content;
        this.respostaPaginada = resposta;
      }
    });
  }

  delete(id: number): void {
    if (confirm('Deseja apagar a Moeda?')) {
      this.servico.delete(id).subscribe({
        complete: () => {
          this.get();
          this.servicoAlerta.enviarAlerta({
            tipo: ETipoAlerta.SUCESSO,
            mensagem: 'Moeda excluída com sucesso!'
          });
        }
      });
    }
  }
}

