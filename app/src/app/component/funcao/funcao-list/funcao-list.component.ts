import { Component, OnInit } from '@angular/core';
import { IList } from '../../I-list';
import { Funcao } from '../../../model/Funcao';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { NgbPaginationModule, NgbTooltipModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { FuncaoService } from '../../../service/funcao/funcao.service';
import { BarraComandosComponent } from '../../barra-comandos/barra-comandos.component';
import { TheadOrdenacaoComponent } from '../../thead-ordenacao/thead-ordenacao.component';
import { RespostaPaginada } from '../../../model/RespostaPaginada';
import { RequisicaoPaginada } from '../../../model/RequisicaoPaginada';
import { TheadOrdenacao } from '../../thead-ordenacao/thead-ordenacao';
import { ETipoAlerta } from '../../../model/ETipoAlerta';
import { AlertaService } from '../../../service/alerta/alerta.service';

@Component({
  selector: 'app-funcao-list',
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
  templateUrl: './funcao-list.component.html',
  styleUrls: ['./funcao-list.component.css']
})
export class FuncaoListComponent implements OnInit, IList<Funcao> {

  constructor(
    private servico: FuncaoService,
    private servicoAlerta: AlertaService
  ) {}

  ngOnInit(): void {
    this.requisicaoPaginada.size = parseInt(localStorage.getItem('tamanhoPagina') || '5');
    this.get();
  }

  registros: Funcao[] = Array<Funcao>();
  respostaPaginada: RespostaPaginada<Funcao> = <RespostaPaginada<Funcao>>{};
  requisicaoPaginada: RequisicaoPaginada = new RequisicaoPaginada();
  termoBusca: string | undefined = '';

  colunas: TheadOrdenacao = [
    { campo: 'idFuncao', descricao: 'Id' },
    { campo: 'nomeFuncao', descricao: 'Nome da Função' },
    { campo: 'ativoFuncao', descricao: 'Ativa' },
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
      next: (resposta: RespostaPaginada<Funcao>) => {
        this.registros = resposta.content;
        this.respostaPaginada = resposta;
      }
    });
  }

  delete(id: number): void {
    if (confirm('Deseja apagar a Função?')) {
      this.servico.delete(id).subscribe({
        complete: () => {
          this.get();
          this.servicoAlerta.enviarAlerta({
            tipo: ETipoAlerta.SUCESSO,
            mensagem: 'Função excluída com sucesso!'
          });
        }
      });
    }
  }
}
