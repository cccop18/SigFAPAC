import { Component } from '@angular/core';
import { IForm } from '../../../i-form';
import { Diarias } from '../../../../model/Diarias';
import { DiariaService } from '../../../../service/diaria/diaria.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RespostaPaginada } from '../../../../model/RespostaPaginada';
import { ETipoAlerta } from '../../../../model/ETipoAlerta';
import { AlertaService } from '../../../../service/alerta/alerta.service';

@Component({
  selector: 'app-diaria-form',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule],
  templateUrl: './diaria-form.component.html',
  styleUrl: './diaria-form.component.css'
})
export class DiariaFormComponent implements IForm<Diarias> {

  constructor(
    private servico: DiariaService,
    private router: Router,
    private route: ActivatedRoute,
    private servicoAlerta: AlertaService,
    private rout: ActivatedRoute
  ){}
  
  ngOnInit(): void {

    this.servico.get().subscribe({
      next: (resposta: RespostaPaginada<Diarias>) => {
        this.diaria = resposta.content;
      }
    });

    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      this.servico.getById(+id).subscribe({
        next: (resposta: Diarias) => {
          this.registro = resposta;
          this.formDiarias.patchValue(this.registro);
        }
      });
    }
  }

  formDiarias = new FormGroup({
    tipoDiaria: new FormControl<string | null>(null, Validators.required),
    nivelAcademicoDiaria: new FormControl<string | null>(null, Validators.required),
    valorDiaria: new FormControl<string | null>(null, Validators.required),
    ativo: new FormControl<boolean | null>(null, Validators.required)
  })

  get form() {
    return this.formDiarias.controls;
  }

  registro: Diarias = <Diarias>{};
  diaria: Diarias[] = [];

  save(): void {
    this.registro = Object.assign(this.registro, this.formDiarias.value);
    this.servico.save(this.registro).subscribe({
      complete: () => {
        this.router.navigate(['/diarias']);
        this.servicoAlerta.enviarAlerta({
          tipo: ETipoAlerta.SUCESSO,
          mensagem: "Operação realizada com sucesso!"
        });
      }
    })
  }

}
