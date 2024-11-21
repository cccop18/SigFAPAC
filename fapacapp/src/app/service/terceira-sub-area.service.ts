import { Injectable } from '@angular/core';
import { IService } from './i-service';
import { TerceiraSubArea } from '../model/TerceiraSubArea';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../model/RequisicaoPaginada';
import { RespostaPaginada } from '../model/RespostaPaginada';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TerceiraSubAreaService implements IService<TerceiraSubArea> {

  constructor(
    private http: HttpClient
  ) { }

  apiUrl: string = environment.API_URL + '//';

  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<TerceiraSubArea>> {
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
    return this.http.get<RespostaPaginada<TerceiraSubArea>>(url);
  }
  getById(id: number): Observable<TerceiraSubArea> {
    let url = this.apiUrl + id;
    return this.http.get<TerceiraSubArea>(url);
  }
  save(objeto: TerceiraSubArea): Observable<TerceiraSubArea> {
    let url = this.apiUrl;
    if (objeto.id) {
      return this.http.put<TerceiraSubArea>(url, objeto);
    } else {
      return this.http.post<TerceiraSubArea>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
