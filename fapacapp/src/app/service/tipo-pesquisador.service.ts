import { Injectable } from '@angular/core';
import { IService } from './i-service';
import { TipoPesquisador } from '../model/TipoPesquisador';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../model/RequisicaoPaginada';
import { RespostaPaginada } from '../model/RespostaPaginada';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TipoPesquisadorService implements IService<TipoPesquisador> {

  constructor(
    private http: HttpClient
  ) { }

  apiUrl: string = environment.API_URL + '/tipoPesquisador/';

  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<TipoPesquisador>> {
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
    return this.http.get<RespostaPaginada<TipoPesquisador>>(url);
  }
  getById(id: number): Observable<TipoPesquisador> {
    let url = this.apiUrl + id;
    return this.http.get<TipoPesquisador>(url);
  }
  save(objeto: TipoPesquisador): Observable<TipoPesquisador> {
    let url = this.apiUrl;
    if (objeto.id) {
      return this.http.put<TipoPesquisador>(url, objeto);
    } else {
      return this.http.post<TipoPesquisador>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
