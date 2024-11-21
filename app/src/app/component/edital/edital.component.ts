import { Component } from '@angular/core';
import { IList } from '../I-list';
import { Edital } from '../../model/Edital';
import { CommonModule } from '@angular/common';
import { BarraComandosComponent } from '../barra-comandos/barra-comandos.component';
import { RouterLink } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbPagination, NgbTooltip } from '@ng-bootstrap/ng-bootstrap';
import { TheadOrdenacaoComponent } from '../thead-ordenacao/thead-ordenacao.component';
import { EditalService } from '../../service/edital/edital.service';
import { AlertaService } from '../../service/alerta/alerta.service';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';
import { ETipoAlerta } from '../../model/ETipoAlerta';
import { TheadOrdenacao } from '../thead-ordenacao/thead-ordenacao';
import { EditaldadosFormComponent } from '../editalDados/editaldados-form/editaldados-form.component';

@Component({
  selector: 'app-edital',
  standalone: true,
  imports: [CommonModule,
    RouterLink,
    ReactiveFormsModule,
    BarraComandosComponent,
    NgbTooltip,
    NgbPagination,
    TheadOrdenacaoComponent,
    FormsModule,
    EditaldadosFormComponent],
  templateUrl: './edital.component.html',
  styleUrl: './edital.component.css'
})
export class EditalComponent implements IList<Edital> {

  constructor(
    private servico: EditalService,
    private servicoAlerta: AlertaService
  ) { }
  ngOnInit(): void {
    this.requisicaoPaginada.size = parseInt(localStorage.getItem('tamanhoPagina') || '5');
    this.get();
  }
  registros: Edital[] = Array<Edital>();
  respostaPaginada: RespostaPaginada<Edital> = <RespostaPaginada<Edital>>{};
  requisicaoPaginada: RequisicaoPaginada = new RequisicaoPaginada();
  termoBusca: string | undefined = '';

  colunas: TheadOrdenacao = [
    { campo: 'tituloEdital', descricao: 'Titulo' },
    { campo: 'identificacaoEdital', descricao: 'Identificação' },
    { campo: 'programa', descricao: 'Programa' },
    { campo: 'situacaoEdital', descricao: 'Situação' },
    { campo: 'idEdital', descricao: 'id' },
    { campo: 'dataCriacaoEdital', descricao: 'Data de criação' },
    { campo: 'nomeFormularioEdital', descricao: 'Nome do formulário' },
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
      next: (resposta: RespostaPaginada<Edital>) => {
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
            mensagem: "Edital excluído com sucesso!"
          });
        }
      });
    }
  }

}
