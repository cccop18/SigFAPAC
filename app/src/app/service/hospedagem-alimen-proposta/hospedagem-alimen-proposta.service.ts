import { Injectable } from '@angular/core';
import { IService } from '../i-service';
import { HospedagemAlimentacaoProposta } from '../../model/HospedagemAlimentacaoProposta';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';
import { Observable } from 'rxjs';
import { RespostaPaginada } from '../../model/RespostaPaginada';

@Injectable({
  providedIn: 'root'
})
export class HospedagemAlimenPropostaService implements IService<HospedagemAlimentacaoProposta> {



  constructor(
     private http:  HttpClient
  ) { }

  apiUrl: string = environment.API_URL + '/hospedagemalimentacaoproposta/'

  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<HospedagemAlimentacaoProposta>> {
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
    return this.http.get<RespostaPaginada<HospedagemAlimentacaoProposta>>(url);
  }

  getById(id: number): Observable<HospedagemAlimentacaoProposta> {
    let url = this.apiUrl + id;
    return this.http.get<HospedagemAlimentacaoProposta>(url);
  }
  save(objeto: HospedagemAlimentacaoProposta): Observable<HospedagemAlimentacaoProposta> {
    let url = this.apiUrl;
    if (objeto.idHospedagemAlimentacaoProposta) {
      return this.http.put<HospedagemAlimentacaoProposta>(url, objeto);
    } else {
      return this.http.post<HospedagemAlimentacaoProposta>(url, objeto);
    }
  }

  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
