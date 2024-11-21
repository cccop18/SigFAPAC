import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { MenuPropostaComponent } from '../../menu-proposta/menu-proposta/menu-proposta.component';
import { AlertaService } from '../../../service/alerta/alerta.service';
import { PropostaService } from '../../../service/proposta/proposta.service';
import { EstadoService } from '../../../service/estado/estado.service';
import { EditalService } from '../../../service/edital/edital.service';
import { Edital } from '../../../model/Edital';
import { RespostaPaginada } from '../../../model/RespostaPaginada';
import { UnidadeInstituicaoService } from '../../../service/unidade-instituicao.service';
import { AreaConhecimentoService } from '../../../service/area-conhecimento.service';
import { AreaConhecimento } from '../../../model/AreaConhecimento';
import { UnidadeInstituicao } from '../../../model/UnidadeInstituicao';
import { Instituicao } from '../../../model/Instituicao';
import { FaixaFinanciamentoService } from '../../../service/faixaFinanciamento/faixa-financiamento.service';
import { VinculoInstitucional } from '../../../model/VinculoInstitucional';
import { PesquisadorConhecimento } from '../../../model/PesquisadorConhecimento';
import { PropostaConhecimento } from '../../../model/PropostaConhecimento';
import { CadastroAreaconhecimentoPesquisadorComponent } from '../../cadastro-areaconhecimento-pesquisador/cadastro-areaconhecimento-pesquisador.component';
import { NgbModal, NgbModalOptions } from '@ng-bootstrap/ng-bootstrap';
import { PrimeiraSubArea } from '../../../model/PrimeiraSubArea';
import { SegundaSubArea } from '../../../model/SegundaSubArea';
import { TerceiraSubArea } from '../../../model/TerceiraSubArea';
import { InstituicaoService } from '../../../service/instituicao.service';
import { SegundaSubAreaService } from '../../../service/segunda-sub-area.service';
import { PrimeiraSubAreaService } from '../../../service/primeira-sub-area.service';
import { TerceiraSubAreaService } from '../../../service/terceira-sub-area.service';
import { ETipoAlerta } from '../../../model/ETipoAlerta';
import { IForm } from '../../i-form';
import { PropostaTitulo } from '../../../model/PropostaTitulo';
import { PropostaTituloService } from '../../../service/propostaTitulo/proposta-titulo.service';
import { FaixaFinanciamento } from '../../../model/FaixaFinanciamento';

@Component({
  selector: 'app-titulo-proposta-form',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule, MenuPropostaComponent, CadastroAreaconhecimentoPesquisadorComponent],
  templateUrl: './titulo-proposta-form.component.html',
  styleUrl: './titulo-proposta-form.component.css'
})
export class TituloPropostaFormComponent implements IForm<PropostaTitulo>, OnInit {

  registro: PropostaTitulo = <PropostaTitulo>{};
  edital: Edital = <Edital>{}; // O edital carregado
  areasConhecimento: AreaConhecimento[] = [];
  primeiraSubAreas: PrimeiraSubArea[] = [];
  segundaSubAreas: SegundaSubArea[] = [];
  terceiraSubAreas: TerceiraSubArea[] = [];
  instituicoes: Instituicao[] = [];
  unidades: UnidadeInstituicao[] = [];
  faixa: FaixaFinanciamento[] = [];
  faixaMin: number | undefined;
  faixaMax: number | undefined;



  exibirCamposPatente: boolean = false;
  exibirCamposInovacao: boolean = false;
  exibirCamposEtica: boolean = false;
  exibirTermoAceite: boolean = false;


  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private alertaService: AlertaService,
    private servico: PropostaTituloService,
    private servicoEdital: EditalService,
    private servicoUnidade: UnidadeInstituicaoService,
    private servicoArea: AreaConhecimentoService,
    private servicoFaixa: FaixaFinanciamentoService,
    private modalService: NgbModal,

    private instituicaoService: InstituicaoService,
    private unidadeInstituicaoService: UnidadeInstituicaoService,
    private servicoAreaConhecimento: AreaConhecimentoService,
    private servicoSegundaSubArea: SegundaSubAreaService,
    private servicoPrimeiraSubArea: PrimeiraSubAreaService,
    private servicoTerceiraSubArea: TerceiraSubAreaService,
  ) {
  }

  ngOnInit(): void {
    this.carregarInstituicoes();
  
    this.formPropostaTitulo.get('idInstituicao')?.valueChanges.subscribe((instituicaoId) => {
      if (instituicaoId) {
        this.carregarUnidades(instituicaoId);
      }
    });
    this.servicoAreaConhecimento.get().subscribe({
      next: (resposta: RespostaPaginada<AreaConhecimento>) => {
        this.areasConhecimento = resposta.content;
      }
    });

    this.servicoPrimeiraSubArea.get().subscribe({
      next: (resposta: RespostaPaginada<PrimeiraSubArea>) => {
        this.primeiraSubAreas = resposta.content;
      }
    });

    this.servicoSegundaSubArea.get().subscribe({
      next: (resposta: RespostaPaginada<SegundaSubArea>) => {
        this.segundaSubAreas = resposta.content;
      }
    });

    this.servicoTerceiraSubArea.get().subscribe({
      next: (resposta: RespostaPaginada<TerceiraSubArea>) => {
        this.terceiraSubAreas = resposta.content;
      }
    });

    this.unidadeInstituicaoService.get().subscribe({
      next: (resposta: RespostaPaginada<UnidadeInstituicao>) => {
        this.unidades = resposta.content;
      }
    });

    this.servicoFaixa.get().subscribe({
      next: (resposta: RespostaPaginada<FaixaFinanciamento>) => {
        this.faixa = resposta.content
      }
    });

    this.instituicaoService.get().subscribe({
      next: (resposta: RespostaPaginada<Instituicao>) => {
        this.instituicoes = resposta.content;
      }
    });
    // Obter o id do edital dos parâmetros da rota
    this.route.queryParams.subscribe(params => {
      const idEdital = params['idEdital'];

      if (idEdital) {
        // Carregar o edital vinculado e verificar os campos booleanos
        this.servicoEdital.getById(idEdital).subscribe({
          next: (resposta: Edital) => {
            this.edital = resposta;
            this.formPropostaTitulo.patchValue({ idEdital: idEdital });

            if (this.edital) {
              this.formPropostaTitulo.patchValue({
                nomeEdital: this.edital.tituloEdital,
                termoAceiteTexto: this.edital.termoAceiteTextoEdital,
                duracaoProposta: this.edital.duracaoPropostaEdital,
                dataInicioPesquisa: this.edital.dataAberturaEdital,
              });
              // Definir visibilidade dos campos com base nos valores do edital
              this.exibirTermoAceite = this.edital.termoAceiteBooleanEdital || false;
              this.exibirCamposPatente = this.edital.patenteEdital || false;
              this.exibirCamposInovacao = this.edital.inovacaoTecEdital || false;
              this.exibirCamposEtica = this.edital.autoEticaEdital || false;
            }
          },
          error: (err) => {
            console.error('Erro ao carregar o edital:', err);
          }
        });
      }
    });
    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      this.servico.getById(+id).subscribe({
        next: (resposta: PropostaTitulo) => {
          this.registro = resposta;
          this.formPropostaTitulo.patchValue(this.registro);
          
        }
      });
    }
  }

  formPropostaTitulo = new FormGroup({
    idEdital: new FormControl<number | null>(null), 
    idProposta: new FormControl<number | null>(null),
    nomeEdital: new FormControl<string | null>(null),
    termoAceiteTexto: new FormControl<string | null>(null),
    pesquisador: new FormControl<number>(1),
    tituloProposta: new FormControl<string | null>(null),
    grupoPesquisa: new FormControl<string | null>(null),
    dataInicioPesquisa: new FormControl<string | null>(null),
    duracaoProposta: new FormControl<string | null>(null),
    termoAceiteProposta: new FormControl<boolean | null>(null),
    patenteProposta: new FormControl<boolean | null>(null),
    inovacaoProposta: new FormControl<boolean | null>(null),
    eticaProposta: new FormControl<boolean | null>(null),
    idInstituicao: new FormControl<number | null>(null),
    idUnidade: new FormControl<number | null>(null),
    propostaConhecimento: new FormControl<PropostaConhecimento[]>([]),
  });

  get form() {
    return this.formPropostaTitulo.controls;
  }

  PropostaConhecimento: PropostaConhecimento[] = [];

  receberPropostaConhecimento(propostaConhecimento: PropostaConhecimento): void {

    this.PropostaConhecimento.push(propostaConhecimento);
    

   // this.PropostaConhecimento.forEach((conhecimento) => {
     // conhecimento.idAreaConhecimento = Number(conhecimento.idAreaConhecimento);
     // conhecimento.idPrimeiraSubArea = Number(conhecimento.idPrimeiraSubArea);
     // conhecimento.idSegundaSubArea = Number(conhecimento.idSegundaSubArea);
     // conhecimento.idTerceiraSubArea = Number(conhecimento.idTerceiraSubArea);
   // });

    this.form.propostaConhecimento.setValue(this.PropostaConhecimento);
  }
  // Busca por minhas áreas de conhecimento
  getAreaConhecimentoNome(idAreaConhecimento: number): string {
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
  // Carregando Instituições e com base no ID da Instituição, carregar as Unidades
  onInstituicaoChange(event: any): void {
    const instituicaoId = event.target.value;

    if (Number(instituicaoId)) { // Verifica se é um número válido
      this.carregarUnidades(instituicaoId); // Chama o método para carregar unidades
    } else {
      console.error('Instituição ID inválido:', instituicaoId);
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

  carregarFaixaFinanciamento(idEdital: number): void {
    this.servicoFaixa.getByEditalId(idEdital).subscribe({
      next: (resposta: RespostaPaginada<FaixaFinanciamento>) => {
        if (resposta.content.length > 0) {
          this.faixaMin = resposta.content[0].minFaixaFinanciamento;
          this.faixaMax = resposta.content[0].maxFaixaFinanciamento;
        }
      },
      error: (err) => {
        console.error('Erro ao carregar faixa de financiamento:', err);
      }
    });
  }
  

  options: NgbModalOptions = {
    centered: true,
    size: 'lg', // ou 'xl' para tamanho maior
    windowClass: 'custom-modal-width'
  };
  // Função para abrir o modal
  openModal(content: any): void {
    this.modalService.open(content, { centered: true, size: 'lg' });
  }

  save(): void {
    // Atualiza o objeto registro com os dados do formulário
    this.registro = Object.assign(this.registro, this.formPropostaTitulo.value);
  
    console.log("Dados para serem salvos:", this.registro);
    
    // Chama o serviço para salvar os dados
    this.servico.save(this.registro).subscribe({
      next: (resposta: PropostaTitulo) => {
        // Aqui, o idProposta gerado pelo banco deve estar na resposta
        this.registro.idProposta = resposta.idProposta;
  
        // Navega para a próxima rota enviando os parâmetros idProposta e idEdital
        this.router.navigate(['/propostaabrangencia/form/'], {
          queryParams: { 
            id: this.registro.idProposta,   // Supondo que idProposta esteja no objeto retornado
            idEdital: this.registro.idEdital // Usando o idEdital já preenchido
          }
        });
  
        // Exibe alerta de sucesso
        this.servicoArea.enviarAlerta({
          tipo: ETipoAlerta.SUCESSO,
          mensagem: "Operação realizada com sucesso!"
        });
      },
      error: (err) => {
        console.error('Erro ao salvar a proposta:', err);
        this.servicoArea.enviarAlerta({
          tipo: ETipoAlerta.ERRO,
          mensagem: "Erro ao salvar a proposta."
        });
      }
    });
  }
  
}