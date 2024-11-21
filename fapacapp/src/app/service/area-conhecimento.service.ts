import { Injectable } from '@angular/core';
import { IService } from './i-service';
import { AreaConhecimento } from '../model/AreaConhecimento';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../model/RequisicaoPaginada';
import { RespostaPaginada } from '../model/RespostaPaginada';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AreaConhecimentoService implements IService<AreaConhecimento> {

  constructor(
    private http: HttpClient
  ) { }

  apiUrl: string = environment.API_URL + '/areaConhecimento/';
  
  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<AreaConhecimento>> {
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
    return this.http.get<RespostaPaginada<AreaConhecimento>>(url);
  }
  getById(id: number): Observable<AreaConhecimento> {
    let url = this.apiUrl + id;
    return this.http.get<AreaConhecimento>(url);
  }
  save(objeto: AreaConhecimento): Observable<AreaConhecimento> {
    let url = this.apiUrl;
    if (objeto.id) {
      return this.http.put<AreaConhecimento>(url, objeto);
    } else {
      return this.http.post<AreaConhecimento>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
