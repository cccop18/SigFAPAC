import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { EditalOrcamento } from '../../model/EditalOrcamento';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';

@Injectable({
  providedIn: 'root'
})
export class EditalOrcamentoService {

  constructor(
    private http: HttpClient
  ) { }

  apiUrl: string = environment.API_URL + '/editalOrcamento/'

  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<EditalOrcamento>> {
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
    return this.http.get<RespostaPaginada<EditalOrcamento>>(url);
  }
  getById(id: number): Observable<EditalOrcamento> {
    let url = this.apiUrl + id;
    return this.http.get<EditalOrcamento>(url);
  }
  save(objeto: EditalOrcamento): Observable<EditalOrcamento> {
    let url = this.apiUrl;
    if (objeto.idEdital) {
      console.log("Método update chamado");
      return this.http.put<EditalOrcamento>(url, objeto);
    } else {
      console.log("Método save chamado");
      return this.http.post<EditalOrcamento>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
