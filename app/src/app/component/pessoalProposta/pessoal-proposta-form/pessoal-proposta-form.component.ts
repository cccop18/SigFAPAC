import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { MenuPropostaComponent } from '../../menu-proposta/menu-proposta/menu-proposta.component';
import { PessoalPropostaService } from '../../../service/pessoalProposta/pessoal-proposta.service';
import { OrcamentoPropostaService } from '../../../service/orcamentoProposta/orcamento-proposta.service';
import { EditalService } from '../../../service/edital/edital.service';
import { AlertaService } from '../../../service/alerta/alerta.service';
import { RespostaPaginada } from '../../../model/RespostaPaginada';
import { PessoalProposta } from '../../../model/PessoalProposta';
import { OrcamentoProposta } from '../../../model/OrcamentoProposta';
import { Edital } from '../../../model/Edital';
import { ETipoAlerta } from '../../../model/ETipoAlerta';

@Component({
  selector: 'app-pessoal-proposta-form',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule, MenuPropostaComponent],
  templateUrl: './pessoal-proposta-form.component.html',
  styleUrl: './pessoal-proposta-form.component.css'
})
export class PessoalPropostaFormComponent {
  constructor(
    private servico: PessoalPropostaService,
    private servicoOrcamento: OrcamentoPropostaService,
    private servicoEdital: EditalService,
    private router: Router,
    private route: ActivatedRoute,
    private servicoAlerta: AlertaService,
    private rout: ActivatedRoute
  ){}

  ngOnInit(): void {

    this.periodos = Array.from({ length: 36 }, (v, k) => k + 1);
    this.meses = Array.from({ length: 36 }, (v, k) => `${k + 1} ${k + 1 === 1 ? 'mês' : 'meses'}`);
    // Carregar diárias
    this.servico.get().subscribe({
      next: (resposta: RespostaPaginada<PessoalProposta>) => {
        this.pessoalProposta = resposta.content;
      }
    });
  
    // Carregar orçamentos
    this.servicoOrcamento.get().subscribe({
      next: (resposta: RespostaPaginada<OrcamentoProposta>) => {
        this.orcamento = resposta.content;
      }
    });

    this.servicoEdital.get().subscribe({
      next: (respota: RespostaPaginada<Edital>) => {
        this.editais = respota.content;
      }
    })

  
    // Pegar id do orçamento da query e carregar orçamento
    const orcamentoId = this.route.snapshot.queryParamMap.get('orcamentoId');
    if (orcamentoId) {
      this.servicoOrcamento.getById(+orcamentoId).subscribe({
        next: (resposta: OrcamentoProposta) => {
          this.formPessoalProposta.patchValue({
            orcamentoProposta: resposta
          });
        }
      });
    }
  }

  formPessoalProposta = new FormGroup({
    idPessoalProposta: new FormControl<number | null>(null),
    funcaoPessoalProposta: new FormControl<string | null>(null),
    formacaoPessoalProposta: new FormControl <string | null>(null),
    perfilPessoalProposta:  new FormControl<string | null>(null),
    periodoPessoalProposta: new FormControl<number | null>(null),
    inicioPessoalPropostacol: new FormControl< string | null>(null),
    horaSemanaPessoalProposta: new FormControl <number | null>(null),
    custoHoraPessoalProposta: new FormControl<number | null>(null),
    custoTotalPessoalPropostacol: new FormControl<number | null>(null),
    dataPessoalProposta: new FormControl<string | null>(null),
    orcamentoProposta: new FormControl<OrcamentoProposta | null>(null),
  });



  registro: PessoalProposta = <PessoalProposta>{};
  pessoalProposta: PessoalProposta[] = [];
  orcamento: OrcamentoProposta[] = [];
  editais: Edital[] = [];
  periodos: number[] = [];
  meses: string[] = [];


  save(): void {
    this.registro = Object.assign(this.registro, this.formPessoalProposta.value);
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
    if (confirm('Deseja apagar a Diaria?')) {
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
    this.registro = Object.assign(this.registro, this.formPessoalProposta.value);
    this.router.navigate(['/propostaencargo/form'], {
      queryParams: { 
        orcamentoId: this.registro.orcamentoProposta.idOrcamentoProposta,   // Supondo que idProposta esteja no objeto retornado
      }
    })};



}
