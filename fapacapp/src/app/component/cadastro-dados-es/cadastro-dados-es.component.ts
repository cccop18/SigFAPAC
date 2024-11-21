import { Component, OnInit, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-cadastro-dados-es',
  standalone: true,
  imports: [CommonModule, RouterLink, ReactiveFormsModule],
  templateUrl: './cadastro-dados-es.component.html',
  styleUrls: ['./cadastro-dados-es.component.css'],
})
export class CadastroDadosEsComponent implements OnInit {
  pesquisadorForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    @Inject(PLATFORM_ID) private platformId: object
  ) {
    this.pesquisadorForm = this.fb.group({
      nomeCompleto: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
    });
  }

  ngOnInit(): void {}

  // Função para lidar com upload de imagem
  handleImageUpload(event: Event): void {
    if (isPlatformBrowser(this.platformId)) {
      const inputElement = event.target as HTMLInputElement | null;
      const file = inputElement?.files?.[0];
      const preview = document.getElementById('preview');

      if (file && preview) {
        const reader = new FileReader();
        reader.onload = (e: ProgressEvent<FileReader>) => {
          if (e.target) {
            preview.innerHTML = `<img src="${e.target.result}"  style="width: 120px;" alt="Preview da Imagem" />`;
          }
        };
        reader.readAsDataURL(file);
      } else {
      }
    }
  }
}
