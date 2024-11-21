import { Injectable } from '@angular/core';
import { IService } from '../i-service';
import { MaterialProposta } from '../../model/MaterialProposta';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MaterialPropostaService implements IService<MaterialProposta> {

  constructor(
    private http: HttpClient
  ) { }

  apiUrl: string = environment.API_URL + '/materialproposta/';
  
  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<MaterialProposta>> {
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
    return this.http.get<RespostaPaginada<MaterialProposta>>(url);
  }
  getById(id: number): Observable<MaterialProposta> {
    let url = this.apiUrl + id;
    return this.http.get<MaterialProposta>(url);
  }
  save(objeto: MaterialProposta): Observable<MaterialProposta> {
    let url = this.apiUrl;
    if (objeto.idMaterialProposta) {
      return this.http.put<MaterialProposta>(url, objeto);
    } else {
      return this.http.post<MaterialProposta>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
