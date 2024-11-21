import { Injectable } from '@angular/core';
import { IService } from './i-service';
import { PesquisadorConhecimento } from '../model/PesquisadorConhecimento';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../model/RequisicaoPaginada';
import { RespostaPaginada } from '../model/RespostaPaginada';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PesquisadorConhecimentoService implements IService<PesquisadorConhecimento> {

  constructor(
    private http: HttpClient
  ) { }
 apiUrl: string = environment.API_URL + '/pesquisadorConhecimento/';

  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<PesquisadorConhecimento>> {
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
    return this.http.get<RespostaPaginada<PesquisadorConhecimento>>(url);
  }
  getById(id: number): Observable<PesquisadorConhecimento> {
    let url = this.apiUrl + id;
    return this.http.get<PesquisadorConhecimento>(url);
  }
  save(objeto: PesquisadorConhecimento): Observable<PesquisadorConhecimento> {
    let url = this.apiUrl;
    if (objeto.idPesquisadorConhecimento) {
      return this.http.put<PesquisadorConhecimento>(url, objeto);
    } else {
      return this.http.post<PesquisadorConhecimento>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
