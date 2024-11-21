import { Injectable } from '@angular/core';
import { IService } from '../i-service';
import { PessoalProposta } from '../../model/PessoalProposta';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PessoalPropostaService implements IService<PessoalProposta> {

  constructor(
    private http: HttpClient
  ) { }

  apiUrl: string = environment.API_URL + '/pessoalproposta/';
  
  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<PessoalProposta>> {
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
    return this.http.get<RespostaPaginada<PessoalProposta>>(url);
  }
  getById(id: number): Observable<PessoalProposta> {
    let url = this.apiUrl + id;
    return this.http.get<PessoalProposta>(url);
  }
  save(objeto: PessoalProposta): Observable<PessoalProposta> {
    let url = this.apiUrl;
    if (objeto.idPessoalProposta) {
      return this.http.put<PessoalProposta>(url, objeto);
    } else {
      return this.http.post<PessoalProposta>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
