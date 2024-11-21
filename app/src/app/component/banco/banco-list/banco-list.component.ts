import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { NgbPaginationModule } from '@ng-bootstrap/ng-bootstrap';
import { TheadOrdenacaoComponent } from '../../thead-ordenacao/thead-ordenacao.component';
import { IList } from '../../I-list';
import { Banco } from '../../../model/Banco';
import { BancoService } from '../../../service/banco.service';
import { RespostaPaginada } from '../../../model/RespostaPaginada';
import { RequisicaoPaginada } from '../../../model/RequisicaoPaginada';
import { TheadOrdenacao } from '../../thead-ordenacao/thead-ordenacao';
import { ETipoAlerta } from '../../../model/ETipoAlerta';
import { BarraComandosComponent } from '../../barra-comandos/barra-comandos.component';
import { AlertaService } from '../../../service/alerta/alerta.service';

@Component({
  selector: 'app-banco-list',
  standalone: true,
  imports: [CommonModule, RouterLink, NgbPaginationModule, FormsModule, TheadOrdenacaoComponent, BarraComandosComponent],
  templateUrl: './banco-list.component.html',
  styleUrl: './banco-list.component.css'
})
export class BancoListComponent implements IList<Banco>{
  constructor(
    private servico: BancoService,
    private servicoAlerta: AlertaService
  ) {}

  ngOnInit(): void {
    this.requisicaoPaginada.size = parseInt(localStorage.getItem('tamanhoPagina') || '5');
    this.get();
  }

  registros: Banco[] = [];
  respostaPaginada: RespostaPaginada<Banco> = <RespostaPaginada<Banco>>{};
  requisicaoPaginada: RequisicaoPaginada = new RequisicaoPaginada();
  termoBusca: string | undefined = '';

  colunas: TheadOrdenacao = [
    { campo: 'idBanco', descricao: 'ID' },
    { campo: 'numeroBanco', descricao: 'Número' },
    { campo: 'nomeBanco', descricao: 'Nome' },
    { campo: 'ativoBanco', descricao: 'Situação' },
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

  get(termoBusca?: string | undefined): void {
    this.termoBusca = termoBusca;
    this.servico.get(termoBusca, this.requisicaoPaginada).subscribe({
      next: (resposta: RespostaPaginada<Banco>) => {
        this.registros = resposta.content
        this.respostaPaginada = resposta;
      }
    });
  }

  delete(id: number): void {
    if (confirm('Deseja desativar um banco?')) {
      this.servico.delete(id).subscribe({
        complete: () => {
          this.get();
          this.servicoAlerta.enviarAlerta({
            tipo: ETipoAlerta.SUCESSO,
            mensagem: "Banco desativado com sucesso!"
          });
        }
      });
    }
  }

}
