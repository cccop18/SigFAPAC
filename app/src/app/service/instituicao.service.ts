import { Injectable } from '@angular/core';
import { IService } from './i-service';
import { Instituicao } from '../model/Instituicao';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../model/RequisicaoPaginada';
import { RespostaPaginada } from '../model/RespostaPaginada';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class InstituicaoService implements IService<Instituicao>{

  constructor(
    private http: HttpClient
  ) { }
  apiUrl: string = environment.API_URL + '/instituicoes/';

  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<Instituicao>> {
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
    return this.http.get<RespostaPaginada<Instituicao>>(url);
  }
  getById(id: number): Observable<Instituicao> {
    let url = this.apiUrl + id;
    return this.http.get<Instituicao>(url);
  }
  save(objeto: Instituicao): Observable<Instituicao> {
    let url = this.apiUrl;
    if (objeto.idInstituicao) {
      return this.http.put<Instituicao>(url, objeto);
    } else {
      return this.http.post<Instituicao>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
