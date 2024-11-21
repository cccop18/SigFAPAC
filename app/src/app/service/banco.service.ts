import { Injectable } from '@angular/core';
import { IService } from './i-service';
import { Banco } from '../model/Banco';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { RequisicaoPaginada } from '../model/RequisicaoPaginada';
import { Observable } from 'rxjs';
import { RespostaPaginada } from '../model/RespostaPaginada';
@Injectable({
  providedIn: 'root'
})
export class BancoService implements IService<Banco> {

  constructor(
    private http: HttpClient
  ) { }

  apiUrl: string = environment.API_URL + '/banco/';
  
  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<Banco>> {
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
    return this.http.get<RespostaPaginada<Banco>>(url);
  }
  getById(id: number): Observable<Banco> {
    let url = this.apiUrl + id;
    return this.http.get<Banco>(url);
  }
  save(objeto: Banco): Observable<Banco> {
    let url = this.apiUrl;
    if (objeto.idBanco) {
      return this.http.put<Banco>(url, objeto);
    } else {
      return this.http.post<Banco>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}