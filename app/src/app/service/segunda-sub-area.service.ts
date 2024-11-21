import { Injectable } from '@angular/core';
import { IService } from './i-service';
import { SegundaSubArea } from '../model/SegundaSubArea';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../model/RequisicaoPaginada';
import { RespostaPaginada } from '../model/RespostaPaginada';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SegundaSubAreaService implements IService<SegundaSubArea>{

  constructor(
    private http: HttpClient
  ) { }

  apiUrl: string = environment.API_URL + '/segundaSubArea/';
  
  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<SegundaSubArea>> {
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
    return this.http.get<RespostaPaginada<SegundaSubArea>>(url);
  }
  getById(id: number): Observable<SegundaSubArea> {
    let url = this.apiUrl + id;
    return this.http.get<SegundaSubArea>(url);
  }
  save(objeto: SegundaSubArea): Observable<SegundaSubArea> {
    let url = this.apiUrl;
    if (objeto.idSegundaSubArea) {
      return this.http.put<SegundaSubArea>(url, objeto);
    } else {
      return this.http.post<SegundaSubArea>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
