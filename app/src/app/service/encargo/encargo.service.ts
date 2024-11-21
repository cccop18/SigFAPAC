import { Injectable } from '@angular/core';
import { IService } from '../i-service';
import { EncargoProposta } from '../../model/EncargoProposta';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';
import { Observable } from 'rxjs';
import { RespostaPaginada } from '../../model/RespostaPaginada';

@Injectable({
  providedIn: 'root'
})
export class EncargoService implements IService<EncargoProposta> {

  constructor(
    private http: HttpClient
  ) { }

  apiUrl: string = environment.API_URL + '/encargoproposta/'

  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<EncargoProposta>> {
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
    return this.http.get<RespostaPaginada<EncargoProposta>>(url);
  }

  getById(id: number): Observable<EncargoProposta> {
    let url = this.apiUrl + id;
    return this.http.get<EncargoProposta>(url);
  }
  save(objeto: EncargoProposta): Observable<EncargoProposta> {
    let url = this.apiUrl;
    if (objeto.idEncargoProposta) {
      return this.http.put<EncargoProposta>(url, objeto);
    } else {
      return this.http.post<EncargoProposta>(url, objeto);
    }
  }

  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
