import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IService } from './i-service';
import { PesquisadorDto } from '../model/PesquisadorDto';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../model/RequisicaoPaginada';
import { RespostaPaginada } from '../model/RespostaPaginada';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PesquisadorDtoService implements IService<PesquisadorDto>{

  constructor(
    private http: HttpClient
  ) { }
   apiUrl: string = environment.API_URL + '/pesquisadorDto/'

  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<PesquisadorDto>> {
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
    return this.http.get<RespostaPaginada<PesquisadorDto>>(url);
  }
  getById(id: number): Observable<PesquisadorDto> {
    let url = this.apiUrl + id;
    return this.http.get<PesquisadorDto>(url);
  }
  save(objeto: PesquisadorDto): Observable<PesquisadorDto> {
    let url = this.apiUrl;
    if (objeto.idPesquisador) {
      return this.http.put<PesquisadorDto>(url, objeto);
    } else {
      return this.http.post<PesquisadorDto>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
