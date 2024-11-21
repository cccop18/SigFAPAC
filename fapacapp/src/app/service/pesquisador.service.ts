import { Injectable } from '@angular/core';
import { IService } from './i-service';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../model/RequisicaoPaginada';
import { RespostaPaginada } from '../model/RespostaPaginada';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment.development';
import { Pesquisador } from '../model/Pesquisador';

@Injectable({
  providedIn: 'root'
})
export class PesquisadorService implements IService<Pesquisador> {

  constructor(
    private http: HttpClient
  ) { }

  apiUrl: string = environment.API_URL + '/pesquisador/';

  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<Pesquisador>> {
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
    return this.http.get<RespostaPaginada<Pesquisador>>(url);
  }
  getById(id: number): Observable<Pesquisador> {
    let url = this.apiUrl + id;
    return this.http.get<Pesquisador>(url);
  }
  save(objeto: Pesquisador): Observable<Pesquisador> {
    let url = this.apiUrl;
    if (objeto.idPesquisador) {
      return this.http.put<Pesquisador>(url, objeto);
    } else {
      return this.http.post<Pesquisador>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
