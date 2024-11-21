import { Injectable } from '@angular/core';
import { IService } from '../i-service';
import { AtividadeProposta } from '../../model/AtividadeProposta';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AtividadePropostaService implements IService<AtividadeProposta> {

  constructor(
    private http: HttpClient
  ) { }
  apiUrl: string = environment.API_URL + '/AtividadePropostas/';
  
  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<AtividadeProposta>> {
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
    return this.http.get<RespostaPaginada<AtividadeProposta>>(url);
  }
  getById(id: number): Observable<AtividadeProposta> {
    let url = this.apiUrl + id;
    return this.http.get<AtividadeProposta>(url);
  }
  save(objeto: AtividadeProposta): Observable<AtividadeProposta> {
    let url = this.apiUrl;
    if (objeto.idAtividadeProposta) {
      return this.http.put<AtividadeProposta>(url, objeto);
    } else {
      return this.http.post<AtividadeProposta>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
