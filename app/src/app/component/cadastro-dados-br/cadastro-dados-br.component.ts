import { Component, OnInit, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { FormsModule, ReactiveFormsModule, Validators, FormBuilder, FormGroup } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { CadastroDePesquisadorService } from '../../service/cadastrarPesquisador/cadastro-de-pesquisador.service';

@Component({
  selector: 'app-cadastro-dados-br',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule],
  templateUrl: './cadastro-dados-br.component.html',
  styleUrls: ['./cadastro-dados-br.component.css'],
})

export class CadastroDadosBrComponent implements OnInit {
  dadosPessoaisForm: FormGroup = this.fb.group({});
  estados: Array<{ id: number; nome: string }> = [];

  constructor(private fb: FormBuilder, private cadastroService: CadastroDePesquisadorService, @Inject(PLATFORM_ID) private platformId: object) {}

  ngOnInit(): void {
    this.dadosPessoaisForm = this.fb.group({
      nomeCompleto: ['', Validators.required],
      rg: ['', Validators.required],
      dataEmissao: ['', Validators.required],
      orgaoEmissor: ['', Validators.required],
      cpf: ['', [Validators.required, Validators.pattern('\\d{11}')]],
      uf: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      sexo: ['', Validators.required],
      dataNascimento: ['', Validators.required],
      racaCor: [''],
      nomeMae: ['', Validators.required],
      nomePai: [''],
      linkLattes: ['', Validators.required],
      nivelAcademico: ['', Validators.required]
    });

    this.loadStates();
  }

  // Carregar os estados
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

  // Salvar dados pessoais no serviço ao avançar
  salvarDadosPessoais(): void {
    if (this.dadosPessoaisForm.valid) {
      this.cadastroService.setDados('dadosPessoais', this.dadosPessoaisForm.value);
    }
  }

  // Função de envio de imagem (mesma que já foi definida)
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
