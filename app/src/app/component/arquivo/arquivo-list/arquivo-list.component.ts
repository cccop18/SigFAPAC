import { Component } from '@angular/core';
import { ArquivoService } from '../../../service/arquivo/arquivo.service';
import { IList } from '../../I-list';
import { Arquivo } from '../../../model/Arquivo';
import { RespostaPaginada } from '../../../model/RespostaPaginada';
import { RequisicaoPaginada } from '../../../model/RequisicaoPaginada';
import { TheadOrdenacao } from '../../thead-ordenacao/thead-ordenacao';
import { ETipoAlerta } from '../../../model/ETipoAlerta';
import { CommonModule } from '@angular/common';
import { BarraComandosComponent } from '../../barra-comandos/barra-comandos.component';
import { RouterLink } from '@angular/router';
import { NgbPagination, NgbTooltip } from '@ng-bootstrap/ng-bootstrap';
import { TheadOrdenacaoComponent } from '../../thead-ordenacao/thead-ordenacao.component';
import { FormsModule } from '@angular/forms';
import { environment } from '../../../../environments/environment';
import { AlertaService } from '../../../service/alerta/alerta.service';

@Component({
  selector: 'app-arquivo-list',
  standalone: true,
  imports: [CommonModule, 
            BarraComandosComponent, 
            RouterLink, 
            NgbTooltip, 
            NgbPagination, 
            TheadOrdenacaoComponent, 
            FormsModule],
  templateUrl: './arquivo-list.component.html',
  styleUrl: './arquivo-list.component.css'
})
export class ArquivoListComponent implements IList<Arquivo> {

  constructor(
    private servico: ArquivoService,
    private servicoAlerta: AlertaService
  ) { }


  ngOnInit(): void {
    this.requisicaoPaginada.size = parseInt(localStorage.getItem('tamanhoPagina') || '5');
    this.get();
  }

  registros: Arquivo[] = Array<Arquivo>();
  respostaPaginada: RespostaPaginada<Arquivo> = <RespostaPaginada<Arquivo>>{};
  requisicaoPaginada: RequisicaoPaginada = new RequisicaoPaginada();
  termoBusca: string | undefined = '';
  
  urlView = environment.API_URL + '/arquivo/view/';       // URL para visualizar arquivos
  urlDownload = environment.API_URL + '/arquivo/download/'; // URL para download de arquivos

  colunas: TheadOrdenacao = [
    { campo: 'idArquivo', descricao: 'Id' },
    { campo: 'nomeArquivo', descricao: 'Nome' },
    { campo: 'fileArquivo', descricao: 'Arquivo' },
    { campo: 'ativoArquivo', descricao: 'Status' },
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
      next: (resposta: RespostaPaginada<Arquivo>) => {
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
