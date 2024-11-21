import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';
import { Observable } from 'rxjs';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { DocPessoal } from '../../model/DocPessoal';

@Injectable({
  providedIn: 'root'
})
export class DocpessoalService {

  constructor(
    private http: HttpClient
  ) { }


  apiUrl: string = environment.API_URL + '/documentoPessoal/'

  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<DocPessoal>> {
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
    return this.http.get<RespostaPaginada<DocPessoal>>(url);
  }
  getById(id: number): Observable<DocPessoal> {
    let url = this.apiUrl + id;
    return this.http.get<DocPessoal>(url);
  }
  save(objeto: DocPessoal): Observable<DocPessoal> {
    let url = this.apiUrl;
    if (objeto.idPessoais) {
      return this.http.put<DocPessoal>(url, objeto);
    } else {
      return this.http.post<DocPessoal>(url, objeto);
    }
  }
  
  
  
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}


