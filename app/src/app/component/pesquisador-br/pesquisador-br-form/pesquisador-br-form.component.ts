import { Component } from '@angular/core';
import { IForm } from '../../i-form';
import { PesquisadorBrasileiroDto } from '../../../model/PesquisadorBrasileiroDto';
import { ETipoAlerta } from '../../../model/ETipoAlerta';
import { NgbModal, NgbModalOptions, NgbTooltipModule } from '@ng-bootstrap/ng-bootstrap';
import { PesquisadorConhecimento } from '../../../model/PesquisadorConhecimento';
import { VinculoInstitucional } from '../../../model/VinculoInstitucional';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { TerceiraSubArea } from '../../../model/TerceiraSubArea';
import { SegundaSubArea } from '../../../model/SegundaSubArea';
import { PrimeiraSubArea } from '../../../model/PrimeiraSubArea';
import { AreaConhecimento } from '../../../model/AreaConhecimento';
import { UnidadeInstituicao } from '../../../model/UnidadeInstituicao';
import { Instituicao } from '../../../model/Instituicao';
import { RespostaPaginada } from '../../../model/RespostaPaginada';
import { CepService } from '../../../service/api/cep-service.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { AlertaService } from '../../../service/alerta/alerta.service';
import { TerceiraSubAreaService } from '../../../service/terceira-sub-area.service';
import { PrimeiraSubAreaService } from '../../../service/primeira-sub-area.service';
import { SegundaSubAreaService } from '../../../service/segunda-sub-area.service';
import { AreaConhecimentoService } from '../../../service/area-conhecimento.service';
import { UnidadeInstituicaoService } from '../../../service/unidade-instituicao.service';
import { InstituicaoService } from '../../../service/instituicao.service';
import { TipoPesquisadorService } from '../../../service/tipo-pesquisador.service';
import { PesquisadorBrasileiroDtoService } from '../../../service/pesquisadorBrasileiroDto/pesquisador-brasileiro-dto.service';
import { CommonModule } from '@angular/common';
import { CadastroDadosProfissionaisComponent } from '../../cadastro-dados-profissionais/cadastro-dados-profissionais.component';
import { CadastroAreaconhecimentoPesquisadorComponent } from '../../cadastro-areaconhecimento-pesquisador/cadastro-areaconhecimento-pesquisador.component';
import { CadastroInstituicaoComponent } from '../../cadastro-instituicao/cadastro-instituicao.component';
import { CadastroUnidadeComponent } from '../../cadastro-unidade/cadastro-unidade.component';
import { strongPasswordValidator } from '../../../validators/senha-forte.validator';

@Component({
  selector: 'app-pesquisador-br-form',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, CommonModule, RouterLink,
    CadastroDadosProfissionaisComponent,
    CadastroAreaconhecimentoPesquisadorComponent,
    NgbTooltipModule,
    CadastroInstituicaoComponent,
    CadastroUnidadeComponent,],
  templateUrl: './pesquisador-br-form.component.html',
  styleUrl: './pesquisador-br-form.component.css'
})
export class PesquisadorBrFormComponent implements IForm<PesquisadorBrasileiroDto>{

  //Controle dos modal de vinculo
  estadoModal: string = 'vinculoInstitucional';  
  tituloModal: string = 'Vínculo Institucional'; 
  mostrarBotaoCadastrarUnidade: boolean = true; 

  constructor(
    private servico: PesquisadorBrasileiroDtoService,
    private servicoTipoPesquisador: TipoPesquisadorService,
    private instituicaoService: InstituicaoService,
    private unidadeInstituicaoService: UnidadeInstituicaoService,
    private servicoAreaConhecimento: AreaConhecimentoService,
    private servicoSegundaSubArea: SegundaSubAreaService,
    private servicoPrimeiraSubArea: PrimeiraSubAreaService,
    private servicoTerceiraSubArea: TerceiraSubAreaService,
    private servicoAlerta: AlertaService,
    private router: Router,
    private route: ActivatedRoute,
    private modalService: NgbModal,
    private cepService: CepService  // Injetando o serviço de CEP

  ) { }


  ngOnInit(): void {
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

    this.instituicaoService.get().subscribe({
      next: (resposta: RespostaPaginada<Instituicao>) => {
        this.instituicoes = resposta.content;
      }
    });
    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      this.servico.getById(+id).subscribe({
        next: (resposta: PesquisadorBrasileiroDto) => {
          this.registro = resposta;
          this.formPesquisadorBr.patchValue(this.registro);
        }
      });
    }
  }
  registro: PesquisadorBrasileiroDto = <PesquisadorBrasileiroDto>{};
  instituicoes: Instituicao[] = [];
  unidades: UnidadeInstituicao[] = [];
  areasConhecimento: AreaConhecimento[] = [];
  primeiraSubAreas: PrimeiraSubArea[] = [];
  segundaSubAreas: SegundaSubArea[] = [];
  terceiraSubAreas: TerceiraSubArea[] = [];
  imagemPreview: string | ArrayBuffer | null = null;


  formPesquisadorBr = new FormGroup({
    idPesquisador: new FormControl<number | null>(null),
    nomeCompletoPesquisador: new FormControl<string | null>(null, { nonNullable: true, validators: [Validators.required, Validators.pattern(/^[A-Za-zÀ-ÖØ-öø-ÿ\s]{3,}$/)] }),
    fotoPesquisador: new FormControl<string | null>(null, { nonNullable: true, validators: [Validators.required] }),
    emailPesquisador: new FormControl<string | null>(null, { nonNullable: true, validators: [Validators.required, Validators.email] }),
    sexoPesquisador: new FormControl<string | null>(null, { nonNullable: true, validators: [Validators.required] }),
    dataNascimentoPesquisador: new FormControl<string | null>(null, { nonNullable: true, validators: [Validators.required] }),
    corRaca: new FormControl<string | null>(null),
    nomeMaePesquisador: new FormControl<string | null>(null, { nonNullable: true, validators: [Validators.required, Validators.pattern(/^[A-Za-zÀ-ÖØ-öø-ÿ\s]{3,}$/)] }),
    nomePaiPesquisador: new FormControl<string | null>(null, Validators.pattern(/^[A-Za-zÀ-ÖØ-öø-ÿ\s]{3,}$/)),
    curriculoLattesPesquisador: new FormControl<string | null>(null, { nonNullable: true, validators: [Validators.required] }),
    nivelAcademicoPesquisador: new FormControl<string | null>(null, { nonNullable: true, validators: [Validators.required] }),
    senhaPesquisador: new FormControl<string | null>(null, { nonNullable: true, validators: [Validators.required, strongPasswordValidator()] }),
    // Novos campos
    cpfPesquisador: new FormControl<string | null>(null, { nonNullable: true, validators: [Validators.required]}),
    rgPesquisador: new FormControl<string | null>(null, { nonNullable: true, validators: [Validators.required] }),
    orgaoEmissorPesquisador: new FormControl<string | null>(null, { nonNullable: true, validators: [Validators.required]}),
    ufPesquisador: new FormControl<string | null>(null, { nonNullable: true, validators: [Validators.required]}),
    dataEmissaoPesquisador: new FormControl<string | null>(null, { nonNullable: true, validators: [Validators.required] }),
    telefonePesquisador: new FormControl<string | null>(null, { nonNullable: true, validators: [Validators.required, Validators.pattern("^\\(\\d{2}\\) \\d{4,5}-\\d{4}$")] }),
    idTipoPesquisador: new FormControl<number>(1),

    // Endereço Residencial
    idEnderecoResidencial: new FormControl<number | null>(null),
    tipoEnderecoResidencial: new FormControl<string>("RESIDENCIAL"),
    cepEnderecoResidencial: new FormControl<string | null>(null, { nonNullable: true, validators: [Validators.required, Validators.pattern(/^[0-9]{5}-?[0-9]{3}$/)] }),
    logradouroEnderecoResidencial: new FormControl<string | null>(null, { nonNullable: true, validators: [Validators.required] }),
    numeroEnderecoResidencial: new FormControl<string | null>(null, { nonNullable: true, validators: [Validators.required] }),
    bairroEnderecoResidencial: new FormControl<string | null>(null, { nonNullable: true, validators: [Validators.required] }),
    paisEnderecoResidencial: new FormControl<string | null>(null, { nonNullable: true, validators: [Validators.required] }),
    estadoEnderecoResidencial: new FormControl<string | null>(null, { nonNullable: true, validators: [Validators.required] }),
    municipioEnderecoPesquisador: new FormControl<string | null>(null, { nonNullable: true, validators: [Validators.required] }),
    telefoneEnderecoPesquisador: new FormControl<string | null>(null, { nonNullable: true, validators: [Validators.required, Validators.pattern("^\\(\\d{2}\\) \\d{4,5}-\\d{4}$")] }),

    // Endereço Profissional
    idEnderecoProfissional: new FormControl<number | null>(null),
    tipoEnderecoProfissional: new FormControl<string>("PROFISSIONAL"),
    cepEnderecoProfissional: new FormControl<string | null>(null, Validators.pattern(/^[0-9]{5}-?[0-9]{3}$/)),
    logradouroEnderecoProfissional: new FormControl<string | null>(null),
    numeroEnderecoProfissional: new FormControl<string | null>(null),
    bairroEnderecoProfissional: new FormControl<string | null>(null),
    paisEnderecoProfissional: new FormControl<string | null>(null),
    estadoEnderecoProfissional: new FormControl<string | null>(null),
    municipioEnderecoProfissional: new FormControl<string | null>(null),
    telefoneEnderecoProfissional: new FormControl<string | null>(null, Validators.pattern("^\\(\\d{2}\\) \\d{4,5}-\\d{4}$")),

    // Vínculos Institucionais
    vinculosInstitucionais: new FormControl<VinculoInstitucional[]>([]), // Ajuste conforme necessário
    pesquisadorConhecimento: new FormControl<PesquisadorConhecimento[]>([]) // Ajuste conforme necessário

  });


  get form() {
    return this.formPesquisadorBr.controls;
  }

  vinculosInstitucionais: VinculoInstitucional[] = [];

  receberVinculo(vinculo: VinculoInstitucional): void {

    this.vinculosInstitucionais.push(vinculo);
    console.log('Vínculo institucional adicionado:', vinculo);
    this.form.vinculosInstitucionais.setValue(this.vinculosInstitucionais);
    console.log('Vínculos institucionais:', this.vinculosInstitucionais);

    // // Verifica se o vínculo já existe na lista antes de adicionar
    // const vinculoExistente = this.vinculosInstitucionais.find(
    //   v => v.idInstituicao === vinculo.idInstituicao && v.idUnidadeInstituicao === vinculo.idUnidadeInstituicao
    // );

    // if (!vinculoExistente) {
    //   // Adiciona o vínculo institucional à lista
    //   this.vinculosInstitucionais.push(vinculo);
    //   console.log('Vínculo institucional adicionado:', vinculo);
    //   this.form.vinculosInstitucionais.setValue(this.vinculosInstitucionais);
    //   console.log('Vínculos institucionais:', this.vinculosInstitucionais);
    // } else {
    //   // console.log('Vínculo já existe:', vinculo);
    // }
  }
  // Carrega os dados de instituição e unidade para exibir
  getUnidadeNome(idUnidadeInstituicao: number): string {
    console.log('Unidade chamada:', idUnidadeInstituicao);
    console.log('Todas as unidades:', this.unidades);
    
    // Mudança para usar o campo correto: idUnidadeInstituicao
    const unidade = this.unidades.find(uni => uni.id === idUnidadeInstituicao);
    
    console.log('Unidade encontrada:', unidade);
    return unidade ? unidade.nome_unidade : '==';
  }
  
  
  
  
  getInstituicaoNome(idInstituicao: number): string {
    const instituicao = this.instituicoes.find(inst => inst.idInstituicao === idInstituicao);
    return instituicao ? instituicao.nomeInstituicao : 'N/A Instituições';
  }

  PesquisadorConhecimento: PesquisadorConhecimento[] = [];


  receberPesquisadorConhecimento(pesquisadorConhecimento: PesquisadorConhecimento): void {

    this.PesquisadorConhecimento.push(pesquisadorConhecimento);
    console.log('Área de conhecimento adicionada:', pesquisadorConhecimento);

    this.PesquisadorConhecimento.forEach((conhecimento) => {
      conhecimento.idAreaConhecimento = Number(conhecimento.idAreaConhecimento);
      conhecimento.idPrimeiraSubArea = Number(conhecimento.idPrimeiraSubArea);
      conhecimento.idSegundaSubArea = Number(conhecimento.idSegundaSubArea);
      conhecimento.idTerceiraSubArea = Number(conhecimento.idTerceiraSubArea);
    });

    this.form.pesquisadorConhecimento.setValue(this.PesquisadorConhecimento);
    console.log('Pesquisador conhecimento:', this.PesquisadorConhecimento);

    
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

  options: NgbModalOptions = {
    centered: true,
    size: 'lg', // ou 'xl' para tamanho maior
    windowClass: 'custom-modal-width'
  };




  // Método para carregar e exibir a imagem no campo de pré-visualização
  carregarImagem(event: Event): void {
    const input = event.target as HTMLInputElement;

    if (input.files && input.files[0]) {
      const arquivo = input.files[0];
      const reader = new FileReader();

      // Define que o resultado esperado é uma URL para a visualização
      reader.onload = () => {
        if (reader.result) {
          // Verifica se é uma string antes de atribuir ao imagemPreview
          this.imagemPreview = reader.result as string;
        }
      };

      // Lê o arquivo de imagem como uma URL
      reader.readAsDataURL(arquivo);
    }
  }
 // Método para verificar o CEP e preencher os campos de endereço automaticamente
  verificarCep(tipoEndereco: 'RESIDENCIAL' | 'PROFISSIONAL'): void {
    const campoCep = tipoEndereco === 'RESIDENCIAL'
      ? 'cepEnderecoResidencial'
      : 'cepEnderecoProfissional';

    const formEndereco = tipoEndereco === 'RESIDENCIAL'
      ? this.formPesquisadorBr.get('cepEnderecoResidencial')
      : this.formPesquisadorBr.get('cepEnderecoProfissional');

    const cep = formEndereco?.value;

    if (cep && formEndereco?.valid) {
      this.cepService.consultarCep(cep).subscribe({
        next: (dados) => {
          console.log(dados);
          if (dados.erro) {
            formEndereco?.setErrors({ cepInvalido: true });
          } else {
            const enderecoCampos = tipoEndereco === 'RESIDENCIAL'
              ? {
                logradouroEnderecoResidencial: dados.logradouro,
                bairroEnderecoResidencial: dados.bairro,
                estadoEnderecoResidencial: dados.uf,
                paisEnderecoResidencial: 'Brasil'
              }
              : {
                logradouroEnderecoProfissional: dados.logradouro,
                bairroEnderecoProfissional: dados.bairro,
                estadoEnderecoProfissional: dados.uf,
                paisEnderecoProfissional: 'Brasil'
              };

            // Preenche os campos de endereço automaticamente
            this.formPesquisadorBr.patchValue(enderecoCampos);
          }
        },
        error: () => {
          formEndereco?.setErrors({ cepInvalido: true });
        }
      });
    }
  }
  // Função para abrir o modal
  openModal(content: any): void {
    this.modalService.open(content, { centered: true, size: 'lg' });
  }

  // Função chamada quando o usuário deseja cadastrar uma nova instituição
  cadastroInstituicao(): void {
    this.estadoModal = 'cadastroInstituicao';
    this.tituloModal = 'Cadastro de Instituição';
    this.mostrarBotaoCadastrarUnidade = false;  // Oculta o botão ao entrar na tela de cadastro de instituição
  }

  // Função chamada após o cadastro da instituição, exibe o botão para cadastrar unidade
  exibirBotaoCadastrarUnidade(): void {
    this.mostrarBotaoCadastrarUnidade = true;
  }

  // Função chamada para ir para o cadastro de unidade
  irParaCadastroUnidade(): void {
    this.estadoModal = 'cadastroUnidade';
    this.tituloModal = 'Cadastro de Unidade';
    this.mostrarBotaoCadastrarUnidade = false; // Oculta o botão após o clique
  }

  // Função chamada após o cadastro da unidade, volta para vínculo institucional
  voltarParaVinculo(): void {
    this.estadoModal = 'vinculoInstitucional';
    this.tituloModal = 'Vínculo Institucional';
  }


  save(): void {
    this.registro = Object.assign(this.registro, this.formPesquisadorBr.value);

    console.log(this.registro);

    const fotoInput: HTMLInputElement = document.getElementById('fotoPesquisador') as HTMLInputElement;
    const fileFoto: File | null = fotoInput.files?.item(0) ?? null;

    if (fileFoto) {
      this.servico.save(this.registro, fileFoto).subscribe({
        complete: () => {
          this.router.navigate(['/editais/']);
          this.servicoAlerta.enviarAlerta({
            tipo: ETipoAlerta.SUCESSO,
            mensagem: "Operação realizada com sucesso!"
          });
        }
      });

    }

  }
}
