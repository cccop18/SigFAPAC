import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { EditalRestricoes } from '../../model/EditalRestricoes';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';

@Injectable({
  providedIn: 'root'
})
export class EditalRestricoesService {

  constructor(
    private http: HttpClient
  ) { }

  apiUrl: string = environment.API_URL + '/editalRestricoes/'

  get(termoBusca?: boolean, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<EditalRestricoes>> {
    let url = this.apiUrl + "?";
    if (termoBusca) {
      url += "termoBusca=" + termoBusca;
    }
    if (paginacao){
      url += "&page=" + paginacao.page;
      url += "size=" + paginacao.size;
      paginacao.sort.forEach(campo => {
        url += "&sort=" + campo;
      });
    } else {
      url += "&unpaged=true";
    }
    return this.http.get<RespostaPaginada<EditalRestricoes>>(url);
  }

  getById(id: number): Observable<EditalRestricoes>{
    let url = this.apiUrl + id;
    return this.http.get<EditalRestricoes>(url);
  }

  save(objeto: EditalRestricoes): Observable<EditalRestricoes> {
    let url = this.apiUrl;
    if (objeto.idEdital) {
      console.log("Método update chamado");
      return this.http.put<EditalRestricoes>(url, objeto);
    } else {
      console.log("Método save chamado");
      return this.http.post<EditalRestricoes>(url, objeto);
    }
  }

  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }

}
