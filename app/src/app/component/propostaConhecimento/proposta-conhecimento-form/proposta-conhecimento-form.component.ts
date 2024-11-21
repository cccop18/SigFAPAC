import { Component, EventEmitter, Inject, Input, OnInit, Output, PLATFORM_ID, Renderer2 } from '@angular/core';
import { IForm } from '../../i-form';
import { AreaConhecimento } from '../../../model/AreaConhecimento';
import { PrimeiraSubArea } from '../../../model/PrimeiraSubArea';
import { SegundaSubArea } from '../../../model/SegundaSubArea';
import { TerceiraSubArea } from '../../../model/TerceiraSubArea';
import { Instituicao } from '../../../model/Instituicao';
import { UnidadeInstituicao } from '../../../model/UnidadeInstituicao';
import { PropostaConhecimento } from '../../../model/PropostaConhecimento';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { FormArray, FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { AlertaService } from '../../../service/alerta/alerta.service';
import { PropostaTituloService } from '../../../service/propostaTitulo/proposta-titulo.service';
import { EditalService } from '../../../service/edital/edital.service';
import { UnidadeInstituicaoService } from '../../../service/unidade-instituicao.service';
import { AreaConhecimentoService } from '../../../service/area-conhecimento.service';
import { FaixaFinanciamentoService } from '../../../service/faixaFinanciamento/faixa-financiamento.service';
import { NgbModal, NgbModalOptions } from '@ng-bootstrap/ng-bootstrap';
import { InstituicaoService } from '../../../service/instituicao.service';
import { SegundaSubAreaService } from '../../../service/segunda-sub-area.service';
import { PrimeiraSubAreaService } from '../../../service/primeira-sub-area.service';
import { TerceiraSubAreaService } from '../../../service/terceira-sub-area.service';
import { RespostaPaginada } from '../../../model/RespostaPaginada';
import { Proposta } from '../../../model/Proposta';
import { PropostaConhecimentoService } from '../../../service/propostaConhecimento/proposta-conhecimento.service';
import { CadastroAreaconhecimentoPesquisadorComponent } from "../../cadastro-areaconhecimento-pesquisador/cadastro-areaconhecimento-pesquisador.component";
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { MenuPropostaComponent } from '../../menu-proposta/menu-proposta/menu-proposta.component';
import { AlertaComponent } from "../../alerta/alerta.component";
import { CadastroDePesquisadorService } from '../../../service/cadastrarPesquisador/cadastro-de-pesquisador.service';
import { ETipoAlerta } from '../../../model/ETipoAlerta';
import { PesquisadorConhecimento } from '../../../model/PesquisadorConhecimento';
import { PropostaService } from '../../../service/proposta/proposta.service';

@Component({
  selector: 'app-proposta-conhecimento-form',
  standalone: true,
  imports: [CadastroAreaconhecimentoPesquisadorComponent,
    FormsModule,
    RouterLink,
    ReactiveFormsModule,
    CommonModule,
    MenuPropostaComponent, AlertaComponent],
  templateUrl: './proposta-conhecimento-form.component.html',
  styleUrl: './proposta-conhecimento-form.component.css'
})
export class PropostaConhecimentoFormComponent implements OnInit {

  registro: PropostaConhecimento = <PropostaConhecimento>{};
  areasConhecimento: AreaConhecimento[] = []; // Para armazenar as áreas de conhecimento
  primeiraSubAreas: PrimeiraSubArea[] = [];  // Para armazenar as primeiras subáreas
  segundaSubAreas: SegundaSubArea[] = [];   // Para armazenar as segundas subáreas
  terceiraSubAreas: TerceiraSubArea[] = [];  // Para armazenar as terceiras subáreas
  minhasAreas: PropostaConhecimento[] = []; // Para armazenar as áreas de conhecimento do pesquisador

  constructor(
    private renderer: Renderer2,
    @Inject(PLATFORM_ID) private platformId: Object,
    private fb: FormBuilder,
    private areaConhecimentoService: AreaConhecimentoService,
    private cadastroService: PropostaConhecimentoService,
    private route: ActivatedRoute,
    private servico: PropostaConhecimentoService,
    private servicoProposta: PropostaService,
    private router: Router,
    private servicoAlerta: AlertaService,
    private servicoAreaConhecimento: AreaConhecimentoService,
    private servicoSegundaSubArea: SegundaSubAreaService,
    private servicoPrimeiraSubArea: PrimeiraSubAreaService,
    private servicoTerceiraSubArea: TerceiraSubAreaService,
  ) {

  }

  ngOnInit(): void {
    
    if (isPlatformBrowser(this.platformId)) {
      this.carregarAreasConhecimento();
    }
    this.carregarDados();
  }
  
  carregarDados(): void {
    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      this.servicoProposta.getById(+id).subscribe({
        next: (proposta: Proposta) => {
          this.formPropostaConhecimento.patchValue({
            proposta: proposta.idProposta
          });
        },
        error: () => {
          this.servicoAlerta.enviarAlerta({
            tipo: ETipoAlerta.ERRO,
            mensagem: 'Erro ao carregar a proposta.'
          });
        }
      });
  
      this.servico.get().subscribe({
        next: (resposta: RespostaPaginada<PropostaConhecimento>) => {
          this.minhasAreas = resposta.content.filter(faixa => faixa.idPropostaConhecimento === +id);
        },
        error: () => {
          this.servicoAlerta.enviarAlerta({
            tipo: ETipoAlerta.ERRO,
            mensagem: 'Erro ao carregar as áreas de conhecimento.'
          });
        }
      });
    } else {
      this.servicoAlerta.enviarAlerta({
        tipo: ETipoAlerta.ERRO,
        mensagem: 'Nenhum ID encontrado na rota!'
      });
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

  // novo formGroup
  formPropostaConhecimento = new FormGroup({
    idPropostaConhecimento: new FormControl<number | null>(null),
    proposta: new FormControl<number | null>(null),
    areaConhecimento: new FormControl<number | null>(null, Validators.required),
    primeiraSubArea: new FormControl<number | null>(null, Validators.required),
    segundaSubArea: new FormControl<number | null>(null),
    terceiraSubArea: new FormControl<number | null>(null),
  });

  // Busca por minhas áreas de conhecimento
  getAreaConhecimentoNome(idAreaConhecimento: number): string {
    console.log("Chegou area:", idAreaConhecimento);
    const area = this.areasConhecimento.find(ac => ac.idAreaConhecimento === idAreaConhecimento);
    return area ? area.nomeAreaConhecimento : 'N/A';
  }

  getPrimeiraSubAreaNome(idPrimeiraSubArea: number | null): string {
    if (idPrimeiraSubArea === null) return 'N/A';
    const subArea = this.primeiraSubAreas.find(sa => sa.id === idPrimeiraSubArea);
    return subArea ? subArea.nomePrimeiraSub : 'N/A';
  }

  getSegundaSubAreaNome(idSegundaSubArea: number | null): string {
    if (idSegundaSubArea === null) return 'N/A';
    const subArea = this.segundaSubAreas.find(sa => sa.idSegundaSubArea === idSegundaSubArea);
    return subArea ? subArea.nomeSegundaSub : 'N/A';
  }

  getTerceiraSubAreaNome(idTerceiraSubArea: number | null): string {
    if (idTerceiraSubArea === null) return 'N/A';
    const subArea = this.terceiraSubAreas.find(sa => sa.id === idTerceiraSubArea);
    return subArea ? subArea.nomeTerceiraSub : 'N/A';
  }
  
  // Função para salvar os dados de área de conhecimento no serviço
  salvarAreasConhecimento(): void {
  this.registro = Object.assign(this.registro, this.formPropostaConhecimento.value);
  console.log('Dados de registro:', this.registro);
  this.servico.save(this.registro).subscribe({
    complete: () => {
      //this.router.navigate(['/editais/']);
      this.refreshComponent();
      this.formPropostaConhecimento.reset();
      this.servicoAlerta.enviarAlerta({
        tipo: ETipoAlerta.SUCESSO,
        mensagem: "Operação realizada com sucesso!"
      });
    }
  });

  }
  delete(id: number): void {
    if (confirm('Confirma a remoção desta faixa?')) {
      this.servico.delete(id).subscribe({
        complete: () => {
          this.refreshComponent();
          this.servicoAlerta.enviarAlerta({
            tipo: ETipoAlerta.SUCESSO,
            mensagem: "Faixa removida com sucesso!"
          });
        }
      });
    }
  }
  refreshComponent() {
    this.carregarDados();
  }

  // Função para carregar as opções da subárea ao selecionar uma área de conhecimento
  onAreaConhecimentoChange(event: any): void {
  const areaIdString = event.target.value;
  // Dividir a string em partes usando ":" como separador
  const areaIdParts = areaIdString.split(":");
  
  // Pegar o valor após os ":"
  const areaId = areaIdParts.length > 1 ? areaIdParts[1] : areaIdParts[0]; // Caso não tenha ":", pega o valor original
  
  console.log('ID da área após o ":"', areaId);
    if (areaId) {
      console.log('ID da área selecionadagfhhhshsd:', areaId);
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
    const primeiraIdString = event.target.value;
  // Dividir a string em partes usando ":" como separador
  const primaiaIdParts = primeiraIdString.split(":");

    const primeiraSubAreaId = primaiaIdParts.length > 1 ? primaiaIdParts[1] : primaiaIdParts[0];
    console.log('ID da primeira subárea após o ":"', primeiraSubAreaId);
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
    const segundaIdString = event.target.value;
  // Dividir a string em partes usando ":" como separador
  const segundaIdParts = segundaIdString.split(":");

    const segundaSubAreaId = segundaIdParts.length > 1 ? segundaIdParts[1] : segundaIdParts[0];
    console.log('ID da primeira subárea após o ":"', segundaSubAreaId);

   
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