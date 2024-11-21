import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { IForm } from '../../i-form';
import { EnderecoUnidade } from '../../../model/EnderecoUnidade';
import { EnderecoUnidadeService } from '../../../service/endereco-unidade.service';
import { ETipoAlerta } from '../../../model/ETipoAlerta';
import { CepService } from '../../../service/api/cep-service.service';  // Importando o serviço de CEP
import { AlertaService } from '../../../service/alerta/alerta.service';

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
    private route: ActivatedRoute,
    private cepService: CepService  // Injetando o serviço de CEP
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
  }

  registro: EnderecoUnidade = <EnderecoUnidade>{};

  formEnderecoUnidade = new FormGroup({
    cepEnderecoUnidade: new FormControl<string | null>(null, [
      Validators.required,
      Validators.pattern(/^[0-9]{5}-?[0-9]{3}$/)  // CEP deve ter 8 dígitos
    ]),
    logradouroEnderecoUnidade: new FormControl<string | null>(null, [
      Validators.required  // Logradouro é obrigatório
    ]),
    numeroEnderecoUnidade: new FormControl<string | null>(null, [
      Validators.pattern(/^[0-9]*$/)  // Número deve ser numérico se fornecido
    ]),
    bairroEnderecoUnidade: new FormControl<string | null>(null, [
      Validators.required  // Bairro é obrigatório
    ]),
    estadoEnderecoUnidade: new FormControl<string | null>(null, [
      Validators.required  // Estado é obrigatório (nome completo do estado)
    ]),
    paisEnderecoUnidade: new FormControl<string | null>(null, [
      Validators.required  // País é obrigatório
    ])
  });

  get form() {
    return this.formEnderecoUnidade.controls;
  }

  verificarCep(): void {
    const cep = this.formEnderecoUnidade.get('cepEnderecoUnidade')?.value;
    if (cep && this.formEnderecoUnidade.get('cepEnderecoUnidade')?.valid) {
      this.cepService.consultarCep(cep).subscribe({
        next: (dados) => {
          if (dados.erro) {
            // Marcar como CEP inválido se não for encontrado
            this.formEnderecoUnidade.get('cepEnderecoUnidade')?.setErrors({ cepInvalido: true });
          } else {
            // Preencher os campos de endereço automaticamente com base no CEP válido
            this.formEnderecoUnidade.patchValue({
              logradouroEnderecoUnidade: dados.logradouro,
              bairroEnderecoUnidade: dados.bairro,
              estadoEnderecoUnidade: dados.uf,
              paisEnderecoUnidade: 'Brasil'
            });
          }
        },
        error: () => {
          // Tratar erros de conexão com a API
          this.formEnderecoUnidade.get('cepEnderecoUnidade')?.setErrors({ cepInvalido: true });
        }
      });
    } else if (!this.formEnderecoUnidade.get('cepEnderecoUnidade')?.valid) {
      this.formEnderecoUnidade.get('cepEnderecoUnidade')?.setErrors({ cepInvalido: true });
    }
  }

  save(): void {
    this.registro = Object.assign(this.registro, this.formEnderecoUnidade.value);
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
