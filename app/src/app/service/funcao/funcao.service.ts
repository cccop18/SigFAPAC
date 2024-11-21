import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { Funcao } from '../../model/Funcao';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';

@Injectable({
  providedIn: 'root'
})
export class FuncaoService {

  constructor(private http: HttpClient) { }

  apiUrl: string = environment.API_URL + '/funcao/';

  // Método para buscar funções com termo de busca e paginação
  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<Funcao>> {
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
    return this.http.get<RespostaPaginada<Funcao>>(url);
  }

  // Método para buscar função por ID
  getById(id: number): Observable<Funcao> {
    let url = this.apiUrl + id;
    return this.http.get<Funcao>(url);
  }

  // Método para salvar ou atualizar função
  save(objeto: Funcao): Observable<Funcao> {
    let url = this.apiUrl;
    if (objeto.idFuncao) {
      return this.http.put<Funcao>(url, objeto);
    } else {
      return this.http.post<Funcao>(url, objeto);
    }
  }

  // Método para deletar função por ID
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
