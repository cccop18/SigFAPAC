import { Component } from '@angular/core';
import { LoginService } from '../../service/login/i-login.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup;

  constructor(private loginService: LoginService, private fb: FormBuilder) {
    this.loginForm = this.fb.group({
      login: ['', Validators.required],
      senha: ['', Validators.required],
    });
  }

  onSubmit(): void {
    if (this.loginForm.valid) {
      this.loginService.login(this.loginForm.value).subscribe(
        (response) => {
          console.log('Login bem-sucedido:', response);
          // Redirecione o usuário ou realize ações adicionais após login bem-sucedido
        },
        (error) => {
          console.error('Erro no login:', error);
          // Mostre uma mensagem de erro ao usuário, se necessário
        }
      );
    }
  }
}

