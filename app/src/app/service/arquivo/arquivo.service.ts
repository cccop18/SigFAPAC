import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IService } from '../i-service';
import { Arquivo } from '../../model/Arquivo';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ArquivoService {

  constructor(
    private http: HttpClient
  ) { }

  apiUrl: string = environment.API_URL + '/arquivo/'
  
  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<Arquivo>> {
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
    return this.http.get<RespostaPaginada<Arquivo>>(url);
  }
  getById(id: number): Observable<Arquivo> {
    let url = this.apiUrl + id;
    return this.http.get<Arquivo>(url);
  }
  save(arquivo: Arquivo, file: File): Observable<Arquivo> {
    const formData: FormData = new FormData();
    formData.append('objeto', new Blob([JSON.stringify(arquivo)], { type: 'application/json' }));
    formData.append('file', file);
  
    return this.http.post<Arquivo>(`${this.apiUrl}`, formData);
  }
  
  
  
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
