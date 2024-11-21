import { Injectable } from '@angular/core';
import { IService } from '../i-service';
import { OrcamentoProposta } from '../../model/OrcamentoProposta';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class OrcamentoPropostaService implements IService<OrcamentoProposta>{

  constructor(
    private http: HttpClient
  ) { }

  apiUrl: string = environment.API_URL + '/orcamentoProposta/';

  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<OrcamentoProposta>> {
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
    return this.http.get<RespostaPaginada<OrcamentoProposta>>(url);
  }
  getById(id: number): Observable<OrcamentoProposta> {
    let url = this.apiUrl + id;
    return this.http.get<OrcamentoProposta>(url);
  }
  save(objeto: OrcamentoProposta): Observable<OrcamentoProposta> {
    let url = this.apiUrl;
    if (objeto.idOrcamentoProposta) {
      return this.http.put<OrcamentoProposta>(url, objeto);
    } else {
      return this.http.post<OrcamentoProposta>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
