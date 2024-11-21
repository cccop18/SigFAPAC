import { Component, OnInit, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { CadastroDePesquisadorService } from '../../service/cadastrarPesquisador/cadastro-de-pesquisador.service';

@Component({
  selector: 'app-cadastro-dados-es',
  standalone: true,
  imports: [CommonModule, RouterLink, ReactiveFormsModule], // Adiciona ReactiveFormsModule aqui
  templateUrl: './cadastro-dados-es.component.html',
  styleUrls: ['./cadastro-dados-es.component.css'],
})
export class CadastroDadosEsComponent implements OnInit {
  dadosPessoaisForm: FormGroup = this.fb.group({});
  estados: Array<{ id: number; nome: string }> = [];
  rgFile: File | null = null;
  imageFile: File | null = null;

  constructor(
    private fb: FormBuilder,
    private cadastroService: CadastroDePesquisadorService,
    private router: Router,
    @Inject(PLATFORM_ID) private platformId: object
  ) {}

  ngOnInit(): void {
    // Inicialização do formulário com campos e validações
    this.dadosPessoaisForm = this.fb.group({
      nomeCompleto: ['', Validators.required],
      passaport: ['', Validators.required],
      rg: ['', Validators.required],
      rgFile: [null, Validators.required],
      email: ['', [Validators.required, Validators.email]],
      sexo: ['', Validators.required],
      dataNascimento: ['', Validators.required],
      racaCor: [''],
      nomeMae: ['', Validators.required],
      nomePai: [''],
      linkLattes: ['', Validators.required],
      nivelAcademico: ['', Validators.required],
      imageFile: [null]
    });

    this.loadStates();
  }

  // Carregar os estados do IBGE
  async loadStates(): Promise<void> {
    try {
      const response = await fetch('https://servicodados.ibge.gov.br/api/v1/localidades/estados');
      this.estados = await response.json();
      this.estados.sort((a, b) => a.nome.localeCompare(b.nome));
    } catch (error) {
      console.error('Erro ao carregar os estados:', error);
    }
  }

  // Função para salvar dados pessoais no serviço
  salvarDadosPessoais(): void {
    if (this.dadosPessoaisForm.valid) {
      // Armazena os dados pessoais no serviço, junto com os arquivos
      this.cadastroService.setDados('dadosPessoais', this.dadosPessoaisForm.value);
      this.cadastroService.setArquivo('rgFile', this.rgFile); // Armazena o PDF do RG no serviço
      this.cadastroService.setArquivo('imageFile', this.imageFile); // Armazena a imagem de perfil no serviço

      console.log('Dados pessoais armazenados no serviço:', this.dadosPessoaisForm.value);
      console.log('Arquivo PDF RG armazenado no serviço:', this.rgFile);
      console.log('Imagem de perfil armazenada no serviço:', this.imageFile);

      // Navegar para o próximo componente (ex: área de conhecimento ou outra etapa)
      this.router.navigate(['cadastro/area/conhecimento']); // Altere para a rota correta da próxima etapa
    }
  }

  // Função de captura do arquivo PDF do RG
  handleRgUpload(event: Event): void {
    if (isPlatformBrowser(this.platformId)) {
      const inputElement = event.target as HTMLInputElement | null;
      const file = inputElement?.files?.[0] || null;
      this.rgFile = file; // Armazena o arquivo RG em PDF no componente
    }
  }

  // Função de captura da imagem (foto do pesquisador)
  handleImageUpload(event: Event): void {
    if (isPlatformBrowser(this.platformId)) {
      const inputElement = event.target as HTMLInputElement | null;
      const file = inputElement?.files?.[0] || null;
      this.imageFile = file; // Armazena a imagem de perfil no componente

      // Exibe o preview da imagem
      const preview = document.getElementById('preview');
      if (file && preview) {
        const reader = new FileReader();
        reader.onload = (e: ProgressEvent<FileReader>) => {
          if (e.target) {
            preview.innerHTML = `<img src="${e.target.result}" style="width: 120px" alt="Preview da Imagem" />`;
          }
        };
        reader.readAsDataURL(file);
      }
    }
  }
}
