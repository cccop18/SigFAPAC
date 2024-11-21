import { Injectable } from '@angular/core';
import { IService } from './i-service';
import { EnderecoPesquisador } from '../model/EnderecoPesquisador';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../model/RequisicaoPaginada';
import { RespostaPaginada } from '../model/RespostaPaginada';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class EnderecoPesquisadorService implements IService<EnderecoPesquisador> {

  constructor(
    private http: HttpClient
  ) { }

  apiUrl: string = environment.API_URL + '/enderecoPesquisador/';

  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<EnderecoPesquisador>> {
    let url = this.apiUrl + "?";
    if (termoBusca) {
      url += "termoBusca=" + termoBusca;
    }
    if (paginacao) {
      url += "&page=" + paginacao.page;
      url += "&size=" + paginacao.size;
      paginacao.sort.forEach(campo => {
        url += "&sort=" + campo;
      });
    } else {
      url += "&unpaged=true";
    }
    return this.http.get<RespostaPaginada<EnderecoPesquisador>>(url);
  }
  getById(id: number): Observable<EnderecoPesquisador> {
    let url = this.apiUrl + id;
    return this.http.get<EnderecoPesquisador>(url);
  }
  save(objeto: EnderecoPesquisador): Observable<EnderecoPesquisador> {
    let url = this.apiUrl;
    if (objeto.idEnderecoPesquisador) {
      return this.http.put<EnderecoPesquisador>(url, objeto);
    } else {
      return this.http.post<EnderecoPesquisador>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
