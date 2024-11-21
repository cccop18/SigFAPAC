import { Injectable } from '@angular/core';
import { IService } from '../i-service';
import { Proposta } from '../../model/Proposta';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PropostaService implements IService<Proposta> {

  constructor(
    private http: HttpClient
  ) { }

  apiUrl: string = environment.API_URL + '/propostas/'
  
  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<Proposta>> {
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
    return this.http.get<RespostaPaginada<Proposta>>(url);
  }
  getById(id: number): Observable<Proposta> {
    let url = this.apiUrl + id;
    return this.http.get<Proposta>(url);
  }
  save(objeto: Proposta): Observable<Proposta> {
    let url = this.apiUrl;
    if (objeto.idProposta) {
      return this.http.put<Proposta>(url, objeto);
    } else {
      return this.http.post<Proposta>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
