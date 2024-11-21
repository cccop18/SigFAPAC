import { Component } from '@angular/core';
import { IForm } from '../../i-form';
import { DiariaProposta } from '../../../model/DiariaProposta';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { DiariaPropostaService } from '../../../service/diariaProposta/diaria-proposta.service';
import { AlertaService } from '../../../service/alerta/alerta.service';
import { ETipoAlerta } from '../../../model/ETipoAlerta';
import { OrcamentoProposta } from '../../../model/OrcamentoProposta';
import { Moeda } from '../../../model/Moeda';
import { MoedaService } from '../../../service/moeda/moeda.service';
import { OrcamentoPropostaService } from '../../../service/orcamentoProposta/orcamento-proposta.service';
import { RespostaPaginada } from '../../../model/RespostaPaginada';
import { MenuPropostaComponent } from '../../menu-proposta/menu-proposta/menu-proposta.component';
import { HttpClient } from '@angular/common/http';
import { Edital } from '../../../model/Edital';
import { EditalService } from '../../../service/edital/edital.service';

@Component({
  selector: 'app-diaria-proposta-form',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule, MenuPropostaComponent],
  templateUrl: './diaria-proposta-form.component.html',
  styleUrl: './diaria-proposta-form.component.css'
})
export class DiariaPropostaFormComponent {

  mostrarCamposLocalidade: boolean = false;

  constructor(
    private http: HttpClient,
    private servico: DiariaPropostaService,
    private servicoMoeda: MoedaService,
    private servicoOrcamento: OrcamentoPropostaService,
    private servicoEdital: EditalService,
    private router: Router,
    private route: ActivatedRoute,
    private servicoAlerta: AlertaService,
    private rout: ActivatedRoute
  ){}

  ngOnInit(): void {
    // Carregar diárias
    this.servico.get().subscribe({
      next: (resposta: RespostaPaginada<DiariaProposta>) => {
        this.diariaProposta = resposta.content;
      }
    });
  
    // Carregar moedas
    this.servicoMoeda.get().subscribe({
      next: (resposta: RespostaPaginada<Moeda>) => {
        this.moedas = resposta.content;
      }
    });
  
    // Carregar orçamentos
    this.servicoOrcamento.get().subscribe({
      next: (resposta: RespostaPaginada<OrcamentoProposta>) => {
        this.orcamento = resposta.content;
      }
    });

    this.servicoEdital.get().subscribe({
      next: (respota: RespostaPaginada<Edital>) => {
        this.editais = respota.content;
      }
    })
  
    // Pegar id da diária da query
    const diariaId = this.route.snapshot.queryParamMap.get('id');
    if (diariaId) {
      this.servico.getById(+diariaId).subscribe({
        next: (resposta: DiariaProposta) => {
          this.registro = resposta;
          this.formDiariaProposta.patchValue(this.registro);
        }
      });
    }
  
    // Pegar id do orçamento da query e carregar orçamento
    const orcamentoId = this.route.snapshot.queryParamMap.get('orcamentoId');
    if (orcamentoId) {
      this.servicoOrcamento.getById(+orcamentoId).subscribe({
        next: (resposta: OrcamentoProposta) => {
          this.formDiariaProposta.patchValue({
            orcamentoProposta: resposta
          });
        }
      });
    }
  }


  formDiariaProposta = new FormGroup({
    idDiariaProposta: new FormControl<number | null>(null),
    tipoLocalidadeDiariaProposta: new FormControl<string | null>(null, Validators.required),
    paisDiariaProposta: new FormControl <string | null>(null, Validators.required),
    estadoDiariaProposta:  new FormControl<string | null>(null, Validators.required),
    municipioDiariaProposta: new FormControl<string | null>(null, Validators.required),
    provinciaDiariaProposta: new FormControl< string | null>(null),
    numeroDiariaProposta: new FormControl <number | null>(null,Validators.required),
    custoUnitDiariaProposta: new FormControl <number | null>(null, Validators.required),
    data: new FormControl<string | null>(null, Validators.required),
    justicativa: new FormControl<string | null>(null),
    orcamentoProposta: new FormControl<OrcamentoProposta | null>(null),
    moeda: new FormControl <Moeda | null>(null, Validators.required),
  });

  get form() {
    return this.formDiariaProposta.controls;
  }


  registro: DiariaProposta = <DiariaProposta>{};
  diariaProposta: DiariaProposta[] = [];
  moedas: Moeda[] = [];
  orcamento: OrcamentoProposta[] = [];
  editais: Edital[] = [];
  estados = [
    { sigla: 'AC', nome: 'Acre' },
    { sigla: 'AL', nome: 'Alagoas' },
    { sigla: 'AM', nome: 'Amazonas' },
    { sigla: 'AP', nome: 'Amapá' },
    { sigla: 'BA', nome: 'Bahia' },
    { sigla: 'CE', nome: 'Ceará' },
    { sigla: 'DF', nome: 'Distrito Federal' },
    { sigla: 'ES', nome: 'Espírito Santo' },
    { sigla: 'GO', nome: 'Goiás' },
    { sigla: 'MA', nome: 'Maranhão' },
    { sigla: 'MG', nome: 'Minas Gerais' },
    { sigla: 'MS', nome: 'Mato Grosso do Sul' },
    { sigla: 'MT', nome: 'Mato Grosso' },
    { sigla: 'PA', nome: 'Pará' },
    { sigla: 'PB', nome: 'Paraíba' },
    { sigla: 'PE', nome: 'Pernambuco' },
    { sigla: 'PI', nome: 'Piauí' },
    { sigla: 'PR', nome: 'Paraná' },
    { sigla: 'RJ', nome: 'Rio de Janeiro' },
    { sigla: 'RN', nome: 'Rio Grande do Norte' },
    { sigla: 'RO', nome: 'Rondônia' },
    { sigla: 'RR', nome: 'Roraima' },
    { sigla: 'RS', nome: 'Rio Grande do Sul' },
    { sigla: 'SC', nome: 'Santa Catarina' },
    { sigla: 'SE', nome: 'Sergipe' },
    { sigla: 'SP', nome: 'São Paulo' },
    { sigla: 'TO', nome: 'Tocantins' },
  ];
  municipios: string[] = [];
  

  toggleLocalidadeForm(): void {
    this.mostrarCamposLocalidade = !this.mostrarCamposLocalidade;
  }

  onTipoLocalidadeChange(): void {
    const tipo = this.formDiariaProposta.get('tipoLocalidadeDiariaProposta')?.value;
    if (tipo === 'Nacional') {
      // Limpar campos internacionais
      this.formDiariaProposta.get('paisDiariaProposta')?.reset();
      this.formDiariaProposta.get('provinciaDiariaProposta')?.reset();
      this.formDiariaProposta.patchValue({
        paisDiariaProposta: "Brasil"
      })
    } else if (tipo === 'Internacional') {
      // Limpar campos nacionais
      this.formDiariaProposta.get('estadoDiariaProposta')?.reset();
      this.formDiariaProposta.get('municipioDiariaProposta')?.reset();
    }
  }

  onEstadoChange(): void {
    const estadoSelecionado = this.formDiariaProposta.get('estadoDiariaProposta')?.value;
    if (estadoSelecionado) {
      // Chamar API do IBGE para obter municípios com base no código da UF
      this.http.get<any[]>(`https://servicodados.ibge.gov.br/api/v1/localidades/estados/${estadoSelecionado}/municipios`)
        .subscribe({
          next: (municipios: any[]) => {
            this.municipios = municipios.map(municipio => municipio.nome);
          },
          error: (err) => {
            console.error('Erro ao carregar municípios:', err);
          }
        });
    }
  }

  save(): void {
    this.registro = Object.assign(this.registro, this.formDiariaProposta.value);
    console.log(this.registro);
    this.servico.save(this.registro).subscribe({
      complete: () => {
        // Recarregar a página toda
        window.location.reload();
        this.servicoAlerta.enviarAlerta({
          tipo: ETipoAlerta.SUCESSO,
          mensagem: "Operação realizada com sucesso!"
        });
      }
    });
  }
  

  delete(id: number): void {
    if (confirm('Deseja apagar a Diaria?')) {
      this.servico.delete(id).subscribe({
        complete: () => {
          window.location.reload();
          this.servicoAlerta.enviarAlerta({
            tipo: ETipoAlerta.SUCESSO,
            mensagem: "Edital excluído com sucesso!"
          });
        }
      });
    }
  }

  redirect(): void {
    this.registro = Object.assign(this.registro, this.formDiariaProposta.value);
    this.router.navigate(['/propostamaterial/form'], {
      queryParams: { 
        orcamentoId: this.registro.orcamentoProposta.idOrcamentoProposta,   // Supondo que idProposta esteja no objeto retornado
      }
    })};

}
