import { Injectable } from '@angular/core';
import { IService } from './i-service';
import { VinculoInstitucional } from '../model/VinculoInstitucional';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../model/RequisicaoPaginada';
import { RespostaPaginada } from '../model/RespostaPaginada';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class VinculoInstitucionalService implements IService <VinculoInstitucional>{

  constructor(
    private http: HttpClient
  ) { }

  apiUrl: string = environment.API_URL + '/vinculoInstitucional/';

  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<VinculoInstitucional>> {
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
    return this.http.get<RespostaPaginada<VinculoInstitucional>>(url);
  }
  getById(id: number): Observable<VinculoInstitucional> {
    let url = this.apiUrl + id;
    return this.http.get<VinculoInstitucional>(url);
  }
  save(objeto: VinculoInstitucional): Observable<VinculoInstitucional> {
    let url = this.apiUrl;
    if (objeto.idVinculoInstitucional) {
      return this.http.put<VinculoInstitucional>(url, objeto);
    } else {
      return this.http.post<VinculoInstitucional>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
