import { Injectable } from '@angular/core';
import { IService } from '../i-service';
import { Membros } from '../../model/Membros';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MembrosService implements IService<Membros> {

  constructor(
    private http: HttpClient
  ) { }

  apiUrl: string = environment.API_URL + '/membros/';


  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<Membros>> {
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
    return this.http.get<RespostaPaginada<Membros>>(url);
  }
  getById(id: number): Observable<Membros> {
    let url = this.apiUrl + id;
    return this.http.get<Membros>(url);
  }
  save(objeto: Membros): Observable<Membros> {
    let url = this.apiUrl;
    if (objeto.idMembros) {
      return this.http.put<Membros>(url, objeto);
    } else {
      return this.http.post<Membros>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
  getByPropostaId(idProposta: number): Observable<RespostaPaginada<Membros>> {
    return this.http.get<RespostaPaginada<Membros>>(`${this.apiUrl}/membros?propostaId=${idProposta}`);
  }
}
