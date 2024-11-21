import { Component } from '@angular/core';
import { IList } from '../../../I-list';
import { Diarias } from '../../../../model/Diarias';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { NgbPaginationModule, NgbTooltipModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { DiariaService } from '../../../../service/diaria/diaria.service';
import { BarraComandosComponent } from '../../../barra-comandos/barra-comandos.component';
import { TheadOrdenacaoComponent } from '../../../thead-ordenacao/thead-ordenacao.component';
import { RespostaPaginada } from '../../../../model/RespostaPaginada';
import { RequisicaoPaginada } from '../../../../model/RequisicaoPaginada';
import { TheadOrdenacao } from '../../../thead-ordenacao/thead-ordenacao';
import { ETipoAlerta } from '../../../../model/ETipoAlerta';
import { AlertaService } from '../../../../service/alerta/alerta.service';

@Component({
  selector: 'app-diaria-list',
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
  templateUrl: './diaria-list.component.html',
  styleUrl: './diaria-list.component.css'
})
export class DiariaListComponent implements IList<Diarias> {

  constructor(
    private servico: DiariaService,
    private servicoAlerta: AlertaService
  ) {}

  
  ngOnInit(): void {
    this.requisicaoPaginada.size = parseInt(localStorage.getItem('tamanhoPagina') || '5');
    this.get();
  }

  registros: Diarias[] = Array<Diarias>();
  respostaPaginada: RespostaPaginada<Diarias> = <RespostaPaginada<Diarias>>{};
  requisicaoPaginada: RequisicaoPaginada = new RequisicaoPaginada();
  termoBusca: string | undefined = '';

  colunas: TheadOrdenacao = [
    { campo: 'idDiaria', descricao: 'Id'},
    { campo: 'tipoDiaria', descricao: 'Tipo de Viagem' },
    { campo: 'nivelAcademicoDiaria', descricao: 'Nivel Academico' },
    { campo: 'valorDiaria', descricao: 'Valor em R$' },
    { campo: 'ativo', descricao: 'Ativa' },
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
      next: (resposta: RespostaPaginada<Diarias>) => {
        this.registros = resposta.content
        this.respostaPaginada = resposta;
      }
    });
  }

  delete(id: number): void {
    if (confirm('Deseja apagar a Diaria?')) {
      this.servico.delete(id).subscribe({
        complete: () => {
          this.get();
          this.servicoAlerta.enviarAlerta({
            tipo: ETipoAlerta.SUCESSO,
            mensagem: "Agendamento cancelado com sucesso!"
          });
        }
      });
    }
  }

}
