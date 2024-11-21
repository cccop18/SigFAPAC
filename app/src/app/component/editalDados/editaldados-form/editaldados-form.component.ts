import { Component } from '@angular/core';
import { EditalDadosService } from '../../../service/editalDados/edital-dados.service';
import { AlertaService } from '../../../service/alerta/alerta.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { EditalDados } from '../../../model/EditalDados';
import { RespostaPaginada } from '../../../model/RespostaPaginada';
import { Programa } from '../../../model/Programa';
import { ProgramaService } from '../../../service/programa/programa.service';
import { ETipoAlerta } from '../../../model/ETipoAlerta';
import { CommonModule } from '@angular/common';
import { MenuEditalComponent } from '../../menu-edital/menu-edital.component';
import { environment } from '../../../../environments/environment';

@Component({
  selector: 'app-editaldados-form',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule, MenuEditalComponent],
  templateUrl: './editaldados-form.component.html',
  styleUrls: ['./editaldados-form.component.css']
})
export class EditaldadosFormComponent {
encodeURIComponent(arg0: string) {
throw new Error('Method not implemented.');
}
  meses: number[] = [];
  novoArquivo: boolean = false;  // Flag para controlar a exibição do campo de arquivo
  isEditMode: boolean = false;   // Flag para determinar se é modo de edição

  constructor(
    private servico: EditalDadosService,
    private servicoAlerta: AlertaService,
    private servicoPrograma: ProgramaService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    
    this.meses = Array.from({ length: 120 }, (_, i) => i + 1);

    this.servicoPrograma.get().subscribe({
      next: (resposta: RespostaPaginada<Programa>) => {
        this.programas = resposta.content;
      }
    });

    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      this.isEditMode = true;  // Define como modo de edição se houver um ID
      this.novoArquivo = false;  // Não exibe o campo de upload de arquivo no modo de edição inicialmente
      this.servico.getById(+id).subscribe({
        next: (resposta: EditalDados) => {
          this.registro = resposta;
          this.formEditalDados.patchValue(this.registro);
          const { fileEdital, ...formData } = this.registro;
          this.formEditalDados.patchValue(formData);
        }
      });
    } else {
      this.isEditMode = false;  // Define como novo cadastro se não houver um ID
      this.novoArquivo = true;  // Exibe o campo de upload de arquivo no modo de novo cadastro
    }

    // Listener para mudanças no campo termoAceiteBooleanEdital
    this.formEditalDados.get('termoAceiteBooleanEdital')?.valueChanges.subscribe((checked) => {
      const termoAceiteCtrl = this.formEditalDados.get('termoAceiteTextoEdital');
      if (checked) {
        termoAceiteCtrl?.setValidators(Validators.required);
      } else {
        termoAceiteCtrl?.clearValidators();
      }
      termoAceiteCtrl?.updateValueAndValidity();
    });
  }

  registro: EditalDados = <EditalDados>{};
  programas: Programa[] = [];

  urlView = environment.API_URL + '/editalDados/view/';  // URL para visualizar arquivos

  formEditalDados = new FormGroup({
    tituloEdital: new FormControl<string | null>(null, [Validators.required, Validators.pattern(/^(.*\S.*\S.*\S.*)$/)]),
    identificacaoEdital: new FormControl<string | null>(null, Validators.required),
    fileEdital: new FormControl<string | null>(null),  // Não é mais obrigatório por padrão
    numeroAnoEdital: new FormControl<string | null>(null),
    programa: new FormControl<number | null>(null, Validators.required),
    nomeFormularioEdital: new FormControl<string | null>(null, [Validators.required, Validators.pattern(/^(.*\S.*\S.*\S.*)$/)]),
    codUnidadeAdmEdital: new FormControl<number | null>(null),
    textoChamadaEdital: new FormControl<string | null>(null),
    duracaoPropostaEdital: new FormControl<string | null>(null, Validators.required),
    multiplasPropostaEdital: new FormControl<boolean | null>(null),
    cordenadorParticipaEdital: new FormControl<boolean | null>(null),
    cargaHorariaEdital: new FormControl<boolean | null>(null),
    escolheDuracaoEdital: new FormControl<boolean | null>(null),
    obgOrientadorEdital: new FormControl<boolean | null>(null),
    cordenadorBolsaEdital: new FormControl<boolean | null>(null),
    editalEspecial: new FormControl<boolean | null>(null),
    proponenteBolsaEdital: new FormControl<boolean | null>(null),
    patenteEdital: new FormControl<boolean | null>(null),
    inovacaoTecEdital: new FormControl<boolean | null>(null),
    autoEticaEdital: new FormControl<boolean | null>(null),
    termoAceiteBooleanEdital: new FormControl<boolean | null>(null),
    termoAceiteTextoEdital: new FormControl<string | null>(null)
  });

  get form() {
    return this.formEditalDados.controls;
  }

  // Método para ativar o upload de um novo arquivo
  ativarNovoArquivo(): void {
    this.novoArquivo = true;
    this.formEditalDados.get('fileEdital')?.setValidators(Validators.required);
    this.formEditalDados.get('fileEdital')?.updateValueAndValidity();
  }

  // Método para remover o arquivo existente e permitir adicionar um novo
  removerArquivo(): void {
    this.registro.fileEdital = null; // Limpa o valor do arquivo no registro
    this.formEditalDados.patchValue({ fileEdital: null });
    this.ativarNovoArquivo();
  }

  save(): void {
    this.registro = Object.assign(this.registro, this.formEditalDados.value);
  
    // Captura o elemento de input com id 'fileEdital'
    const fileInput: HTMLInputElement | null = document.getElementById('fileEdital') as HTMLInputElement;
    const file: File | null = fileInput && fileInput.files ? fileInput.files.item(0) : null;
  
    if (!this.isEditMode) { // Novo cadastro
      if (file) {
        this.servico.save(this.registro, file).subscribe({
          next: (resposta: EditalDados) => {
            this.servicoAlerta.enviarAlerta({
              tipo: ETipoAlerta.SUCESSO,
              mensagem: "Operação realizada com sucesso!"
            });
            this.router.navigate(['editalfaixa/form'], {
              queryParams: { id: resposta.idEdital }  // Enviando o ID do edital salvo para a próxima página
            });
          }
        });
      }
    } else { // Modo de edição
      this.servico.update(this.registro, file).subscribe({
        complete: () => {
          this.servicoAlerta.enviarAlerta({
            tipo: ETipoAlerta.SUCESSO,
            mensagem: "Operação realizada com sucesso!"
          });
          this.router.navigate(['editalfaixa/form'], {
            queryParams: { id: this.registro.idEdital }  // Enviando o ID do edital salvo para a próxima página
          });
        }
      });
    }
  }
}
