import { Injectable } from '@angular/core';
import { IService } from '../i-service';
import { FuncaoEdital } from '../../model/FuncaoEdital';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FuncaoEditalService implements IService<FuncaoEdital>{

  constructor(
    private http: HttpClient
  ) { }

  
  apiUrl: string = environment.API_URL + '/funcaoEdital/';
  
  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<FuncaoEdital>> {
    let url = this.apiUrl;
    const params: string[] = [];

    // Adiciona termo de busca, se fornecido
    if (termoBusca) {
      params.push(`termoBusca=${termoBusca}`);
    }

    // Adiciona parâmetros de paginação, se fornecidos
    if (paginacao) {
      params.push(`page=${paginacao.page}`);
      params.push(`size=${paginacao.size}`);

      // Verifica se há campos de ordenação antes de iterar
      if (paginacao.sort && paginacao.sort.length > 0) {
        paginacao.sort.forEach(campo => {
          params.push(`sort=${campo}`);
        });
      }
    } else {
      // Caso não haja paginação, desabilita paginação no servidor
      params.push('unpaged=true');
    }

    // Concatena os parâmetros na URL
    if (params.length > 0) {
      url += '?' + params.join('&');
    }

    // Realiza a requisição HTTP
    return this.http.get<RespostaPaginada<FuncaoEdital>>(url);
  }
  getById(id: number): Observable<FuncaoEdital> {
    let url = this.apiUrl + id;
    return this.http.get<FuncaoEdital>(url);
  }
  save(objeto: FuncaoEdital): Observable<FuncaoEdital> {
    let url = this.apiUrl;
    if (objeto.idFuncaoEdital) {
      return this.http.put<FuncaoEdital>(url, objeto);
    } else {
      return this.http.post<FuncaoEdital>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
