import { Injectable } from '@angular/core';
import { IService } from '../i-service';
import { PesquisadorBrasileiroDto } from '../../model/PesquisadorBrasileiroDto';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../../model/RequisicaoPaginada';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { HttpClient } from '@angular/common/http';
import { VinculoInstitucional } from '../../model/VinculoInstitucional';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PesquisadorBrasileiroDtoService{

  constructor(
    private http: HttpClient
  ) { }

   // Armazena o vínculo institucional atual
   private vinculoInstitucional: VinculoInstitucional = {} as VinculoInstitucional;

   // Armazena uma lista de vínculos institucionais (caso necessário)
   private vinculosInstitucionais: VinculoInstitucional[] = [];
 
   // Método para adicionar um vínculo institucional
   adicionarVinculoInstitucional(vinculo: VinculoInstitucional): void {
     this.vinculoInstitucional = vinculo;
     this.vinculosInstitucionais.push(vinculo);
   }
   // Método para obter o vínculo institucional
  getVinculoInstitucional(): VinculoInstitucional {
    return this.vinculoInstitucional;
  }

  // Método para obter todos os vínculos institucionais (caso necessário)
  getVinculosInstitucionais(): VinculoInstitucional[] {
    return this.vinculosInstitucionais;
  }

  apiUrl: string = environment.API_URL + '/pesquisadorBr/';
  
  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<PesquisadorBrasileiroDto>> {
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
    return this.http.get<RespostaPaginada<PesquisadorBrasileiroDto>>(url);
  }
  getById(id: number): Observable<PesquisadorBrasileiroDto> {
    let url = this.apiUrl + id;
    return this.http.get<PesquisadorBrasileiroDto>(url);
  }
  save(objeto: PesquisadorBrasileiroDto, fileFoto: File): Observable<PesquisadorBrasileiroDto> {
    let url = this.apiUrl;
    const formData: FormData = new FormData();
    formData.append('objeto', new Blob([JSON.stringify(objeto)], { type: 'application/json' }));
    formData.append('fileFoto', fileFoto);
    if (objeto.idPesquisador) {
      return this.http.put<PesquisadorBrasileiroDto>(`${this.apiUrl}`, formData);
    } else {
      console.log(formData)
      return this.http.post<PesquisadorBrasileiroDto>(`${this.apiUrl}`, formData);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
