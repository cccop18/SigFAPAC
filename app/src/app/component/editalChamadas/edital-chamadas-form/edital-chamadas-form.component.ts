import { Component } from '@angular/core';
import { ETipoAlerta } from '../../../model/ETipoAlerta';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { EditalChamadas } from '../../../model/EditalChamadas';
import { AlertaService } from '../../../service/alerta/alerta.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { EditalChmadasService } from '../../../service/editalChamadas/edital-chmadas.service';
import { CommonModule } from '@angular/common';
import { MenuEditalComponent } from '../../menu-edital/menu-edital.component';
import { dateRangeValidator } from '../../../validators/date.validator';
import { noPastDateValidator } from '../../../validators/data-passado.validator';
import { sequentialDateValidator } from '../../../validators/sequencia-data.validator';
import { minimumDaysBetweenValidator } from '../../../validators/dias-minimos';

@Component({
  selector: 'app-edital-chamadas-form',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule, MenuEditalComponent],
  templateUrl: './edital-chamadas-form.component.html',
  styleUrl: './edital-chamadas-form.component.css'
})
export class EditalChamadasFormComponent {
  constructor(
    private servico: EditalChmadasService,
    private servicoAlerta: AlertaService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      this.servico.getById(+id).subscribe({
        next: (resposta: EditalChamadas) => {
          this.registro = resposta;
          this.formEditalChamadas.patchValue(this.registro);
        }
      });
    }
  }

  registro: EditalChamadas = <EditalChamadas>{};

  formEditalChamadas = new FormGroup({
    dataAberturaEdital: new FormControl<string | null>(null, [Validators.required, noPastDateValidator]),
    horaAberturaEdital: new FormControl<string | null>(null, Validators.required),
    dataFechamentoEdital: new FormControl<string | null>(null, [Validators.required, noPastDateValidator]),
    horaFechamentoEdital: new FormControl<string | null>(null, Validators.required),
    dataEnquadramentoEdital: new FormControl<string | null>(null, [Validators.required, noPastDateValidator]),
    dataRecursoEdital: new FormControl<string | null>(null, [Validators.required, noPastDateValidator]),
    dataResultadoEdital: new FormControl<string | null>(null, [Validators.required, noPastDateValidator]),
    dataRecursoFinalEdital: new FormControl<string | null>(null, [noPastDateValidator]),
    dataBolsaEdital: new FormControl<string | null>(null),
    informacaoChamadasEdital: new FormControl<string | null>(null),
  },{
    validators: [
      dateRangeValidator('dataAberturaEdital', 'dataFechamentoEdital'),
      minimumDaysBetweenValidator('dataAberturaEdital', 'dataRecursoEdital', 1),
      dateRangeValidator('dataAberturaEdital', 'dataEnquadramentoEdital'),
      dateRangeValidator('dataAberturaEdital', 'dataRecursoEdital'),
    ],
  }
);

  get form() {
    return this.formEditalChamadas.controls;
  }

  save(): void {
    this.registro = Object.assign(this.registro, this.formEditalChamadas.value);
    this.servico.save(this.registro).subscribe({
      next: (response: EditalChamadas) => {
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
