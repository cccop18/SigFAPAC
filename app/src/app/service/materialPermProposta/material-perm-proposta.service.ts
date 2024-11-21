import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IService } from '../i-service';
import { MaterialPermProposta } from '../../model/MaterialPermProposta';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MaterialPermPropostaService implements IService<MaterialPermProposta>{

  constructor(
    private http: HttpClient
  ) { }

  apiUrl: string = environment.API_URL + '/materialpermproposta/';

  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<MaterialPermProposta>> {
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
    return this.http.get<RespostaPaginada<MaterialPermProposta>>(url);
  }
  getById(id: number): Observable<MaterialPermProposta> {
    let url = this.apiUrl + id;
    return this.http.get<MaterialPermProposta>(url);
  }
  save(objeto: MaterialPermProposta): Observable<MaterialPermProposta> {
    let url = this.apiUrl;
    if (objeto.idMaterialPermProposta) {
      return this.http.put<MaterialPermProposta>(url, objeto);
    } else {
      return this.http.post<MaterialPermProposta>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
