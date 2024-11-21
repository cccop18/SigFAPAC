import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, ActivatedRoute, RouterLink } from '@angular/router';
import { VinculoInstitucional } from '../../model/VinculoInstitucional';
import { VinculoInstitucionalService } from '../../service/vinculo-institucional.service';
import { CadastroDePesquisadorService } from '../../service/cadastrarPesquisador/cadastro-de-pesquisador.service';
import { InstituicaoService } from '../../service/instituicao.service';
import { UnidadeInstituicaoService } from '../../service/unidade-instituicao.service';
import { Instituicao } from '../../model/Instituicao';
import { UnidadeInstituicao } from '../../model/UnidadeInstituicao';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { ETipoAlerta } from '../../model/ETipoAlerta';
import { CommonModule } from '@angular/common';
import { IForm } from '../i-form';
import { AlertaService } from '../../service/alerta/alerta.service';
import { AlertaComponent } from '../alerta/alerta.component';

@Component({
  selector: 'app-cadastro-dados-profissionais',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule, AlertaComponent],
  templateUrl: './cadastro-dados-profissionais.component.html',
  styleUrls: ['./cadastro-dados-profissionais.component.css']
})
export class CadastroDadosProfissionaisComponent implements IForm<VinculoInstitucional> {

  @Input() vinculo: VinculoInstitucional = {} as VinculoInstitucional;
  @Output() vinculoInstitucional = new EventEmitter<VinculoInstitucional>();

  registro: VinculoInstitucional = <VinculoInstitucional>{};
  instituicoes: Instituicao[] = [];
  unidades: UnidadeInstituicao[] = [];

  formVinculo = new FormGroup({
    idInstituicao: new FormControl<number | null>(null, Validators.required),
    idUnidadeInstituicao: new FormControl<number | null>(null, Validators.required),
    vinculoInstitucional: new FormControl<string | null>(null, Validators.required),
    vinculoEmpregaticio: new FormControl<boolean | null>(null, Validators.required),
    tempoServico: new FormControl<string | null>(null, Validators.required),
    regimeTrabalho: new FormControl<string | null>(null, Validators.required),
    funcaoCargo: new FormControl<string | null>(null, Validators.required),
    tempoDaFuncao: new FormControl<string | null>(null, Validators.required),
    idPesquisador: new FormControl<number> (1), 
  });

  constructor(
    private vinculoService: VinculoInstitucionalService,
    private alertaService: AlertaService,
    private instituicaoService: InstituicaoService,
    private unidadeInstituicaoService: UnidadeInstituicaoService,
    private cadastroService: CadastroDePesquisadorService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  get form() {
    return this.formVinculo.controls;
  }

  ngOnInit(): void {
    this.carregarInstituicoes();
  
    this.formVinculo.get('idInstituicao')?.valueChanges.subscribe((instituicaoId) => {
      if (instituicaoId) {
        this.carregarUnidades(instituicaoId);
      }
    });
  
    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      this.vinculoService.getById(+id).subscribe({
        next: (resposta: VinculoInstitucional) => {
          this.registro = resposta;
  
          // Aqui estamos ajustando o valor do campo unidadeInstituicao para o ID (número)
          this.formVinculo.patchValue({
            ...this.registro,
            idUnidadeInstituicao: this.registro.idUnidadeInstituicao || null
          });
        }
      });
    }
  }

  carregarInstituicoes(): void {
    this.instituicaoService.get().subscribe({
      next: (resposta: RespostaPaginada<Instituicao>) => {
        this.instituicoes = resposta.content;
      },
      error: () => {
        this.alertaService.enviarAlerta({
          tipo: ETipoAlerta.ERRO,
          mensagem: 'Erro ao carregar instituições.'
        });
      }
    });
  }

  carregarUnidades(instituicaoId: number): void {
    this.unidadeInstituicaoService.getByInstituicao(instituicaoId).subscribe({
      next: (unidades: UnidadeInstituicao[]) => {
        this.unidades = unidades;
        console.log("aqui dentro")
        console.log(this.unidades)
      },
      error: () => {
        this.alertaService.enviarAlerta({
          tipo: ETipoAlerta.ERRO,
          mensagem: 'Erro ao carregar unidades.'
        });
      }
    });
  }
  

  save(): void {
    
    this.vinculo = Object.assign(this.vinculo, this.formVinculo.value);
    this.vinculoInstitucional.emit(this.vinculo);

    this.alertaService.enviarAlerta({
      tipo: ETipoAlerta.SUCESSO,
      mensagem: 'Operação realizada com sucesso!'
    });
    this.formVinculo.reset();
  }
}
