import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IService } from './i-service';
import { PesquisadorEsDto } from '../model/PesquisadorEsDto';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../model/RequisicaoPaginada';
import { RespostaPaginada } from '../model/RespostaPaginada';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PesquisadorEsDtoService  implements IService<PesquisadorEsDto> {

  constructor(
    private http: HttpClient
  ) { }
  apiUrl: string = environment.API_URL + '/pesquisadorEs/';

  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<PesquisadorEsDto>> {
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
    return this.http.get<RespostaPaginada<PesquisadorEsDto>>(url);
  }
  getById(id: number): Observable<PesquisadorEsDto> {
    let url = this.apiUrl + id;
    return this.http.get<PesquisadorEsDto>(url);
  }
  save(objeto: PesquisadorEsDto): Observable<PesquisadorEsDto> {
    let url = this.apiUrl;
    if (objeto.idPesquisador) {
      return this.http.put<PesquisadorEsDto>(url, objeto);
    } else {
      return this.http.post<PesquisadorEsDto>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
