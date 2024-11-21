import { Injectable } from '@angular/core';
import { IService } from '../i-service';
import { ParceriasProposta } from '../../model/ParceriasProposta';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ParceriasPropostaService implements IService<ParceriasProposta> {

  constructor(
    private http: HttpClient
  ) { }

 apiUrl: string = environment.API_URL + '/parceriasProposta/';
 
  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<ParceriasProposta>> {
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
    return this.http.get<RespostaPaginada<ParceriasProposta>>(url);
  }
  getById(id: number): Observable<ParceriasProposta> {
    let url = this.apiUrl + id;
    return this.http.get<ParceriasProposta>(url);
  }
  save(objeto: ParceriasProposta): Observable<ParceriasProposta> {
    let url = this.apiUrl;
    if (objeto.idParceriasProposta) {
      return this.http.put<ParceriasProposta>(url, objeto);
    } else {
      return this.http.post<ParceriasProposta>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
