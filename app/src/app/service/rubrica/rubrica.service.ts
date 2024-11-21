import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IService } from '../i-service';
import { Rubrica } from '../../model/Rubrica';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RubricaService implements IService<Rubrica> {

  constructor(
    private http: HttpClient
  ) { }


  apiUrl: string = environment.API_URL + '/rubricas/';

  
  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<Rubrica>> {
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
    return this.http.get<RespostaPaginada<Rubrica>>(url);
  }
  getById(id: number): Observable<Rubrica> {
    let url = this.apiUrl + id;
    return this.http.get<Rubrica>(url);
  }
  save(objeto: Rubrica): Observable<Rubrica> {
    let url = this.apiUrl;
    if (objeto.idRubrica) {
      return this.http.put<Rubrica>(url, objeto);
    } else {
      return this.http.post<Rubrica>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
