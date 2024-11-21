import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { EditalDados } from '../../model/EditalDados';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';

@Injectable({
  providedIn: 'root'
})
export class EditalDadosService {
  constructor(
    private http: HttpClient
  ) { }

  apiUrl: string = environment.API_URL + '/editalDados/'

  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<EditalDados>> {
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
    return this.http.get<RespostaPaginada<EditalDados>>(url);
  }
  getById(id: number): Observable<EditalDados> {
    let url = this.apiUrl + id;
    return this.http.get<EditalDados>(url);
  }
  save(arquivo: EditalDados, file: File): Observable<EditalDados> {
    const formData: FormData = new FormData();
    formData.append('objeto', new Blob([JSON.stringify(arquivo)], { type: 'application/json' }));
    formData.append('file', file);
    console.log("Método save chamado");
  
    return this.http.post<EditalDados>(`${this.apiUrl}`, formData);
  }
  
  // Método para atualizar (PUT) sem necessidade de arquivo
  update(arquivo: EditalDados, file: File | null): Observable<EditalDados> {
    const formData: FormData = new FormData();
    formData.append('objeto', new Blob([JSON.stringify(arquivo)], { type: 'application/json' }));
    if (file) {
      formData.append('file', file);
    }
    console.log("Método update chamado");

    return this.http.put<EditalDados>(`${this.apiUrl}`, formData);
  }
  
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
