// faixafinanciamento.service.ts
import { Injectable } from '@angular/core';
import { FaixaFinanciamento } from '../../model/FaixaFinanciamento';
import { IService } from '../i-service';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';
import { Observable } from 'rxjs';
import { RespostaPaginada } from '../../model/RespostaPaginada';

@Injectable({
  providedIn: 'root'
})
export class FaixaFinanciamentoService implements IService<FaixaFinanciamento> {

  constructor(
    private http: HttpClient
  ) { }

  apiUrl: string = environment.API_URL + '/faixafinanciamento/';

  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<FaixaFinanciamento>> {
    let params = new HttpParams();

    if (termoBusca) {
      params = params.set('termoBusca', termoBusca);
    }
    if (paginacao) {
      params = params.set('page', paginacao.page.toString());
      params = params.set('size', paginacao.size.toString());
      paginacao.sort.forEach(campo => {
        params = params.append('sort', campo);
      });
    } else {
      params = params.set('unpaged', 'true');
    }

    return this.http.get<RespostaPaginada<FaixaFinanciamento>>(this.apiUrl, { params });
  }

  getById(id: number): Observable<FaixaFinanciamento> {
    return this.http.get<FaixaFinanciamento>(`${this.apiUrl}${id}`);
  }

  save(objeto: FaixaFinanciamento): Observable<FaixaFinanciamento> {
    if (objeto.idFaixaFinanciamento) {
      console.log("Update chamado:" ,objeto);
      return this.http.put<FaixaFinanciamento>(`${this.apiUrl}${objeto.idFaixaFinanciamento}`, objeto);
    } else {
      console.log("Save chamado:" ,objeto);
      return this.http.post<FaixaFinanciamento>(this.apiUrl, objeto);
    }
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}${id}`);
  }

  getByEditalId(idEdital: number): Observable<RespostaPaginada<FaixaFinanciamento>> {
    const params = new HttpParams().set('idEdital', idEdital.toString());
    return this.http.get<RespostaPaginada<FaixaFinanciamento>>(this.apiUrl, { params });
  }
}
