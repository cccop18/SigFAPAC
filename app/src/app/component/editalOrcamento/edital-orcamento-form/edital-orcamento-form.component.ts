import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormsModule, ReactiveFormsModule, FormArray } from '@angular/forms';
import { Router, ActivatedRoute, RouterLink } from '@angular/router';
import { EditalOrcamentoService } from '../../../service/editalOrcamento/edital-orcamento.service';
import { MoedaService } from '../../../service/moeda/moeda.service';
import { AlertaService } from '../../../service/alerta/alerta.service';
import { EditalOrcamento } from '../../../model/EditalOrcamento';
import { Moeda } from '../../../model/Moeda';
import { RubricaService } from '../../../service/rubrica/rubrica.service';
import { Rubrica } from '../../../model/Rubrica';
import { ETipoAlerta } from '../../../model/ETipoAlerta';
import { RespostaPaginada } from '../../../model/RespostaPaginada';
import { CommonModule } from '@angular/common';
import { MenuEditalComponent } from '../../menu-edital/menu-edital.component';
import { RubricaEdital } from '../../../model/RubricaEdital';
import { RubricaEditalService } from '../../../service/rubricaEdital/rubrica-edital.service';

@Component({
  selector: 'app-edital-orcamento-form',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule, MenuEditalComponent],
  templateUrl: './edital-orcamento-form.component.html',
  styleUrls: ['./edital-orcamento-form.component.css']
})
export class EditalOrcamentoFormComponent implements OnInit {
  constructor(
    private servico: EditalOrcamentoService,
    private servicoMoeda: MoedaService,
    private servicoRubrica: RubricaService,
    private servicoAlerta: AlertaService,
    private router: Router,
    private route: ActivatedRoute,
    private servicoRubricaEdital: RubricaEditalService
  ) { }

  registro: EditalOrcamento = <EditalOrcamento>{};
  moedas: Moeda[] = [];
  rubricas: Rubrica[] = [];
  rubricasedital: RubricaEdital[] = []
  moedaAtiva: boolean = false;

  formEditalOrcamento = new FormGroup({
    cordenadorConfigOrcamentoEdital: new FormControl<boolean | null>(null),
    moeda: new FormControl<number | null>(null),
    limiteAnualPrimeiroAno: new FormControl<string | null>(null),
    limiteAnualSegundoAno: new FormControl<string | null>(null),
    limiteAnualTerceiroAno: new FormControl<string | null>(null),
    limiteAnualQuartoAno: new FormControl<string | null>(null),
    limiteAnualQuintoAno: new FormControl<string | null>(null),
    porcentagemRubricaEdital: new FormControl<RubricaEdital[] | null>(null),
    rubricasArray: new FormArray([]),  // FormArray para as rubricas
    rubricaEdital: new FormArray([])
  });
  
  get rubricasArray(): FormArray {
    return this.formEditalOrcamento.get('rubricasArray') as FormArray;
  }  

  get form() {
    return this.formEditalOrcamento.controls;
  }

  ngOnInit(): void {
    // Carrega moedas
    this.servicoMoeda.get().subscribe({
      next: (resposta: RespostaPaginada<Moeda>) => {
        this.moedas = resposta.content;
      }
    });
  
    // Carrega rubricas
    this.servicoRubrica.get().subscribe({
      next: (resposta: RespostaPaginada<Rubrica>) => {
        this.rubricas = resposta.content;
        console.log("Rubricas carregadas: ", this.rubricas);
  
        // Adiciona dinamicamente controles de rubricas ao FormArray
        const rubricasArray = this.formEditalOrcamento.get('rubricasArray') as FormArray;
        this.rubricas.forEach((rubrica) => {
          rubricasArray.push(new FormGroup({
            id: new FormControl(rubrica.idRubrica),
            nome: new FormControl(rubrica.nomeRubrica),
            selecionado: new FormControl(false),
            porcentagemRubricaEdital: new FormControl(null)
          }));
        });
      },
      error: (err) => {
        console.error("Erro ao carregar rubricas: ", err);
      }
    });
    // Carrega dados do edital
    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      this.servico.getById(+id).subscribe({
        next: (resposta: EditalOrcamento) => {
          this.registro = resposta;
          this.formEditalOrcamento.patchValue(this.registro);
        }
      });
    }
  }  

  save(): void {
    const selectedRubricas = this.rubricasArray.controls
      .filter(control => control.get('selecionado')?.value)
      .map(control => ({
        idRubrica: control.get('id')?.value,
        porcentagemRubricaEdital: control.get('porcentagemRubricaEdital')?.value ?? null // Captura a porcentagem
      }));
  
    const editalOrcamentoPayload = {
      idEdital: this.registro.idEdital,
      cordenadorConfigOrcamentoEdital: this.formEditalOrcamento.value.cordenadorConfigOrcamentoEdital ?? null,
      moeda: this.formEditalOrcamento.value.moeda ?? null,
      limiteAnualPrimeiroAno: this.formEditalOrcamento.value.limiteAnualPrimeiroAno ?? "",
      limiteAnualSegundoAno: this.formEditalOrcamento.value.limiteAnualSegundoAno ?? "",
      limiteAnualTerceiroAno: this.formEditalOrcamento.value.limiteAnualTerceiroAno ?? "",
      limiteAnualQuartoAno: this.formEditalOrcamento.value.limiteAnualQuartoAno ?? "",
      limiteAnualQuintoAno: this.formEditalOrcamento.value.limiteAnualQuintoAno ?? "",
      rubricasSelecionadas: selectedRubricas // Enviar rubrica com porcentagem
    };
  
    this.servico.save(editalOrcamentoPayload).subscribe({
      next: (response: EditalOrcamento) => {
        this.router.navigate(['editalabrangencia/form'], {
          queryParams: { id: response.idEdital }
        });
        this.servicoAlerta.enviarAlerta({
          tipo: ETipoAlerta.SUCESSO,
          mensagem: "Operação realizada com sucesso!"
        });
      }
    });
  }  
}  