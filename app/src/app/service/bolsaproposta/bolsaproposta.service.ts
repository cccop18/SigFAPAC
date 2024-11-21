import { Injectable } from '@angular/core';
import { IService } from '../i-service';
import { BolsaProposta } from '../../model/BolsaProposta';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';
import { Observable } from 'rxjs';
import { RespostaPaginada } from '../../model/RespostaPaginada';

@Injectable({
  providedIn: 'root'
})
export class BolsapropostaService implements IService<BolsaProposta> {

  constructor(
    private http: HttpClient
  )
   { }
   apiUrl: string = environment.API_URL + '/bolsaproposta/';

   get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<BolsaProposta>> {
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
    return this.http.get<RespostaPaginada<BolsaProposta>>(url);
  }
  getById(id: number): Observable<BolsaProposta> {
    let url = this.apiUrl + id;
    return this.http.get<BolsaProposta>(url);
  }
  save(objeto: BolsaProposta): Observable<BolsaProposta> {
    let url = this.apiUrl;
    if (objeto.idBolsaProposta) {
      return this.http.put<BolsaProposta>(url, objeto);
    } else {
      return this.http.post<BolsaProposta>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}


