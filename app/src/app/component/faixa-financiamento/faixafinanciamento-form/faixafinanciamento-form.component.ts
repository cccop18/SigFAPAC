import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { IForm } from '../../i-form';
import { FaixaFinanciamento } from '../../../model/FaixaFinanciamento';
import { FaixaFinanciamentoService } from '../../../service/faixaFinanciamento/faixa-financiamento.service';
import { ETipoAlerta } from '../../../model/ETipoAlerta';
import { AlertaService } from '../../../service/alerta/alerta.service';
import { MenuEditalComponent } from '../../menu-edital/menu-edital.component';
import { EditalService } from '../../../service/edital/edital.service';
import { Edital } from '../../../model/Edital';
import { faixaFinanciamentoValidator } from '../../../validators/min-max.validator';
import { RespostaPaginada } from '../../../model/RespostaPaginada';
import { NgbTooltip } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-faixafinanciamento-form',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule, MenuEditalComponent, NgbTooltip],
  templateUrl: './faixafinanciamento-form.component.html',
  styleUrls: ['./faixafinanciamento-form.component.css']
})
export class FaixafinanciamentoFormComponent implements IForm<FaixaFinanciamento> {

  constructor(
    private servico: FaixaFinanciamentoService,
    private servicoAlerta: AlertaService,
    private router: Router,
    private route: ActivatedRoute,
    private servicoEdital: EditalService) { }

  ngOnInit(): void {
    this.carregarDados();
  }

  carregarDados() {
    const editalId = this.route.snapshot.queryParamMap.get('id'); // Pegando o ID do edital da URL

    if (editalId) {
      // Busca todas as faixas de financiamento
      this.servico.get().subscribe({
        next: (resposta: RespostaPaginada<FaixaFinanciamento>) => {
          // Filtra as faixas de financiamento que possuem o mesmo idEdital
          this.faixafinanciamento = resposta.content.filter(faixa => faixa.edital?.idEdital === +editalId);
          console.log("Faixas filtradas:", this.faixafinanciamento);
        },
        error: () => {
          this.servicoAlerta.enviarAlerta({
            tipo: ETipoAlerta.ERRO,
            mensagem: 'Erro ao carregar faixas de financiamento.'
          });
        }
      });

      // Busca o objeto Edital e preenche o formulário
      this.servicoEdital.getById(+editalId).subscribe({
        next: (edital: Edital) => {
          // Atribui o edital ao campo 'edital' no formulário
          this.formfaixaFinanciamento.patchValue({
            edital: edital
          });
        },
        error: () => {
          this.servicoAlerta.enviarAlerta({
            tipo: ETipoAlerta.ERRO,
            mensagem: "Erro ao carregar o edital."
          });
        }
      });
    } else {
      // Exibe um alerta se o idEdital não for encontrado na rota
      this.servicoAlerta.enviarAlerta({
        tipo: ETipoAlerta.ERRO,
        mensagem: "Nenhum ID de edital encontrado na rota!"
      });
    }
  }

  formfaixaFinanciamento = new FormGroup({
    idFaixaFinanciamento: new FormControl<number | null>(null),
    nomeFaixaFinanciamento: new FormControl<string | null>(null, Validators.required),
    minFaixaFinanciamento: new FormControl<number | null>(null, Validators.required),
    maxFaixaFinanciamento: new FormControl<number | null>(null, Validators.required),
    observacaoFaixaFinanciamento: new FormControl<string | null>(null, Validators.required),
    edital: new FormControl<Edital | null>(null) // Inclui o idEdital
  }, { validators: faixaFinanciamentoValidator });

  get form() {
    return this.formfaixaFinanciamento.controls;
  }

  registro: FaixaFinanciamento = <FaixaFinanciamento>{};
  faixafinanciamento: FaixaFinanciamento[] = [];

  save(): void {
    this.registro = Object.assign(this.registro, this.formfaixaFinanciamento.value);
    this.servico.save(this.registro).subscribe({
      complete: () => {
        window.location.reload();
        this.router.navigate(['/editalfaixa/form'], {queryParams: { id: this.registro.edital.idEdital }});
        this.servicoAlerta.enviarAlerta({
          tipo: ETipoAlerta.SUCESSO,
          mensagem: "Operação realizada com sucesso!"
        });
      }
    });
    this.formfaixaFinanciamento.reset();
  }

  refreshComponent() {
    this.carregarDados();
  }
  delete(id: number): void {
    if (confirm('Confirma a remoção desta faixa?')) {
      this.servico.delete(id).subscribe({
        complete: () => {
          this.refreshComponent();
          this.servicoAlerta.enviarAlerta({
            tipo: ETipoAlerta.SUCESSO,
            mensagem: "Faixa removida com sucesso!"
          });
        }
      });
    }
  }

  redirect(){
    this.registro = Object.assign(this.registro, this.formfaixaFinanciamento.value);
    this.router.navigate(['/editalChamadas/form'], {queryParams: { id: this.registro.edital.idEdital }});
  }
}
