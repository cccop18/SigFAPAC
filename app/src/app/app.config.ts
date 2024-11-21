import { HttpClientXsrfModule, provideHttpClient, withInterceptors } from '@angular/common/http';
import { ApplicationConfig, importProvidersFrom } from '@angular/core';
import { provideRouter, withInMemoryScrolling } from '@angular/router';
import { environment } from '../environments/environment';
import { routes } from './app.routes';
import { erroInterceptor } from './interceptor/erro.interceptor';
import { LoginService } from './service/login/i-login.service';
import { JwtLoginService } from './service/login/jwt-login.service';
import { authInterceptor } from './interceptor/auth.interceptor';

export function loginServiceFactory() {

  const authType = environment.AUTH_TYPE;

  if (authType == 'jwt') {
    return new JwtLoginService();
  } else {
    throw new Error('Tipo de autenticação deve ser "basic" ou "jwt".');
  }

}

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes, withInMemoryScrolling({anchorScrolling: 'enabled'})),
    provideHttpClient(withInterceptors([erroInterceptor, authInterceptor])),
    { provide: LoginService, useFactory: loginServiceFactory },
    importProvidersFrom(HttpClientXsrfModule)
  ]
};
