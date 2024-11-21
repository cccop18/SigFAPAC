import { HttpRequest } from "@angular/common/http";
import { BehaviorSubject } from "rxjs";
import { Pesquisador } from "../../model/Pesquisador";
import { InjectionToken } from "@angular/core";

export interface ILoginService {
  usuarioAutenticado: BehaviorSubject<Pesquisador>;
  login(usuario: Pesquisador): void;
  logout(): void;
  isLoggedIn(): boolean;
  getHeaders(request: HttpRequest<any>): HttpRequest<any>;
}

export const LoginService = new InjectionToken<ILoginService>('ILoginService');
