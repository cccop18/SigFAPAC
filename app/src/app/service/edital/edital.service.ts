import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IService } from '../i-service';
import { Edital } from '../../model/Edital';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EditalService implements IService<Edital>{

  constructor(
    private http: HttpClient
  ) { }
  apiUrl: string = environment.API_URL + '/edital/'
  
  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<Edital>> {
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
    return this.http.get<RespostaPaginada<Edital>>(url);
  }
  getById(id: number): Observable<Edital> {
    let url = this.apiUrl + id;
    return this.http.get<Edital>(url);
  }
  save(objeto: Edital): Observable<Edital> {
    let url = this.apiUrl;
    if (objeto.idEdital) {
      return this.http.put<Edital>(url, objeto);
    } else {
      return this.http.post<Edital>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
