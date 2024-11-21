import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { EncargoService } from '../../service/encargo/encargo.service'; // Importe o serviço correto
import { AlertaService } from '../../service/alerta/alerta.service';
import { ETipoAlerta } from '../../model/ETipoAlerta';
import { OrcamentoProposta } from '../../model/OrcamentoProposta';
import { Moeda } from '../../model/Moeda';
import { MoedaService } from '../../service/moeda/moeda.service';
import { OrcamentoPropostaService } from '../../service/orcamentoProposta/orcamento-proposta.service';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { MenuPropostaComponent } from '../menu-proposta/menu-proposta/menu-proposta.component';
import { EncargoProposta } from '../../model/EncargoProposta';
import { Edital } from '../../model/Edital';
import { EditalService } from '../../service/edital/edital.service';


@Component({
  selector: 'app-encargo-proposta-form',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule, MenuPropostaComponent],
  templateUrl: './encargo-proposta.component.html',
  styleUrl: './encargo-proposta.component.css'
})
export class EncargoPropostaFormComponent {

  constructor(
    private servico:EncargoService, // Use o serviço de EncargoProposta
    private servicoMoeda: MoedaService,
    private servicoOrcamento: OrcamentoPropostaService,
    private servicoEdital: EditalService,
    private router: Router,
    private route: ActivatedRoute,
    private servicoAlerta: AlertaService
  ) { }

  formEncargoProposta = new FormGroup({
    idEncargoProposta: new FormControl<number | null>(null),
    especificacaoEncargoProposta: new FormControl<string | null>(null),
    custoTotalProposta: new FormControl<number | null>(null),
    orcamentoProposta: new FormControl<OrcamentoProposta | null>(null),
    moeda: new FormControl<Moeda | null>(null),
     
  });

  registro: EncargoProposta = <EncargoProposta>{};
  encargosProposta: EncargoProposta[] = []; // Use um array para a lista
  moedas: Moeda[] = [];
  orcamentos: OrcamentoProposta[] = []; // Renomeado para orcamentos
  editais: Edital[] = [];


  ngOnInit(): void {

    this.servico.get().subscribe({
      next: (resposta: RespostaPaginada<EncargoProposta>) => {
        this.encargosProposta = resposta.content; // Atribui ao array
      }
    });

    this.servicoMoeda.get().subscribe({
      next: (resposta: RespostaPaginada<Moeda>) => {
        this.moedas = resposta.content;
      }
    });

    this.servicoOrcamento.get().subscribe({
      next: (resposta: RespostaPaginada<OrcamentoProposta>) => {
        this.orcamentos = resposta.content; // Atribui ao array renomeado
      }
    });

    this.servicoEdital.get().subscribe({
      next: (resposta: RespostaPaginada<Edital>) => {
        this.editais = resposta.content;
      }
    });


   // Pegar id do orçamento da query e carregar orçamento
   const orcamentoId = this.route.snapshot.queryParamMap.get('orcamentoId');
   if (orcamentoId) {
     this.servicoOrcamento.getById(+orcamentoId).subscribe({
       next: (resposta: OrcamentoProposta) => {
         this.formEncargoProposta.patchValue({
           orcamentoProposta: resposta
         });
       }
     });
   }

  }


  save(): void {
    this.registro = Object.assign(this.registro, this.formEncargoProposta.value);
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
    if (confirm('Deseja apagar o Encargo Proposta?')) { // Mensagem específica
      this.servico.delete(id).subscribe({
        complete: () => {
          this.router.navigate(['/encargoproposta']); // Navega para a rota correta
          this.servicoAlerta.enviarAlerta({
            tipo: ETipoAlerta.SUCESSO,
            mensagem: "Encargo Proposta excluído com sucesso!" // Mensagem específica
          });
        }
      });
    }
  }


}