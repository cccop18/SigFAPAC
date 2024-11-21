import { Injectable } from '@angular/core';
import { IService } from '../i-service';
import { ArquivoEdital } from '../../model/ArquivoEdital';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ArquivoeditalService implements IService<ArquivoEdital>{

  constructor(
    private http: HttpClient
  ) { }
  apiUrl: string = environment.API_URL + '/arquivoEdital/'

  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<ArquivoEdital>> {
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
    return this.http.get<RespostaPaginada<ArquivoEdital>>(url);
  }
  getById(id: number): Observable<ArquivoEdital> {
    let url = this.apiUrl + id;
    return this.http.get<ArquivoEdital>(url);
  }
  save(objeto: ArquivoEdital): Observable<ArquivoEdital> {
    let url = this.apiUrl;
    if (objeto.idArquivoEdital) {
      return this.http.put<ArquivoEdital>(url, objeto);
    } else {
      return this.http.post<ArquivoEdital>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
