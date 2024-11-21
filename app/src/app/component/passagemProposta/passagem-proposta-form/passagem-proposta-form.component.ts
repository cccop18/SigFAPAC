import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { MenuPropostaComponent } from '../../menu-proposta/menu-proposta/menu-proposta.component';
import { PassagemPropostaService } from '../../../service/passagemProposta/passagem-proposta.service';
import { MoedaService } from '../../../service/moeda/moeda.service';
import { OrcamentoPropostaService } from '../../../service/orcamentoProposta/orcamento-proposta.service';
import { EditalService } from '../../../service/edital/edital.service';
import { AlertaService } from '../../../service/alerta/alerta.service';
import { OrcamentoProposta } from '../../../model/OrcamentoProposta';
import { RespostaPaginada } from '../../../model/RespostaPaginada';
import { PassagemProposta } from '../../../model/PassagemProposta';
import { Moeda } from '../../../model/Moeda';
import { Edital } from '../../../model/Edital';
import { ETipoAlerta } from '../../../model/ETipoAlerta';

@Component({
  selector: 'app-passagem-proposta-form',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule, MenuPropostaComponent],
  templateUrl: './passagem-proposta-form.component.html',
  styleUrl: './passagem-proposta-form.component.css'
})
export class PassagemPropostaFormComponent {

  mostrarCamposLocalidade: boolean = false;

  constructor(
    private servico: PassagemPropostaService,
    private servicoMoeda: MoedaService,
    private servicoOrcamento: OrcamentoPropostaService,
    private servicoEdital: EditalService,
    private router: Router,
    private route: ActivatedRoute,
    private servicoAlerta: AlertaService,
    private rout: ActivatedRoute
  ){}

  ngOnInit(): void {
    // Carregar diárias
    this.servico.get().subscribe({
      next: (resposta: RespostaPaginada<PassagemProposta>) => {
        this.passagemProposta = resposta.content;
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
          this.formPassagemProposta.patchValue({
            orcamentoProposta: resposta
          });
        }
      });
    }
  }

  formPassagemProposta = new FormGroup({
    idPassagemProposta: new FormControl<number | null>(null),
    tipoPassagemProposta: new FormControl<string | null>(null),
    origemPassagemProposta: new FormControl <string | null>(null),
    destinoPassagemProposta:  new FormControl<string | null>(null),
    idaVoltaPassagemProposta: new FormControl<boolean | null>(null),
    quantidadePassagemProposta: new FormControl< number | null>(null),
    custoUnitarioPassagemProposta: new FormControl <number | null>(null),
    dataPassagemProposta: new FormControl<string | null>(null),
    orcamentoProposta: new FormControl<OrcamentoProposta | null>(null),
    moeda: new FormControl <Moeda | null>(null),
  });



  registro: PassagemProposta = <PassagemProposta>{};
  passagemProposta: PassagemProposta[] = [];
  moedas: Moeda[] = [];
  orcamento: OrcamentoProposta[] = [];
  editais: Edital[] = [];

  toggleLocalidadeForm(): void {
    this.mostrarCamposLocalidade = !this.mostrarCamposLocalidade;
  }

  onTipoLocalidadeChange(): void {
    const tipo = this.formPassagemProposta.get('tipoLocalidadeDiariaProposta')?.value;
    if (tipo === 'Nacional') {
      // Limpar campos internacionais
      this.formPassagemProposta.get('origemPassagemProposta')?.reset();
      this.formPassagemProposta.get('destinoPassagemProposta')?.reset();
      this.formPassagemProposta.get('idaVoltaPassagemProposta')?.reset();
    } else if (tipo === 'Internacional') {
      // Limpar campos nacionais
      this.formPassagemProposta.get('origemPassagemProposta')?.reset();
      this.formPassagemProposta.get('destinoPassagemProposta')?.reset();
    }
  }

  save(): void {
    this.registro = Object.assign(this.registro, this.formPassagemProposta.value);
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
    this.registro = Object.assign(this.registro, this.formPassagemProposta.value);
    this.router.navigate(['/propostaservico/form'], {
      queryParams: { 
        orcamentoId: this.registro.orcamentoProposta.idOrcamentoProposta,   // Supondo que idProposta esteja no objeto retornado
      }
    })};

}
