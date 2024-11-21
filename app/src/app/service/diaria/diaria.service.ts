import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { Diarias } from '../../model/Diarias';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DiariaService {

  constructor(
    private http: HttpClient
  ) { }

  apiUrl: string = environment.API_URL + '/diarias/'

  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<Diarias>> {
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
    return this.http.get<RespostaPaginada<Diarias>>(url);
  }
  
  getById(id: number): Observable<Diarias> {
    let url = this.apiUrl + id;
    return this.http.get<Diarias>(url);
  }
  save(objeto: Diarias): Observable<Diarias> {
    let url = this.apiUrl;
    if (objeto.idDiaria) {
      return this.http.put<Diarias>(url, objeto);
    } else {
      return this.http.post<Diarias>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
