import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { EditalChamadas } from '../../model/EditalChamadas';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';

@Injectable({
  providedIn: 'root'
})
export class EditalChmadasService {

  constructor(
    private http: HttpClient
  ) { }

  apiUrl: string = environment.API_URL + '/editalChamadas/'

  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<EditalChamadas>> {
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
    return this.http.get<RespostaPaginada<EditalChamadas>>(url);
  }
  getById(id: number): Observable<EditalChamadas> {
    let url = this.apiUrl + id;
    return this.http.get<EditalChamadas>(url);
  }
  save(objeto: EditalChamadas): Observable<EditalChamadas> {
    let url = this.apiUrl;
    if (objeto.idEdital) {
      console.log("Método update chamado");
      return this.http.put<EditalChamadas>(url, objeto);
    } else {
      console.log("Método save chamado");
      return this.http.post<EditalChamadas>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
