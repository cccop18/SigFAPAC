import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IService } from './i-service';
import { UnidadeInstituicaoDto } from '../model/UnidadeInstituicaoDto';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../model/RequisicaoPaginada';
import { RespostaPaginada } from '../model/RespostaPaginada';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UnidadeInstituicaoDtoService implements IService<UnidadeInstituicaoDto> {

  constructor(
    private http: HttpClient
  ) { }

  apiUrl: string = environment.API_URL + '/unidadesInstituicao/';

  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<UnidadeInstituicaoDto>> {
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
    return this.http.get<RespostaPaginada<UnidadeInstituicaoDto>>(url);
  }
  getById(id: number): Observable<UnidadeInstituicaoDto> {
    let url = this.apiUrl + id;
    return this.http.get<UnidadeInstituicaoDto>(url);
  }
  save(objeto: UnidadeInstituicaoDto): Observable<UnidadeInstituicaoDto> {
    let url = this.apiUrl;
    if (objeto.idUnidadeInstituicao) {
      return this.http.put<UnidadeInstituicaoDto>(url, objeto);
    } else {
      return this.http.post<UnidadeInstituicaoDto>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
