import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { MenuPropostaComponent } from '../../menu-proposta/menu-proposta/menu-proposta.component';
import { AtividadePropostaService } from '../../../service/atividadeProposta/atividade-proposta.service';
import { MembrosService } from '../../../service/membros/membros.service';
import { PropostaService } from '../../../service/proposta/proposta.service';
import { AlertaService } from '../../../service/alerta/alerta.service';
import { AtividadeProposta } from '../../../model/AtividadeProposta';
import { RespostaPaginada } from '../../../model/RespostaPaginada';
import { Membros } from '../../../model/Membros';
import { Proposta } from '../../../model/Proposta';
import { ETipoAlerta } from '../../../model/ETipoAlerta';

@Component({
  selector: 'app-atividade-proposta-form',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule, MenuPropostaComponent],
  templateUrl: './atividade-proposta-form.component.html',
  styleUrl: './atividade-proposta-form.component.css'
})
export class AtividadePropostaFormComponent {

  constructor(
    private servico: AtividadePropostaService,
    private servicoMermbros: MembrosService,
    private servicoProposta: PropostaService,
    private router: Router,
    private route: ActivatedRoute,
    private servicoAlerta: AlertaService,
    private rout: ActivatedRoute
  ){}

  ngOnInit(): void {

    // Carregar a lista de estados
    this.servico.get().subscribe({
      next: (resposta: RespostaPaginada<AtividadeProposta>) => {
        this.atividades = resposta.content;
      }
    });

  
    // Carregar membros
    this.servicoMermbros.get().subscribe({
      next: (resposta: RespostaPaginada<Membros>) => {
        this.membros = resposta.content;
      }
    });

    this.servicoProposta.get().subscribe({
      next: (respota: RespostaPaginada<Proposta>) => {
        this.propostas = respota.content;
      }
    })

  // Obtenha o id da proposta da query
  const idProposta = this.route.snapshot.queryParamMap.get('id');
  
  if (idProposta) {
    const id = +idProposta;

    // Carregar proposta associada para preencher o formulário
    this.servicoProposta.getById(id).subscribe({
      next: (resposta: Proposta) => {
        this.formAtividadeProposta.patchValue({
          proposta: resposta
        });
      }
    });

    // Carregar todos os membros e filtrar por proposta localmente
    this.servicoMermbros.get().subscribe({
      next: (resposta: RespostaPaginada<Membros>) => {
        this.membros = resposta.content.filter(membro => membro.proposta.idProposta === id);
      }
    });
  }
  }

  registro: AtividadeProposta = <AtividadeProposta>{};
  atividades: AtividadeProposta[] = [];
  membros: Membros[] = [];
  propostas: Proposta[] = [];


  formAtividadeProposta = new FormGroup({
    idAtividadeProposta: new FormControl<number | null>(null),
    descricaoProposta: new FormControl<string | null>(null, Validators.pattern(/^[A-Za-zÀ-ÖØ-öø-ÿ\s]{3,}$/)),
    valorAtividdeProposta: new FormControl<number | null>(null, Validators.required),
    mesInicioAtividadeProposta: new FormControl<string | null>(null, Validators.required),
    duracaoAtividadeProposta: new FormControl <string | null>(null, Validators.required),
    proposta: new FormControl<Proposta | null>(null),
    membros: new FormControl<Membros | null>(null, Validators.required)
  })

  get form() {
    return this.formAtividadeProposta.controls;
  }



  save(): void {
    this.registro = Object.assign(this.registro, this.formAtividadeProposta.value);
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
    if (confirm('Deseja apagar a Atividade?')) {
      this.servico.delete(id).subscribe({
        complete: () => {
          window.location.reload();
          this.servicoAlerta.enviarAlerta({
            tipo: ETipoAlerta.SUCESSO,
            mensagem: "Atividade excluída com sucesso!"
          });
        }
      });
    }
  }

  redirect(): void {
    this.registro = Object.assign(this.registro, this.formAtividadeProposta.value);
    this.router.navigate(['/propostadiaria/form'], {
      queryParams: { 
        id: this.registro.proposta.idProposta,   // Supondo que idProposta esteja no objeto retornado
      }
    })};




}
