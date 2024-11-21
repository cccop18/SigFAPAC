import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AreaConhecimentoService {
  //http://localhost:9000/areaConhecimento/
  //http://localhost:9000/areaConhecimento/1/primeiraSubArea
  //http://localhost:9000/areaConhecimento/primeiraSubArea/1/segundaSubArea
  //http://localhost:9000/areaConhecimento/segundaSubArea/1/terceiraSubArea

  private baseUrl = 'http://localhost:9000/areaConhecimento'; // URL base da API

  constructor(private http: HttpClient) {}

  // Buscar todas as áreas de conhecimento
  getAreasConhecimento(): Observable<any> {
    return this.http.get(`${this.baseUrl}/`);
  }

  // Buscar primeiras subáreas por ID de área de conhecimento
  getPrimeiraSubAreas(areaId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${areaId}/primeiraSubArea`);
  }

  // Buscar segundas subáreas por ID de primeira subárea
  getSegundaSubAreas(primeiraSubAreaId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/primeiraSubArea/${primeiraSubAreaId}/segundaSubArea`);
  }

  // Buscar terceiras subáreas por ID de segunda subárea
  getTerceiraSubAreas(segundaSubAreaId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/segundaSubArea/${segundaSubAreaId}/terceiraSubArea`);
  }
}
