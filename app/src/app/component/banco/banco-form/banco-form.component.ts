import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule,Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { IForm } from '../../i-form';
import { Banco } from '../../../model/Banco';
import { BancoService } from '../../../service/banco.service';
import { ETipoAlerta } from '../../../model/ETipoAlerta';
import { RespostaPaginada } from '../../../model/RespostaPaginada';
import { AlertaService } from '../../../service/alerta/alerta.service';

@Component({
  selector: 'app-banco-form',
  standalone: true,
  imports: [FormsModule, CommonModule,RouterLink, ReactiveFormsModule],
  templateUrl: './banco-form.component.html',
  styleUrl: './banco-form.component.css'
})
export class BancoFormComponent implements IForm<Banco> {
  
  constructor(
    private servico: BancoService,
    private router: Router,
    private route: ActivatedRoute,
    private servicoAlerta: AlertaService,
    private rout: ActivatedRoute
    ) {}
  ngOnInit(): void {

    this.servico.get().subscribe({
      next: (resposta: RespostaPaginada<Banco>) => {
        this.banco = resposta.content;
      }
    });

    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      this.servico.getById(+id).subscribe({
        next: (resposta: Banco) => {
          this.registro = resposta;
          this.formBanco.patchValue(this.registro);
        }
      });
    }
  }

  
  registro: Banco = <Banco>{};
  banco: Banco[] = [];
  
  formBanco = new FormGroup({
    nomeBanco: new FormControl<string | null >(null, Validators.required),
    numeroBanco:new FormControl<number | null>(null, Validators.required),
    ativoBanco: new FormControl<boolean  | null>(null, Validators.required),
  });

  get form() {
    return this.formBanco.controls;
  }

  save(): void {
    this.registro = Object.assign(this.registro, this.formBanco.value);
    this.servico.save(this.registro).subscribe({
      complete: () => {
        this.router.navigate(['/bancos']);
        this.servicoAlerta.enviarAlerta({
          tipo: ETipoAlerta.SUCESSO,
          mensagem: "Operação realizada com sucesso!"
        });
      }
    })
  }
  

}
