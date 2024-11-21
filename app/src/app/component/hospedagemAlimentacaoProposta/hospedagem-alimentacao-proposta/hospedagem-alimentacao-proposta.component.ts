import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { MenuPropostaComponent } from '../../menu-proposta/menu-proposta/menu-proposta.component';
import { OrcamentoPropostaService } from '../../../service/orcamentoProposta/orcamento-proposta.service';
import { EditalService } from '../../../service/edital/edital.service';
import { AlertaService } from '../../../service/alerta/alerta.service';
import { MoedaService } from '../../../service/moeda/moeda.service';
import { HospedagemAlimenPropostaService } from '../../../service/hospedagem-alimen-proposta/hospedagem-alimen-proposta.service';
import { OrcamentoProposta } from '../../../model/OrcamentoProposta';
import { Moeda } from '../../../model/Moeda';
import { HospedagemAlimentacaoProposta } from '../../../model/HospedagemAlimentacaoProposta';
import { Edital } from '../../../model/Edital';
import { RespostaPaginada } from '../../../model/RespostaPaginada';
import { ETipoAlerta } from '../../../model/ETipoAlerta';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-hospedagem-alimentacao-proposta',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule, MenuPropostaComponent],
  templateUrl: './hospedagem-alimentacao-proposta.component.html',
  styleUrl: './hospedagem-alimentacao-proposta.component.css'
})
export class HospedagemAlimentacaoPropostaComponent {
    constructor(
    private http: HttpClient,
    private servico: HospedagemAlimenPropostaService, 
    private servicoMoeda: MoedaService,
    private servicoOrcamento: OrcamentoPropostaService,
    private servicoEdital: EditalService,
    private router: Router,
    private route: ActivatedRoute,
    private servicoAlerta: AlertaService
    ){}


    formHospedagemAlimenProposta = new FormGroup({
      idHospedagemAlimentacaoProposta: new FormControl<number| null>(null),
      tipoLocalidadeHopsProposta: new FormControl<string | null>(null),
      paisHospedagemAlimentacaoProposta: new FormControl<String | null>(null),
      estadoHospedagemAlimentacaoProposta: new FormControl<String| null>(null),
      hospedagemAlimentacaoProposta: new FormControl<String|null>(null),
      municipioHospedagemAlimentacaoProposta: new FormControl<String| null>(null),
      provinciaHospedagemAlimentacaoProposta: new FormControl<String|null>(null),
      especificacaoHospedagemAlimentacaoProposta: new FormControl<String|null>(null),
      quantidadeHospedagemAlimentacaoProposta: new FormControl<number|null>(null),
      custoHospedagemAlimentacaoProposta: new FormControl<number|null>(null),
      custoTotalProposta: new FormControl<number | null>(null),
      dataHospedagemAlimentacaoProposta: new FormControl<String|null>(null),
      orcamentoproposta: new FormControl<OrcamentoProposta|null>(null),
      moeda: new FormControl<Moeda|null>(null),
 });

   registro: HospedagemAlimentacaoProposta = <HospedagemAlimentacaoProposta>{};
   orcamentos: OrcamentoProposta[] = [];
   editais: Edital[] = [];
   hospedagemalimentacaoProposta: HospedagemAlimentacaoProposta[] = [];
   moedas: Moeda[] = [];
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

   ngOnInit(): void {

    this.servico.get().subscribe({
      next: (resposta: RespostaPaginada<HospedagemAlimentacaoProposta>) => {
        this.hospedagemalimentacaoProposta = resposta.content; // Atribui ao array
      }
    });

    this.servicoMoeda.get().subscribe({
      next: (resposta: RespostaPaginada<Moeda>) => {
        this.moedas = resposta.content;
      }
    });

    this.servicoOrcamento.get().subscribe({
      next: (resposta: RespostaPaginada<OrcamentoProposta>) => {
        this.orcamentos = resposta.content; // Atribui ao array renomeado
      }
    });

    this.servicoEdital.get().subscribe({
      next: (resposta: RespostaPaginada<Edital>) => {
        this.editais = resposta.content;
      }
    });


   // Pegar id do orçamento da query e carregar orçamento
   const orcamentoId = this.route.snapshot.queryParamMap.get('orcamentoId');
   if (orcamentoId) {
     this.servicoOrcamento.getById(+orcamentoId).subscribe({
       next: (resposta: OrcamentoProposta) => {
         this.formHospedagemAlimenProposta.patchValue({
           orcamentoproposta: resposta
         });
       }
     });
   }

  }
  onTipoLocalidadeChange(): void {
    const tipoLocalidade = this.formHospedagemAlimenProposta.get('tipoLocalidadeHopsProposta')?.value;
    
    if (tipoLocalidade === 'Internacional') {
      // Configura campos para 'Internacional'
      this.formHospedagemAlimenProposta.get('paisHospedagemAlimentacaoProposta')?.setValidators([Validators.required]);
      this.formHospedagemAlimenProposta.get('provinciaHospedagemAlimentacaoProposta')?.setValidators([Validators.required]);
      this.formHospedagemAlimenProposta.get('estadoHospedagemAlimentacaoProposta')?.clearValidators();
      this.formHospedagemAlimenProposta.get('municipioHospedagemAlimentacaoProposta')?.clearValidators();
    } else {
      // Configura campos para 'Nacional'
      this.formHospedagemAlimenProposta.get('estadoHospedagemAlimentacaoProposta')?.setValidators([Validators.required]);
      this.formHospedagemAlimenProposta.get('municipioHospedagemAlimentacaoProposta')?.setValidators([Validators.required]);
      this.formHospedagemAlimenProposta.get('paisHospedagemAlimentacaoProposta')?.clearValidators();
      this.formHospedagemAlimenProposta.get('provinciaHospedagemAlimentacaoProposta')?.clearValidators();
    }
  
    // Atualiza o status dos campos após alterar as validações
    this.formHospedagemAlimenProposta.get('paisHospedagemAlimentacaoProposta')?.updateValueAndValidity();
    this.formHospedagemAlimenProposta.get('provinciaHospedagemAlimentacaoProposta')?.updateValueAndValidity();
    this.formHospedagemAlimenProposta.get('estadoHospedagemAlimentacaoProposta')?.updateValueAndValidity();
    this.formHospedagemAlimenProposta.get('municipioHospedagemAlimentacaoProposta')?.updateValueAndValidity();
  }
  

  onEstadoChange(): void {
    const estadoSelecionado = this.formHospedagemAlimenProposta.get('estadoHospedagemAlimentacaoProposta')?.value;
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
    this.registro = Object.assign(this.registro, this.formHospedagemAlimenProposta.value);
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
}
