import { Component } from '@angular/core';
import { IForm } from '../../../i-form';
import { Retificacoes } from '../../../../model/Retificacoes';
import { RetificacoesService } from '../../../../service/retificacoes/retificacoes.service';
import { Edital } from '../../../../model/Edital';
import { EditalService } from '../../../../service/edital/edital.service';
import { AlertaService } from '../../../../service/alerta/alerta.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { RespostaPaginada } from '../../../../model/RespostaPaginada';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ETipoAlerta } from '../../../../model/ETipoAlerta';
import { CommonModule } from '@angular/common';
import { MenuEditalComponent } from "../../../menu-edital/menu-edital.component";

@Component({
  selector: 'app-retificacao-form',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule, MenuEditalComponent],
  templateUrl: './retificacao-form.component.html',
  styleUrl: './retificacao-form.component.css'
})
export class RetificacaoFormComponent {

  constructor(
    private servico: RetificacoesService,
    private servicoAlerta: AlertaService,
    private servicoEdital: EditalService,
    private router: Router,
    private route: ActivatedRoute,
    private rota: ActivatedRoute
  ) { }

  ngOnInit(): void {
      this.carregarDados();
  }
  carregarDados() {
    this.servicoEdital.get().subscribe({
      next: (resposta: RespostaPaginada<Edital>) => {
        this.editais = resposta.content
      }
    });

    // Pega o ID do edital da rota
    const editalId = this.rota.snapshot.queryParamMap.get('id');

    
    // Se houver um editalId, busca o objeto Edital e preenche o formulário
    if (editalId) {
      this.servicoEdital.getById(+editalId).subscribe({
        next: (edital: Edital) => {
          // Atribui o edital ao campo 'edital' no formulário
          this.formRetificacoes.patchValue({
            edital: edital
          });
        }
      });    
      


      this.servico.get().subscribe({
        next: (resposta: RespostaPaginada<Retificacoes>) => {
          this.retificacoes = resposta.content.filter((retificacoes) => retificacoes.edital.idEdital === +editalId);
        }
      });
    
    } else {
      // Exiba um alerta ou lide com a situação onde o editalId não está presente
      this.servicoAlerta.enviarAlerta({
        tipo: ETipoAlerta.ERRO,
        mensagem: "Nenhum ID de edital encontrado na rota!"
      });
    }
  }

  refreshComponent() {
    this.carregarDados();
  }
  registro: Retificacoes = <Retificacoes>{};
  retificacoes: Retificacoes[] = [];
  editais: Edital[] = [];

  formRetificacoes = new FormGroup({
    edital: new FormControl<Edital | null>(null),
    nomeRetificacoes: new FormControl<string | null>(null, [Validators.required, Validators.pattern(/^(.*\S.*\S.*\S.*)$/)]),
    fileRetificacoes: new FormControl<string | null>(null, Validators.required),
    ativoRetificacoes: new FormControl<boolean | null>(null),

  });

  get form() {
    return this.formRetificacoes.controls;
  }


  save(): void {
    this.registro = Object.assign(this.registro, this.formRetificacoes.value);

    console.log("Registro", this.registro);
    // Captura o arquivo do input com id 'fileArquivo'
    const fileInput: HTMLInputElement = document.getElementById('fileRetificacoes') as HTMLInputElement;
    const file: File | null = fileInput.files?.item(0) ?? null;

    if (file) {
      // Envia o arquivo junto com o objeto 'registro'
      this.servico.save(this.registro, file).subscribe({
        complete: () => {
            this.refreshComponent();
            this.formRetificacoes.reset();
            this.servicoAlerta.enviarAlerta({
            tipo: ETipoAlerta.SUCESSO,
            mensagem: "Operação realizada com sucesso!"
          });
        }
      });
    }
  }
  
  delete(id: number): void {
    if (confirm('Confirma a remoção dessa retificação? ' )) {
      this.servico.delete(id).subscribe({
        complete: () => {
          this.refreshComponent();
          this.formRetificacoes.reset();
          this.servicoAlerta.enviarAlerta({
            tipo: ETipoAlerta.SUCESSO,
            mensagem: "Retificação removida com sucesso!"
          });
        }
      });
    }
  }
}
