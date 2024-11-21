import { Injectable } from '@angular/core';
import { IService } from '../i-service';
import { PropostaConhecimento } from '../../model/PropostaConhecimento';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PropostaConhecimentoService implements IService<PropostaConhecimento>{

  constructor(
    private http: HttpClient
  ) { }
  
  apiUrl: string = environment.API_URL + '/PropostaConhecimentos/'
  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<PropostaConhecimento>> {
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
    return this.http.get<RespostaPaginada<PropostaConhecimento>>(url);
  }
  getById(id: number): Observable<PropostaConhecimento> {
    let url = this.apiUrl + id;
    return this.http.get<PropostaConhecimento>(url);
  }
  save(objeto: PropostaConhecimento): Observable<PropostaConhecimento> {
    let url = this.apiUrl;
    if (objeto.idPropostaConhecimento) {
      return this.http.put<PropostaConhecimento>(url, objeto);
    } else {
      return this.http.post<PropostaConhecimento>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
