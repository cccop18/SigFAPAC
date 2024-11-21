import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';
import { Observable } from 'rxjs';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { Estado } from '../../model/Estado';

@Injectable({
  providedIn: 'root'
})
export class EstadoService {

  constructor(private http: HttpClient) {}

  apiUrl: string = environment.API_URL + '/estado/';

  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<Estado>> {
    let params = new HttpParams();
    
    // Adiciona termo de busca, se fornecido
    if (termoBusca) {
      params = params.set('termoBusca', termoBusca);
    }

    // Adiciona parâmetros de paginação, se fornecidos
    if (paginacao) {
      params = params.set('page', paginacao.page.toString());
      params = params.set('size', paginacao.size.toString());
      
      // Verifica se há campos de ordenação antes de iterar
      if (paginacao.sort && paginacao.sort.length > 0) {
        paginacao.sort.forEach(campo => {
          params = params.append('sort', campo); // append para múltiplos valores
        });
      }
    } else {
      // Caso não haja paginação, desabilita paginação no servidor
      params = params.set('unpaged', 'true');
    }

    // Realiza a requisição HTTP
    return this.http.get<RespostaPaginada<Estado>>(this.apiUrl, { params });
  }
}
