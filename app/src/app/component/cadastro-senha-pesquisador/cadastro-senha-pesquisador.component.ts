import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CadastroDePesquisadorService } from '../../service/cadastrarPesquisador/cadastro-de-pesquisador.service';


@Component({
  selector: 'app-cadastro-senha-pesquisador',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule],
  templateUrl: './cadastro-senha-pesquisador.component.html',
  styleUrls: ['./cadastro-senha-pesquisador.component.css']
})

export class CadastroSenhaPesquisadorComponent {
  senhaForm: FormGroup;

  constructor(private fb: FormBuilder, private cadastroService: CadastroDePesquisadorService) {
    this.senhaForm = this.fb.group({
      senha: ['', [Validators.required]],
      confirmar_senha: ['', [Validators.required]]
    }, { validators: this.senhasIguais });
  }

  // Validador personalizado para verificar se as senhas são iguais
  senhasIguais(form: AbstractControl) {
    const senha = form.get('senha')?.value;
    const confirmarSenha = form.get('confirmar_senha')?.value;

    return senha === confirmarSenha ? null : { senhasDiferentes: true };
  }

  // Função que pode ser usada no template para verificar se as senhas são iguais
  get senhaNaoCoincide() {
    return this.senhaForm.hasError('senhasDiferentes');
  }

  onSubmit() {
    if (this.senhaForm.valid) {
      // Enviar os dados de senha para o CadastroDePesquisadorService
      const senhaDados = {
        senha: this.senhaForm.get('senha')?.value
      };

      this.cadastroService.setDados('senhapesquisador', senhaDados);

      this.cadastroService.enviarCadastro();
    }
  }
}
