import { Injectable } from '@angular/core';
import { IService } from '../i-service';
import { PropostaTitulo } from '../../model/PropostaTitulo';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PropostaTituloService implements IService<PropostaTitulo>{

  constructor(
    private http: HttpClient
  ) { }

  apiUrl: string = environment.API_URL + '/propostastitulo/'
  
  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<PropostaTitulo>> {
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
    return this.http.get<RespostaPaginada<PropostaTitulo>>(url);
  }
  getById(id: number): Observable<PropostaTitulo> {
    let url = this.apiUrl + id;
    return this.http.get<PropostaTitulo>(url);
  }
  save(objeto: PropostaTitulo): Observable<PropostaTitulo> {
    let url = this.apiUrl;
    if (objeto.idProposta) {
      return this.http.put<PropostaTitulo>(url, objeto);
    } else {
      return this.http.post<PropostaTitulo>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
