import { Component, OnInit, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-cadastro-dados-br',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule],
  templateUrl: './cadastro-dados-br.component.html',
  styleUrls: ['./cadastro-dados-br.component.css'],
})

export class CadastroDadosBrComponent implements OnInit {
  estados: Array<{ id: number; nome: string }> = [];
  municipios: Array<{ id: number; nome: string }> = [];

  constructor(@Inject(PLATFORM_ID) private platformId: object) {}

  ngOnInit() {
    this.loadStates();
  }

  // Carregar os estados ao iniciar
  async loadStates(): Promise<void> {
    try {
      const response = await fetch(
        'https://servicodados.ibge.gov.br/api/v1/localidades/estados'
      );
      this.estados = await response.json();
      this.estados.sort((a, b) => a.nome.localeCompare(b.nome));
    } catch (error) {
      console.error('Erro ao carregar os estados:', error);
    }
  }

  // Função para lidar com upload de imagem (somente no navegador)
  handleImageUpload(event: Event): void {
    if (isPlatformBrowser(this.platformId)) {
      const inputElement = event.target as HTMLInputElement | null;
      const file = inputElement?.files?.[0];
      const preview = document.getElementById('preview');

      if (file && preview) {
        const reader = new FileReader();
        reader.onload = (e: ProgressEvent<FileReader>) => {
          if (e.target) {
            preview.innerHTML = `<img src="${e.target.result}" style="width: 120px "alt="Preview da Imagem" />`;
          }
        };
        reader.readAsDataURL(file);
      }
    }
  }
}
