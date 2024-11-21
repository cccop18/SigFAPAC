import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { EditalPlanoApresentacao } from '../../model/EditalPlanoApresentacao';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';

@Injectable({
  providedIn: 'root'
})
export class EditalPlanoApresentacaoService {

  constructor(
    private http: HttpClient
  ) { }

  apiUrl: string = environment.API_URL + '/editalPlanoApresentacao/'

  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<EditalPlanoApresentacao>> {
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
    return this.http.get<RespostaPaginada<EditalPlanoApresentacao>>(url);
  }
  getById(id: number): Observable<EditalPlanoApresentacao> {
    let url = this.apiUrl + id;
    return this.http.get<EditalPlanoApresentacao>(url);
  }
  save(objeto: EditalPlanoApresentacao): Observable<EditalPlanoApresentacao> {
    let url = this.apiUrl;
    if (objeto.idEdital) {
      console.log("Método update chamado");
      return this.http.put<EditalPlanoApresentacao>(url, objeto);
    } else {
      console.log("Método save chamado");
      return this.http.post<EditalPlanoApresentacao>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
