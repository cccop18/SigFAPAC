import { Component } from '@angular/core';
import { IForm } from '../i-form';
import { Instituicao } from '../../model/Instituicao';
import { InstituicaoService } from '../../service/instituicao.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ETipoAlerta } from '../../model/ETipoAlerta';
import { CommonModule } from '@angular/common';
import { cnpjValidator } from '../../validators/cnpj.validator';
import { AlertaService } from '../../service/alerta/alerta.service';
import { AlertaComponent } from '../alerta/alerta.component';

@Component({
  selector: 'app-cadastro-instituicao',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule, AlertaComponent],
  templateUrl: './cadastro-instituicao.component.html',
  styleUrl: './cadastro-instituicao.component.css'
})
export class CadastroInstituicaoComponent implements IForm<Instituicao> {

  constructor(
    private servico: InstituicaoService,
    private servicoAlerta: AlertaService,
    private servicoInstituicao: InstituicaoService, 
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.servicoInstituicao.get().subscribe({
      next: (resposta: RespostaPaginada<Instituicao>) => {
        this.instituicao = resposta.content;
      }
    });

    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      this.servico.getById(+id).subscribe({
        next: (resposta: Instituicao) => {
          this.registro = resposta;
          this.formInstituicao.patchValue(this.registro);
        }
      });
    }

    // Atualizando o campo de país dinamicamente baseado no valor de estrangeiraInstituicao
    this.formInstituicao.get('estrangeiraInstituicao')!.valueChanges.subscribe(value => {
      const paisControl = this.formInstituicao.get('paisInstituicao')!;
      if (value) {
        paisControl.setValidators([Validators.required]); // Adiciona validação se estrangeiro for marcado
      } else {
        paisControl.clearValidators(); // Remove validação se estrangeiro não for marcado
      }
      paisControl.updateValueAndValidity(); // Atualiza o estado do campo
    });
  }

  registro: Instituicao = <Instituicao>{} ;
  instituicao: Instituicao[] = [];

  formInstituicao = new FormGroup({
    nomeInstituicao: new FormControl<string | null>(null, { nonNullable: true, validators: [Validators.required] }),
    siglaInstituicao: new FormControl<string | null>(null, { nonNullable: true, validators: [Validators.required] }),
    cnpjInstituicao: new FormControl<string | null>(null, { nonNullable: true, validators: [Validators.required, cnpjValidator()]}),
    estrangeiraInstituicao: new FormControl<boolean | null>(null),
    paisInstituicao: new FormControl<string | null>(null),
    dependenciaAdmInstituicao: new FormControl<string | null>(null, { nonNullable: true, validators: [Validators.required] }),
    ensinoSuperiorInstituicao: new FormControl<boolean | null>(null),
    finsLucrativosLnstituicao: new FormControl<boolean | null>(null),
  });

  get form() {
    return this.formInstituicao.controls;
  }
  
  save(): void {
    this.registro = Object.assign(this.registro, this.formInstituicao.value);
    this.servico.save(this.registro).subscribe({
      complete: () => {
        this.servicoAlerta.enviarAlerta({
          tipo: ETipoAlerta.SUCESSO,
          mensagem: "Operação realizada com sucesso!"
        });
      }
    });
  }
}
