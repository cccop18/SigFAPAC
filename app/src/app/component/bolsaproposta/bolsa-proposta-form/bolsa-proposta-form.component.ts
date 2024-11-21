import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { MenuPropostaComponent } from '../../menu-proposta/menu-proposta/menu-proposta.component';
import { HttpClient } from '@angular/common/http';
import { BolsapropostaService } from '../../../service/bolsaproposta/bolsaproposta.service';
import { MoedaService } from '../../../service/moeda/moeda.service';
import { OrcamentoPropostaService } from '../../../service/orcamentoProposta/orcamento-proposta.service';
import { EditalService } from '../../../service/edital/edital.service';
import { AlertaService } from '../../../service/alerta/alerta.service';
import { RespostaPaginada } from '../../../model/RespostaPaginada';
import { BolsaProposta } from '../../../model/BolsaProposta';
import { Moeda } from '../../../model/Moeda';
import { OrcamentoProposta } from '../../../model/OrcamentoProposta';
import { Edital } from '../../../model/Edital';
import { ETipoAlerta } from '../../../model/ETipoAlerta';

@Component({
  selector: 'app-bolsa-proposta-form',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule, MenuPropostaComponent],
  templateUrl: './bolsa-proposta-form.component.html',
  styleUrl: './bolsa-proposta-form.component.css'
})
export class BolsaPropostaFormComponent {
    
  bolsaProposta: BolsaProposta[] = [];
  
  moedas: Moeda[] = [];
  orcamento: OrcamentoProposta[] = []; 
  editais: Edital[] = [];
  

  constructor(
    private http: HttpClient,
    private servico: BolsapropostaService,
    private servicoMoeda: MoedaService,
    private servicoOrcamento: OrcamentoPropostaService,
    private servicoEdital: EditalService,
    private router: Router,
    private route: ActivatedRoute,
    private servicoAlerta: AlertaService,
    private rout: ActivatedRoute
  ){}
  
  
  ngOnInit(): void {
    // Carregar bolsas
    this.servico.get().subscribe({
      next: (resposta: RespostaPaginada<BolsaProposta>) => {
        this.bolsaProposta = resposta.content;
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
    });

    // Pegar id da bolsa da query
    const bolsaId = this.route.snapshot.queryParamMap.get('id');
    if (bolsaId) {
      this.servico.getById(+bolsaId).subscribe({
        next: (resposta: BolsaProposta) => {
          this.registro = resposta;
          this.formBolsaProposta.patchValue(this.registro);
        }
      });
    }

    // Pegar id do orçamento da query e carregar orçamento
    const orcamentoId = this.route.snapshot.queryParamMap.get('orcamentoId');
    if (orcamentoId) {
      this.servicoOrcamento.getById(+orcamentoId).subscribe({
        next: (resposta: OrcamentoProposta) => {
          this.formBolsaProposta.patchValue({
            orcamentoProposta: resposta
          });
        }
      });
    }
  }


  save(): void {
    this.registro = Object.assign(this.registro, this.formBolsaProposta.value);
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
    if (confirm('Deseja apagar o bolsa Proposta?')) { // Mensagem específica
      this.servico.delete(id).subscribe({
        complete: () => {
          this.router.navigate(['/bolsaproposta']); // Navega para a rota correta
          this.servicoAlerta.enviarAlerta({
            tipo: ETipoAlerta.SUCESSO,
            mensagem: "Bolsa Proposta excluído com sucesso!" // Mensagem específica
          });
        }
      });
    }
  }

          formBolsaProposta = new FormGroup({
          idBolsaProposta: new FormControl<number | null>(null),
          modalidadeBolsaProposta: new FormControl<String | null>(null),
          quantidadeBolsaProposta: new FormControl<number | null>(null),
          duracaoBolsaProposta: new FormControl<String | null>(null),
          bolsaMesBolsaProposta: new FormControl<number| null>(null),
          areaBolsaProposta: new FormControl<String| null>(null),
          orcamentoProposta: new FormControl<OrcamentoProposta| null>(null),
          moeda: new FormControl<Moeda|null>(null),
  });
  registro: BolsaProposta = <BolsaProposta>{};
  
}

