import { Injectable } from '@angular/core';
import { RubricaEdital } from '../../model/RubricaEdital';
import { IService } from '../i-service';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RubricaEditalService implements IService<RubricaEdital> {

  constructor(
    private http: HttpClient
  ) { }


  apiUrl: string = environment.API_URL + '/rubricas/';

  
  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<RubricaEdital>> {
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
    return this.http.get<RespostaPaginada<RubricaEdital>>(url);
  }
  getById(id: number): Observable<RubricaEdital> {
    let url = this.apiUrl + id;
    return this.http.get<RubricaEdital>(url);
  }
  save(objeto: RubricaEdital): Observable<RubricaEdital> {
    let url = this.apiUrl;
    if (objeto.idRubricaEdital) {
      return this.http.put<RubricaEdital>(url, objeto);
    } else {
      return this.http.post<RubricaEdital>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
