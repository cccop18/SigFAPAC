import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { IForm } from '../../i-form';
import { EnderecoUnidade } from '../../../model/EnderecoUnidade';
import { EnderecoUnidadeService } from '../../../service/endereco-unidade.service';
import { AlertaService } from '../../../service/alerta.service';
import { ETipoAlerta } from '../../../model/ETipoAlerta';

@Component({
  selector: 'app-endereco-unidade-form',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule],
  templateUrl: './endereco-unidade-form.component.html',
  styleUrl: './endereco-unidade-form.component.css'
})
export class EnderecoUnidadeFormComponent implements IForm<EnderecoUnidade>{

  constructor(
    private servico: EnderecoUnidadeService,
    private servicoAlerta: AlertaService,
    private router: Router,
    private route: ActivatedRoute
  ){}
  ngOnInit(): void {

    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      this.servico.getById(+id).subscribe({
        next: (resposta: EnderecoUnidade) => {
          this.registro = resposta;
          this.formEnderecoUnidade.patchValue(this.registro);
        }
      });
    }
    //this.registro.ativo = this.registro.ativo || false;
  }
  
  registro: EnderecoUnidade = <EnderecoUnidade>{};

  formEnderecoUnidade = new FormGroup({
    cepEnderecoUnidade: new FormControl<string | null>(null),
    logradouroEnderecoUnidade: new FormControl<string | null>(null),
    numeroEnderecoUnidade: new FormControl<string | null>(null),
    bairroEnderecoUnidade: new FormControl<string | null>(null),
    estadoEnderecoUnidade: new FormControl<string | null>(null),
    paisEnderecoUnidade: new FormControl<string | null>(null)
  });  

  get form() {
    return this.formEnderecoUnidade.controls;
  }

  save(): void {
    this.registro = Object.assign(this.registro, this.formEnderecoUnidade.value);
    this.servico.save(this.registro).subscribe({
      complete: () => {
        this.router.navigate(['/convenios']);
        this.servicoAlerta.enviarAlerta({
          tipo: ETipoAlerta.SUCESSO,
          mensagem: "Operação realizada com sucesso!"
        });
      }
    });
  }

}
