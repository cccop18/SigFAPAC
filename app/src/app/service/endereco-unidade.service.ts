import { Injectable } from '@angular/core';
import { IService } from './i-service';
import { EnderecoUnidade } from '../model/EnderecoUnidade';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../model/RequisicaoPaginada';
import { RespostaPaginada } from '../model/RespostaPaginada';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class EnderecoUnidadeService implements IService <EnderecoUnidade> {

  constructor(
    private http: HttpClient
  ) { }
  apiUrl: string = environment.API_URL + '/enderecoUnidade/';
  
  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<EnderecoUnidade>> {
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
    return this.http.get<RespostaPaginada<EnderecoUnidade>>(url);
  }
  getById(id: number): Observable<EnderecoUnidade> {
    let url = this.apiUrl + id;
    return this.http.get<EnderecoUnidade>(url);
  }
  save(objeto: EnderecoUnidade): Observable<EnderecoUnidade> {
    let url = this.apiUrl;
    if (objeto.id) {
      return this.http.put<EnderecoUnidade>(url, objeto);
    } else {
      return this.http.post<EnderecoUnidade>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
