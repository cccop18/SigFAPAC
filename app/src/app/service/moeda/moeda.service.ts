import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';
import { Moeda } from '../../model/Moeda';

@Injectable({
  providedIn: 'root'
})
export class MoedaService {

  constructor(private http: HttpClient) { }

  apiUrl: string = environment.API_URL + '/moeda/';

  // Método para buscar moedas com termo de busca e paginação
  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<Moeda>> {
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
    return this.http.get<RespostaPaginada<Moeda>>(url);
  }

  // Método para buscar moeda por ID
  getById(id: number): Observable<Moeda> {
    let url = this.apiUrl + id;
    return this.http.get<Moeda>(url);
  }

  // Método para salvar ou atualizar função
  save(objeto: Moeda): Observable<Moeda> {
    let url = this.apiUrl;
    if (objeto.idMoeda) {
      return this.http.put<Moeda>(url, objeto);
    } else {
      return this.http.post<Moeda>(url, objeto);
    }
  }

  // Método para deletar moeda por ID
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
