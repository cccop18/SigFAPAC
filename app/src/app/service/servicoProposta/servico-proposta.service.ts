import { Injectable } from '@angular/core';
import { IService } from '../i-service';
import { ServicoProposta } from '../../model/ServicoProposta';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ServicoPropostaService implements IService<ServicoProposta> {

  constructor(
    private http: HttpClient
  ) { }

  apiUrl: string = environment.API_URL + '/servicoproposta/';

  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<ServicoProposta>> {
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
    return this.http.get<RespostaPaginada<ServicoProposta>>(url);
  }
  getById(id: number): Observable<ServicoProposta> {
    let url = this.apiUrl + id;
    return this.http.get<ServicoProposta>(url);
  }
  save(objeto: ServicoProposta): Observable<ServicoProposta> {
    let url = this.apiUrl;
    if (objeto.idServicoProposta) {
      return this.http.put<ServicoProposta>(url, objeto);
    } else {
      return this.http.post<ServicoProposta>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
