import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { MenuPropostaComponent } from '../../menu-proposta/menu-proposta/menu-proposta.component';
import { MembrosService } from '../../../service/membros/membros.service';
import { EditalService } from '../../../service/edital/edital.service';
import { PesquisadorService } from '../../../service/pesquisador.service';
import { AlertaService } from '../../../service/alerta/alerta.service';
import { Edital } from '../../../model/Edital';
import { RespostaPaginada } from '../../../model/RespostaPaginada';
import { Membros } from '../../../model/Membros';
import { Pesquisador } from '../../../model/Pesquisador';
import { Proposta } from '../../../model/Proposta';
import { Funcao } from '../../../model/Funcao';
import { PropostaService } from '../../../service/proposta/proposta.service';
import { FuncaoService } from '../../../service/funcao/funcao.service';
import { ETipoAlerta } from '../../../model/ETipoAlerta';

@Component({
  selector: 'app-proposta-membros-form',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule, MenuPropostaComponent],
  templateUrl: './proposta-membros-form.component.html',
  styleUrl: './proposta-membros-form.component.css'
})
export class PropostaMembrosFormComponent {
  constructor(
    private servico: MembrosService,
    private servicoPesquisador: PesquisadorService,
    private servicoProposta: PropostaService,
    private servicoFuncao: FuncaoService,
    private router: Router,
    private route: ActivatedRoute,
    private servicoAlerta: AlertaService,
    private rout: ActivatedRoute
  ){}


  ngOnInit(): void {
    // Carregar diárias
    this.servico.get().subscribe({
      next: (resposta: RespostaPaginada<Membros>) => {
        this.membros = resposta.content;
      }
    });
  
  
    // Carregar orçamentos
    this.servicoPesquisador.get().subscribe({
      next: (resposta: RespostaPaginada<Pesquisador>) => {
        this.pesquisador = resposta.content;
      }
    });

    this.servicoProposta.get().subscribe({
      next: (respota: RespostaPaginada<Proposta>) => {
        this.propostas = respota.content;
      }
    })

    this.servicoFuncao.get().subscribe({
      next: (respota: RespostaPaginada<Funcao>) => {
        this.funcoes = respota.content;
      }
    })

  
    // Pegar id do orçamento da query e carregar orçamento
    const IdProposta = this.route.snapshot.queryParamMap.get('id');
    if (IdProposta) {
      this.servicoProposta.getById(+IdProposta).subscribe({
        next: (resposta: Proposta) => {
          this.formMembros.patchValue({
            proposta: resposta
          });
        }
      });
    }

     // Carregar os pesquisadores ao iniciar
     this.pesquisarPesquisadores(); // Carrega todos os pesquisadores inicialmente

     // Executar busca ao alterar o campo de busca
     this.pesquisadorControl.valueChanges.subscribe((termo: string) => {
       this.pesquisarPesquisadores(termo);
     });
  }

  formMembros = new FormGroup({
    idMembros: new FormControl<number | null>(null),
    proposta: new FormControl<Proposta | null>(null),
    funcao: new FormControl <Funcao| null>(null),
    pesquisador:  new FormControl<Pesquisador | null>(null, Validators.pattern(/^[A-Za-zÀ-ÖØ-öø-ÿ\s]{3,}$/)),
  });


  registro: Membros = <Membros>{};
  membros: Membros[] = [];
  pesquisador: Pesquisador[] = [];
  propostas: Proposta[] = [];
  funcoes: Funcao[] = [];
  pesquisadorNome: string = '';
  pesquisadores: Pesquisador[] = [];
  pesquisadorSelecionado: Pesquisador | null = null; // Pesquisador selecionado
  pesquisadorControl: FormControl = new FormControl();

   // Método de busca
   pesquisarPesquisadores(termoBusca?: string): void {
    this.servicoPesquisador.get(termoBusca).subscribe({
      next: (resposta: RespostaPaginada<Pesquisador>) => {
        this.pesquisador = resposta.content;
      },
      error: (err) => {
        this.servicoAlerta.enviarAlerta({
          tipo: ETipoAlerta.ERRO,
          mensagem: "Erro ao buscar pesquisadores."
        });
      }
    });
  }

  // Método para selecionar um pesquisador e enviar o nome para o input
  selecionarPesquisador(pesquisador: Pesquisador): void {
    this.pesquisadorSelecionado = pesquisador;
    this.formMembros.patchValue({
      pesquisador: pesquisador
    });
    this.pesquisadorControl.setValue(pesquisador.nomeCompletoPesquisador); // Exibe o nome selecionado no input
  }


  save(): void {
    this.registro = Object.assign(this.registro, this.formMembros.value);
    console.log(this.registro);
    this.servico.save(this.registro).subscribe({
      complete: () => {
        // Recarregar a página toda
        window.location.reload();
        this.servicoAlerta.enviarAlerta({
          tipo: ETipoAlerta.SUCESSO,
          mensagem: "Operação realizada com sucesso!"
        });
      }
    });
  }

  delete(id: number): void {
    if (confirm('Deseja apagar o Material?')) {
      this.servico.delete(id).subscribe({
        complete: () => {
          window.location.reload();
          this.servicoAlerta.enviarAlerta({
            tipo: ETipoAlerta.SUCESSO,
            mensagem: "Edital excluído com sucesso!"
          });
        }
      });
    }
  }

  redirect(): void {
    this.registro = Object.assign(this.registro, this.formMembros.value);
    this.router.navigate(['/propostapassagem/form'], {
      queryParams: { 
        id: this.registro.proposta.idProposta,   // Supondo que idProposta esteja no objeto retornado
      }
    })};


}
