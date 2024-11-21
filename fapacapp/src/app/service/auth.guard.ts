import { CanActivateFn } from '@angular/router';
import { LoginService } from './login/i-login.service';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {
  const loginService = inject(LoginService);
  if (!loginService.isLoggedIn()) {
    loginService.logout();
    return false;
  }
  const papelExigido = route.data['papel'];
  const papelUsuario = loginService.usuarioAutenticado.value.papelPesquisador;
  const podeAcessar = papelExigido == papelUsuario || !papelExigido;
  return podeAcessar;
};
