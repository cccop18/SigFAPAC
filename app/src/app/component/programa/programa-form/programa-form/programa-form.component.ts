import { Component } from '@angular/core';
import { IForm } from '../../../i-form';
import { Programa } from '../../../../model/Programa';
import { ProgramaService } from '../../../../service/programa/programa.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RespostaPaginada } from '../../../../model/RespostaPaginada';
import { ETipoAlerta } from '../../../../model/ETipoAlerta';
import { AlertaService } from '../../../../service/alerta/alerta.service';

@Component({
  selector: 'app-programa-form',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule],
  templateUrl: './programa-form.component.html',
  styleUrl: './programa-form.component.css'
})
export class ProgramaFormComponent implements IForm<Programa>{
  
  constructor(
    private servico: ProgramaService,
    private router: Router,
    private route: ActivatedRoute,
    private servicoAlerta: AlertaService,
    private rout: ActivatedRoute
  ){}

  ngOnInit(): void {

    this.servico.get().subscribe({
      next: (resposta: RespostaPaginada<Programa>) => {
        this.programa = resposta.content;
      }
    });

    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      this.servico.getById(+id).subscribe({
        next: (resposta: Programa) => {
          this.registro = resposta;
          this.formPrograma.patchValue(this.registro);
        }
      });
    }
  }

  registro: Programa = <Programa>{};
  programa: Programa[] = [];

  formPrograma = new FormGroup({
    nomePrograma: new FormControl<string | null>(null, Validators.required),
    ativoPrograma: new FormControl<boolean | null>(null),
  })

  get form() {
    return this.formPrograma.controls;
  }

  save(): void {
    this.registro = Object.assign(this.registro, this.formPrograma.value);
    this.servico.save(this.registro).subscribe({
      complete: () => {
        this.router.navigate(['/programa']);
        this.servicoAlerta.enviarAlerta({
          tipo: ETipoAlerta.SUCESSO,
          mensagem: "Operação realizada com sucesso!"
        });
      }
    })
  }

}
