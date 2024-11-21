import { Component } from '@angular/core';
import { ServicoPropostaService } from '../../../service/servicoProposta/servico-proposta.service';
import { MoedaService } from '../../../service/moeda/moeda.service';
import { OrcamentoPropostaService } from '../../../service/orcamentoProposta/orcamento-proposta.service';
import { EditalService } from '../../../service/edital/edital.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { AlertaService } from '../../../service/alerta/alerta.service';
import { RespostaPaginada } from '../../../model/RespostaPaginada';
import { Moeda } from '../../../model/Moeda';
import { ServicoProposta } from '../../../model/ServicoProposta';
import { OrcamentoProposta } from '../../../model/OrcamentoProposta';
import { Edital } from '../../../model/Edital';
import { ETipoAlerta } from '../../../model/ETipoAlerta';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MenuPropostaComponent } from '../../menu-proposta/menu-proposta/menu-proposta.component';

@Component({
  selector: 'app-servico-proposta-form',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule, MenuPropostaComponent],
  templateUrl: './servico-proposta-form.component.html',
  styleUrl: './servico-proposta-form.component.css'
})
export class ServicoPropostaFormComponent {
  constructor(
    private servico: ServicoPropostaService,
    private servicoMoeda: MoedaService,
    private servicoOrcamento: OrcamentoPropostaService,
    private servicoEdital: EditalService,
    private router: Router,
    private route: ActivatedRoute,
    private servicoAlerta: AlertaService
  ){}

  ngOnInit(): void {
    // Carregar diárias
    this.servico.get().subscribe({
      next: (resposta: RespostaPaginada<ServicoProposta>) => {
        this.servicoProposta = resposta.content;
      }
    });
  
    // Carregar moedas
    this.servicoMoeda.get().subscribe({
      next: (resposta: RespostaPaginada<Moeda>) => {
        this.moedas = resposta.content;
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
          this.formServicoProposta.patchValue({
            orcamentoProposta: resposta
          });
        }
      });
    }
  }

  formServicoProposta = new FormGroup({
    idServicoProposta: new FormControl<number | null>(null),
    tipoServicoProposta: new FormControl<string | null>(null),
    especificacaoServicoProposta: new FormControl <string | null>(null),
    custoServicoProposta:  new FormControl<string | null>(null),
    dataServicoProposta: new FormControl< string | null>(null),
    orcamentoProposta: new FormControl <OrcamentoProposta | null>(null),
    moeda: new FormControl <Moeda | null>(null)
  });



  registro: ServicoProposta = <ServicoProposta>{};
  servicoProposta: ServicoProposta[] = [];
  moedas: Moeda[] = [];
  orcamento: OrcamentoProposta[] = [];
  editais: Edital[] = [];



  save(): void {
    this.registro = Object.assign(this.registro, this.formServicoProposta.value);
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
    if (confirm('Deseja apagar o Serviço?')) {
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
    this.registro = Object.assign(this.registro, this.formServicoProposta.value);
    this.router.navigate(['/propostamaterialperm/form'], {
      queryParams: { 
        orcamentoId: this.registro.orcamentoProposta.idOrcamentoProposta,   // Supondo que idProposta esteja no objeto retornado
      }
    })};

}
