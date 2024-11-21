import { Component } from '@angular/core';
import { IForm } from '../../i-form';
import { Arquivo } from '../../../model/Arquivo';
import { ArquivoService } from '../../../service/arquivo/arquivo.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ETipoAlerta } from '../../../model/ETipoAlerta';
import { AlertaService } from '../../../service/alerta/alerta.service';

@Component({
  selector: 'app-arquivo-form',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule],
  templateUrl: './arquivo-form.component.html',
  styleUrl: './arquivo-form.component.css'
})
export class ArquivoFormComponent implements IForm<Arquivo> {

  constructor(
    private servico: ArquivoService,
    private servicoAlerta: AlertaService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit(): void {

    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      this.servico.getById(+id).subscribe({
        next: (resposta: Arquivo) => {
          console.log(resposta);
          this.registro = resposta;
          this.formArquivo.patchValue(this.registro);
        }
      });
    }

  }

  formArquivo = new FormGroup({
    idArquivo: new FormControl<number | null>(null),
    nomeArquivo: new FormControl<string | null>(null),
    fileArquivo: new FormControl<string | null>(null), // TODO: Verificar se é realmente string e se é fileArquivo ou ativoArquivo
    ativoArquivo: new FormControl<boolean | null>(null)
  });

  get form() {
    return this.formArquivo.controls;
  }

  registro: Arquivo = <Arquivo>{};

  save(): void {
    this.registro = Object.assign(this.registro, this.formArquivo.value);

    // Captura o arquivo do input com id 'fileArquivo'
    const fileInput: HTMLInputElement = document.getElementById('fileArquivo') as HTMLInputElement;
    const file: File | null = fileInput.files?.item(0) ?? null;

    if (file) {
      // Envia o arquivo junto com o objeto 'registro'
      this.servico.save(this.registro, file).subscribe({
        complete: () => {
          this.router.navigate(['/arquivos']);
          this.servicoAlerta.enviarAlerta({
            tipo: ETipoAlerta.SUCESSO,
            mensagem: "Operação realizada com sucesso!"
          });
        }
      });
    }
  }



}
