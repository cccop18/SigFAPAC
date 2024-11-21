import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IService } from '../i-service';
import { PassagemProposta } from '../../model/PassagemProposta';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PassagemPropostaService implements IService<PassagemProposta>{

  constructor(
    private http: HttpClient
  ) { }
  apiUrl: string = environment.API_URL + '/passagemproposta/';
  
  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<PassagemProposta>> {
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
    return this.http.get<RespostaPaginada<PassagemProposta>>(url);
  }
  getById(id: number): Observable<PassagemProposta> {
    let url = this.apiUrl + id;
    return this.http.get<PassagemProposta>(url);
  }
  save(objeto: PassagemProposta): Observable<PassagemProposta> {
    let url = this.apiUrl;
    if (objeto.idPassagemProposta) {
      return this.http.put<PassagemProposta>(url, objeto);
    } else {
      return this.http.post<PassagemProposta>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
