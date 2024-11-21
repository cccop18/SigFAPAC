import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CadastroDePesquisadorService {
  private dadosCadastro: any = {
    dadosPessoais: {},
    endereco: {},
    areaConhecimento: {},
    dadosProfissionais: {},
    senhapesquisador: {},
    arquivos: {
    rgFile: null,
    imageFile: null
    }
  };

  private dadosCompletos = new BehaviorSubject<any>(this.dadosCadastro);

  constructor() {}

  setDados(parte: string, dados: any) {
    this.dadosCadastro[parte] = dados;
    this.dadosCompletos.next(this.dadosCadastro);
  }

  setArquivo(tipo: 'rgFile' | 'imageFile', arquivo: File | null) {
    this.dadosCadastro.arquivos[tipo] = arquivo;
  }

  getDados(): Observable<any> {
    return this.dadosCompletos.asObservable();
  }

  enviarCadastro(): void {
    console.log('Dados completos enviados para o backend:', this.dadosCadastro);
  }
}
