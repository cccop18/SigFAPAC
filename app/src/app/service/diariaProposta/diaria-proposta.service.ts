import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IService } from '../i-service';
import { DiariaProposta } from '../../model/DiariaProposta';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DiariaPropostaService implements IService<DiariaProposta>{

  constructor(
    private http: HttpClient
  ) { }

  apiUrl: string = environment.API_URL + '/diariaproposta/'
  
  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<DiariaProposta>> {
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
    return this.http.get<RespostaPaginada<DiariaProposta>>(url);
  }
  getById(id: number): Observable<DiariaProposta> {
    let url = this.apiUrl + id;
    return this.http.get<DiariaProposta>(url);
  }
  save(objeto: DiariaProposta): Observable<DiariaProposta> {
    let url = this.apiUrl;
    if (objeto.idDiariaProposta) {
      return this.http.put<DiariaProposta>(url, objeto);
    } else {
      return this.http.post<DiariaProposta>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
