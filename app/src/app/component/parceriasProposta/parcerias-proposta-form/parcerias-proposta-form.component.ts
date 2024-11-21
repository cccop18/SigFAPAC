import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { MenuPropostaComponent } from '../../menu-proposta/menu-proposta/menu-proposta.component';
import { ParceriasPropostaService } from '../../../service/parceriasProposta/parcerias-proposta.service';
import { MoedaService } from '../../../service/moeda/moeda.service';
import { OrcamentoPropostaService } from '../../../service/orcamentoProposta/orcamento-proposta.service';
import { EditalService } from '../../../service/edital/edital.service';
import { AlertaService } from '../../../service/alerta/alerta.service';
import { RespostaPaginada } from '../../../model/RespostaPaginada';
import { ParceriasProposta } from '../../../model/ParceriasProposta';
import { Moeda } from '../../../model/Moeda';
import { OrcamentoProposta } from '../../../model/OrcamentoProposta';
import { Edital } from '../../../model/Edital';
import { ETipoAlerta } from '../../../model/ETipoAlerta';

@Component({
  selector: 'app-parcerias-proposta-form',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule, MenuPropostaComponent],
  templateUrl: './parcerias-proposta-form.component.html',
  styleUrl: './parcerias-proposta-form.component.css'
})
export class ParceriasPropostaFormComponent {

  constructor(
    private servico: ParceriasPropostaService,
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
      next: (resposta: RespostaPaginada<ParceriasProposta>) => {
        this.parceriasProposta = resposta.content;
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
          this.formParceriasProposta.patchValue({
            orcamentoProposta: resposta
          });
        }
      });
    }
  }

  formParceriasProposta = new FormGroup({
    idParceriasProposta: new FormControl<number | null>(null),
    entidadeParceriaProposta: new FormControl<string | null>(null),
    tipoParceriaProposta: new FormControl <string | null>(null),
    descricaoParceriaProposta:  new FormControl<string | null>(null),
    orcamentoProposta: new FormControl <OrcamentoProposta | null>(null),
    moeda: new FormControl <Moeda | null>(null)
  });

  registro: ParceriasProposta = <ParceriasProposta>{};
  parceriasProposta: ParceriasProposta[] = [];
  moedas: Moeda[] = [];
  orcamento: OrcamentoProposta[] = [];
  editais: Edital[] = [];

  save(): void {
    this.registro = Object.assign(this.registro, this.formParceriasProposta.value);
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
    this.registro = Object.assign(this.registro, this.formParceriasProposta.value);
    this.router.navigate(['/'], {
      queryParams: { 
        orcamentoId: this.registro.orcamentoProposta.idOrcamentoProposta,   // Supondo que idProposta esteja no objeto retornado
      }
    })};

}
