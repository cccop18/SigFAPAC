import { Component, OnInit, Renderer2, Inject, PLATFORM_ID, ViewEncapsulation, Input, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray,  ReactiveFormsModule, FormControl } from '@angular/forms';
import { isPlatformBrowser } from '@angular/common';
import { AreaConhecimentoService } from '../../service/areaConhecimento/area-conhecimento.service';
import { CadastroDePesquisadorService } from '../../service/cadastrarPesquisador/cadastro-de-pesquisador.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { PesquisadorConhecimento } from '../../model/PesquisadorConhecimento';
import { AlertaComponent } from '../alerta/alerta.component';
import { AlertaService } from '../../service/alerta/alerta.service';
import { ETipoAlerta } from '../../model/ETipoAlerta';
import { PropostaConhecimento } from '../../model/PropostaConhecimento';

@Component({
  selector: 'app-cadastro-areaconhecimento-pesquisador',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, AlertaComponent],
  templateUrl: './cadastro-areaconhecimento-pesquisador.component.html',
  styleUrls: ['./cadastro-areaconhecimento-pesquisador.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class CadastroAreaconhecimentoPesquisadorComponent implements OnInit {
 // Emite um evento quando um novo conhecimento é adicionado NO CADASTRO DE PESQUISADOR
  @Input() areaConhecimento: PesquisadorConhecimento = {} as PesquisadorConhecimento;
  @Output() pesquisadorConhecimento = new EventEmitter<PesquisadorConhecimento>();

  @Input() areaConhecimentoProposta: PropostaConhecimento = {} as PropostaConhecimento;
  @Output() propostaConhecimento = new EventEmitter<PropostaConhecimento>();
  

  areaConhecimentoForm: FormGroup; // Formulário reativo para áreas de conhecimento
  areasConhecimento: any[] = []; // Para armazenar as áreas de conhecimento
  primeiraSubAreas: any[] = [];  // Para armazenar as primeiras subáreas
  segundaSubAreas: any[] = [];   // Para armazenar as segundas subáreas
  terceiraSubAreas: any[] = [];  // Para armazenar as terceiras subáreas

  constructor(
    private renderer: Renderer2,
    @Inject(PLATFORM_ID) private platformId: Object,
    private fb: FormBuilder,
    private areaConhecimentoService: AreaConhecimentoService,
    private cadastroService: CadastroDePesquisadorService,
    private router: Router,
    private servicoAlerta: AlertaService
  ) {
    this.areaConhecimentoForm = this.fb.group({
      areas: this.fb.array([this.createAreaConhecimentoGroup()]) // Inicializa com um grupo de áreas
    });
  }

  ngOnInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      this.carregarAreasConhecimento();
    }
  }

  // Método para carregar todas as áreas de conhecimento
  carregarAreasConhecimento(): void {
    this.areaConhecimentoService.getAreasConhecimento().subscribe(
      (data) => {
        this.areasConhecimento = data.content || data; // Certifique-se de que 'data.content' ou 'data' contém os dados corretamente
        console.log('Áreas de conhecimento carregadas:', this.areasConhecimento);
      },
      (error) => {
        console.error('Erro ao carregar áreas de conhecimento:', error);
      }
    );
  }

  // Método para criar um novo grupo de área de conhecimento
  createAreaConhecimentoGroup(): FormGroup {
    return this.fb.group({
      areaConhecimento: ['', Validators.required],
      subArea1: ['', Validators.required],
      subArea2: ['', Validators.required],
      subArea3: ['', Validators.required]
    });
  }
  // novo formGroup
  formPesquisadorConhecimento = new FormGroup ({
    idPesquisadorConhecimento: new FormControl<number | null>(null),
    idAreaConhecimento: new FormControl<number | null>(null, Validators.required),
    idPrimeiraSubArea: new FormControl<number | null>(null, Validators.required),
    idSegundaSubArea: new FormControl<number | null>(null ),
    idTerceiraSubArea: new FormControl<number | null>(null),
  });

  // Getter para acessar o FormArray de áreas
  get areas(): FormArray {
    return this.areaConhecimentoForm.get('areas') as FormArray;
  }

  // Função para adicionar uma nova área de conhecimento
  addEspecialidade(event: Event): void {
    event.preventDefault();
    this.areas.push(this.createAreaConhecimentoGroup());
    console.log('Nova área adicionada ao formulário:', this.areaConhecimentoForm.value);
  }

  // Função para salvar os dados de área de conhecimento no serviço
  salvarAreasConhecimento(): void {
    this.areaConhecimento = Object.assign(this.areaConhecimento, this.formPesquisadorConhecimento.value);
    this.pesquisadorConhecimento.emit(this.areaConhecimento);

    if (this.formPesquisadorConhecimento.value.idPesquisadorConhecimento === null) {
      // Remover o campo 'idPesquisador' do objeto antes de emitir
      const { idPesquisadorConhecimento, ...propostaConhecimentoSemIdPesquisador } = this.formPesquisadorConhecimento.value;
      
      this.areaConhecimentoProposta = Object.assign(this.areaConhecimentoProposta, propostaConhecimentoSemIdPesquisador);
      this.propostaConhecimento.emit(this.areaConhecimentoProposta);
    }
    // Envia a mensagem de sucesso da operação
    this.servicoAlerta.enviarAlerta({
      tipo: ETipoAlerta.SUCESSO,
      mensagem: "Operação realizada com sucesso!"
    });
    this.formPesquisadorConhecimento.reset();
  }

  // Função para carregar as opções da subárea ao selecionar uma área de conhecimento
  onAreaConhecimentoChange(event: any): void {
    const areaId = event.target.value;
    if (areaId) {
      console.log('ID da área selecionada:', areaId);
      this.areaConhecimentoService.getPrimeiraSubAreas(areaId.trim()).subscribe(
        (data) => {
          this.primeiraSubAreas = data;
          console.log('Primeiras subáreas carregadas:', data);
        },
        (error) => {
          console.error('Erro ao carregar subáreas 1:', error);
        }
      );
    }
  }

  onPrimeiraSubAreaChange(event: any): void {
    const primeiraSubAreaId = event.target.value;
    if (primeiraSubAreaId) {
      this.areaConhecimentoService.getSegundaSubAreas(primeiraSubAreaId).subscribe(
        (data) => {
          this.segundaSubAreas = data;
        },
        (error) => {
          console.error('Erro ao carregar subáreas 2:', error);
        }
      );
    }
  }

  onSegundaSubAreaChange(event: any): void {
    const segundaSubAreaId = event.target.value;
    if (segundaSubAreaId) {
      this.areaConhecimentoService.getTerceiraSubAreas(segundaSubAreaId).subscribe(
        (data) => {
          this.terceiraSubAreas = data;
        },
        (error) => {
          console.error('Erro ao carregar subáreas 3:', error);
        }
      );
    }
  }
}
