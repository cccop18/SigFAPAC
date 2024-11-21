import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { CommonModule } from '@angular/common';  // Importa o CommonModule
import { ReactiveFormsModule, FormsModule } from '@angular/forms';  // Para formulários reativos

@Component({
  selector: 'app-cadastro-senha-pesquisador',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule],  // Adiciona CommonModule aqui
  templateUrl: './cadastro-senha-pesquisador.component.html',
  styleUrls: ['./cadastro-senha-pesquisador.component.css']
})
export class CadastroSenhaPesquisadorComponent {
  senhaForm: FormGroup;

  constructor(private fb: FormBuilder) {
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
      // Aqui vai a lógica de submissão do formulário
    }
  }
}
