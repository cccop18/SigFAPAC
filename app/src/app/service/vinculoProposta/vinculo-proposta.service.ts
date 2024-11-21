import { Injectable } from '@angular/core';
import { IService } from '../i-service';
import { VinculoProposta } from '../../model/VinculoProposta';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class VinculoPropostaService implements IService<VinculoProposta> {

  constructor(
    private http: HttpClient
  ) { }
  apiUrl: string = environment.API_URL + '/vinculoProposta/';

  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<VinculoProposta>> {
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
    return this.http.get<RespostaPaginada<VinculoProposta>>(url);
  }
  getById(id: number): Observable<VinculoProposta> {
    let url = this.apiUrl + id;
    return this.http.get<VinculoProposta>(url);
  }
  save(objeto: VinculoProposta): Observable<VinculoProposta> {
    let url = this.apiUrl;
    if (objeto.idVinculoLoProposta) {
      return this.http.put<VinculoProposta>(url, objeto);
    } else {
      return this.http.post<VinculoProposta>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    throw new Error('Method not implemented.');
  }
}
