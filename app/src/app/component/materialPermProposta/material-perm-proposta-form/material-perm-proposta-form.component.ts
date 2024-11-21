import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { MenuPropostaComponent } from '../../menu-proposta/menu-proposta/menu-proposta.component';
import { MaterialPermProposta } from '../../../model/MaterialPermProposta';
import { MaterialPermPropostaService } from '../../../service/materialPermProposta/material-perm-proposta.service';
import { MoedaService } from '../../../service/moeda/moeda.service';
import { OrcamentoPropostaService } from '../../../service/orcamentoProposta/orcamento-proposta.service';
import { EditalService } from '../../../service/edital/edital.service';
import { AlertaService } from '../../../service/alerta/alerta.service';
import { RespostaPaginada } from '../../../model/RespostaPaginada';
import { Moeda } from '../../../model/Moeda';
import { OrcamentoProposta } from '../../../model/OrcamentoProposta';
import { Edital } from '../../../model/Edital';
import { ETipoAlerta } from '../../../model/ETipoAlerta';

@Component({
  selector: 'app-material-perm-proposta-form',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule, MenuPropostaComponent],
  templateUrl: './material-perm-proposta-form.component.html',
  styleUrl: './material-perm-proposta-form.component.css'
})
export class MaterialPermPropostaFormComponent {

  constructor(
    private servico: MaterialPermPropostaService,
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
      next: (resposta: RespostaPaginada<MaterialPermProposta>) => {
        this.materialPermProposta = resposta.content;
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
          this.formMaterialPermProposta.patchValue({
            orcamentoProposta: resposta
          });
        }
      });
    }
  }

  formMaterialPermProposta = new FormGroup({
    idMaterialPermProposta: new FormControl<number | null>(null),
    especificacaoMaterialPermProposta: new FormControl<string | null>(null),
    tipoMaterialPermProposta: new FormControl <string | null>(null),
    quantidadeMaterialPermProposta:  new FormControl<number | null>(null),
    custoMaterialPermProposta: new FormControl<number | null>(null),
    dataMaterialPermProposta: new FormControl< string | null>(null),
    orcamentoProposta: new FormControl <OrcamentoProposta | null>(null),
    moeda: new FormControl <Moeda | null>(null)
  });



  registro: MaterialPermProposta = <MaterialPermProposta>{};
  materialPermProposta: MaterialPermProposta[] = [];
  moedas: Moeda[] = [];
  orcamento: OrcamentoProposta[] = [];
  editais: Edital[] = [];

  save(): void {
    this.registro = Object.assign(this.registro, this.formMaterialPermProposta.value);
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


}
