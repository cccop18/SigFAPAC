import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';
import { Observable } from 'rxjs';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { DocPessoalEdital } from '../../model/DocPessoalEdital';

@Injectable({
  providedIn: 'root'
})
export class DocpessoaleditService {

  constructor(
    private http: HttpClient
  ) { }

   apiUrl: string = environment.API_URL + '/docpessoaledital/'

   get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<DocPessoalEdital>> {
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
    return this.http.get<RespostaPaginada<DocPessoalEdital>>(url);
  }
  getById(id: number): Observable<DocPessoalEdital> {
    let url = this.apiUrl + id;
    return this.http.get<DocPessoalEdital>(url);
  }
  save(objeto: DocPessoalEdital): Observable<DocPessoalEdital> {
    let url = this.apiUrl;
    if (objeto.idDocPessoalEdital) {
      return this.http.put<DocPessoalEdital>(url, objeto);
    } else {
      return this.http.post<DocPessoalEdital>(url, objeto);
    }
  } 
  
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}




