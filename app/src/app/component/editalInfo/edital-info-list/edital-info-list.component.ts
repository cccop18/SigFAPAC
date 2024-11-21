import { Component } from '@angular/core';
import { EditalService } from '../../../service/edital/edital.service';
import { AlertaService } from '../../../service/alerta/alerta.service';
import { Edital } from '../../../model/Edital';
import { RespostaPaginada } from '../../../model/RespostaPaginada';
import { Retificacoes } from '../../../model/Retificacoes';
import { RetificacoesService } from '../../../service/retificacoes/retificacoes.service';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { BarraComandosComponent } from '../../barra-comandos/barra-comandos.component';
import { EditalDados } from '../../../model/EditalDados';
import { EditalDadosService } from '../../../service/editalDados/edital-dados.service';
import { RequisicaoPaginada } from '../../../model/RequisicaoPaginada';
import { NgbPaginationModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { Programa } from '../../../model/Programa';
import { ProgramaService } from '../../../service/programa/programa.service';
import { environment } from '../../../../environments/environment';

@Component({
  selector: 'app-edital-info-list',
  standalone: true,
  imports: [CommonModule, RouterLink, BarraComandosComponent, NgbPaginationModule, FormsModule],
  templateUrl: './edital-info-list.component.html',
  styleUrl: './edital-info-list.component.css'
})
export class EditalInfoListComponent {
  // urls para download e visualização de arquivos
  urlView = environment.API_URL + '/editalDados/view/';
  urlDownload = environment.API_URL + '/editalDados/download/';

  urlViewRetificacao = environment.API_URL + '/retificacao/view/';
  urlDownloadRetificacao = environment.API_URL + '/retificacao/download/';

  constructor(
    private servicoEdital: EditalDadosService,
    private servicoRetificacao: RetificacoesService,
    private servicoPrograma: ProgramaService,
    private route: ActivatedRoute,
    private servicoAlerta: AlertaService,

  ) { }

  ngOnInit(): void {
    const id = this.route.snapshot.queryParamMap.get('id');
    this.getEdital(Number(id));
    this.getRetificacao();

    this.servicoPrograma.get().subscribe({
      next: (resposta: RespostaPaginada<Programa>) => {
        this.programas = resposta.content;
      }
    });
  }

  registroEdital: EditalDados[] = Array<EditalDados>();
  respostaPaginada: RespostaPaginada<EditalDados> = <RespostaPaginada<EditalDados>>{};

  registroRetificacao: Retificacoes[] = Array<Retificacoes>();
  respostaRetificaoPaginada: RespostaPaginada<Retificacoes> = <RespostaPaginada<Retificacoes>>{};
  termoBusca: string | undefined = '';
  requisicaoPaginada: RequisicaoPaginada = new RequisicaoPaginada();
  programas: Programa[] = [];

  getEdital(id: number): void {
    this.servicoEdital.getById(+id).subscribe({
      next: (resposta: EditalDados) => {
        this.registroEdital = [resposta];
      }
    });
  }
  getRetificacao(termoBusca?: string): void {
    this.termoBusca = termoBusca;
    this.servicoRetificacao.get(termoBusca, this.requisicaoPaginada).subscribe({
      next: (resposta: RespostaPaginada<Retificacoes>) => {
        this.registroRetificacao = resposta.content;
        this.respostaRetificaoPaginada = resposta;
      }
    });
  }
  mudarPagina(pagina: number): void {
    this.requisicaoPaginada.page = pagina - 1;
    this.getRetificacao(this.termoBusca);
  }
  ordenar(ordenacao: string[]): void {
    this.requisicaoPaginada.sort = ordenacao;
    this.requisicaoPaginada.page = 0;
    this.getRetificacao(this.termoBusca);
  }
  mudarTamanhoPagina() {
    localStorage.setItem('tamanhoPagina', this.requisicaoPaginada.size.toString());
    this.getRetificacao(this.termoBusca);
  }
  getProgramaNome(idPrograma: number| null): string {
    
    // Mudança para usar o campo correto: idUnidadeInstituicao
    const programa = this.programas.find(uni => uni.idPrograma === idPrograma);
    
    console.log('Unidade encontrada:', programa);
    return programa ? programa.nomePrograma : '==';
  }
}
