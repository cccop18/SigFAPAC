import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { BarraComandosComponent } from '../../../barra-comandos/barra-comandos.component';
import { RouterLink } from '@angular/router';
import { NgbPaginationModule, NgbTooltipModule } from '@ng-bootstrap/ng-bootstrap';
import { TheadOrdenacaoComponent } from '../../../thead-ordenacao/thead-ordenacao.component';
import { FormsModule } from '@angular/forms';
import { IList } from '../../../I-list';
import { Programa } from '../../../../model/Programa';
import { RespostaPaginada } from '../../../../model/RespostaPaginada';
import { RequisicaoPaginada } from '../../../../model/RequisicaoPaginada';
import { TheadOrdenacao } from '../../../thead-ordenacao/thead-ordenacao';
import { ProgramaService } from '../../../../service/programa/programa.service';
import { ETipoAlerta } from '../../../../model/ETipoAlerta';
import { AlertaService } from '../../../../service/alerta/alerta.service';

@Component({
  selector: 'app-programa-list',
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
  templateUrl: './programa-list.component.html',
  styleUrl: './programa-list.component.css'
})
export class ProgramaListComponent implements IList<Programa>{

  constructor(
    private servico: ProgramaService,
    private servicoAlerta: AlertaService
  ) {}

  
  ngOnInit(): void {
    this.requisicaoPaginada.size = parseInt(localStorage.getItem('tamanhoPagina') || '5');
    this.get();
  }

  registros: Programa[] = Array<Programa>();
  respostaPaginada: RespostaPaginada<Programa> = <RespostaPaginada<Programa>>{};
  requisicaoPaginada: RequisicaoPaginada = new RequisicaoPaginada();
  termoBusca: string | undefined = '';

  colunas: TheadOrdenacao = [
    { campo: 'idPrograma', descricao: 'Id'},
    { campo: 'nomePrograma', descricao: 'Nome do Programa' },
    { campo: 'ativoPrograma', descricao: 'Status'},
    { campo: '', descricao: 'Ações' },
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
      next: (resposta: RespostaPaginada<Programa>) => {
        this.registros = resposta.content
        this.respostaPaginada = resposta;
      }
    });
  }
  delete(id: number): void {
    if (confirm('Deseja desativar o Programa?')) {
      this.servico.delete(id).subscribe({
        complete: () => {
          this.get();
          this.servicoAlerta.enviarAlerta({
            tipo: ETipoAlerta.SUCESSO,
            mensagem: "Programa desativado com sucesso!"
          });
        }
      });
    }
  }

}
