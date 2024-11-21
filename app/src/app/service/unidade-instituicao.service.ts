import { Injectable } from '@angular/core';
import { IService } from './i-service';
import { UnidadeInstituicao } from '../model/UnidadeInstituicao';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../model/RequisicaoPaginada';
import { RespostaPaginada } from '../model/RespostaPaginada';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UnidadeInstituicaoService implements IService<UnidadeInstituicao>{

  constructor(
    private http: HttpClient
  ) { }

  apiUrl: string = environment.API_URL + '/unidadesInstituicao/';

  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<UnidadeInstituicao>> {
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
    return this.http.get<RespostaPaginada<UnidadeInstituicao>>(url);
  }
  getById(id: number): Observable<UnidadeInstituicao> {
    let url = this.apiUrl + id;
    return this.http.get<UnidadeInstituicao>(url);
  }
  save(objeto: UnidadeInstituicao): Observable<UnidadeInstituicao> {
    let url = this.apiUrl;
    if (objeto.id) {
      return this.http.put<UnidadeInstituicao>(url, objeto);
    } else {
      return this.http.post<UnidadeInstituicao>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
  getByInstituicao(instituicaoId: number): Observable<UnidadeInstituicao[]> {
    const url = `${this.apiUrl}instituicao/${instituicaoId}`;
    return this.http.get<UnidadeInstituicao[]>(url);
  }
}
