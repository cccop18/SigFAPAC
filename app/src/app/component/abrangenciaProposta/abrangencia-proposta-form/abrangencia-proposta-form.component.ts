import { Component, OnInit } from '@angular/core';
import { IForm } from '../../i-form';
import { AbrangenciaProposta } from '../../../model/AbrangenciaProposta';
import { AbrangenciaPropostaService } from '../../../service/abrangenciaProposta/abrangencia-proposta.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { AlertaService } from '../../../service/alerta/alerta.service';
import { EditalService } from '../../../service/edital/edital.service';
import { EstadoService } from '../../../service/estado/estado.service';
import { PropostaService } from '../../../service/proposta/proposta.service';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Estado } from '../../../model/Estado';
import { CommonModule } from '@angular/common';
import { Proposta } from '../../../model/Proposta';
import { RespostaPaginada } from '../../../model/RespostaPaginada';
import { ETipoAlerta } from '../../../model/ETipoAlerta';
import { HttpClient } from '@angular/common/http';
import { MenuPropostaComponent } from '../../menu-proposta/menu-proposta/menu-proposta.component';

@Component({
  selector: 'app-abrangencia-proposta-form',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule, MenuPropostaComponent],
  templateUrl: './abrangencia-proposta-form.component.html',
  styleUrl: './abrangencia-proposta-form.component.css'
})
export class AbrangenciaPropostaFormComponent implements OnInit {
  estados: Estado[] = [];
  municipios: string[] = [];
  municipiosSelecionados: string[] = [];

  formAbrangencias = new FormGroup({
    idAbrangenciaProposta: new FormControl<number | null>(null),
    municipioAbrangenciaProposta: new FormControl<string | null>(null, Validators.required),
    proposta: new FormControl<Proposta | null>(null),
    estado: new FormControl<Estado | null>(null, Validators.required),
  });

  registro: AbrangenciaProposta = <AbrangenciaProposta>{};
  constructor(
    private http: HttpClient,
    private servico: AbrangenciaPropostaService,
    private router: Router,
    private route: ActivatedRoute,
    private servicoAlerta: AlertaService,
    private servicoProposta: PropostaService,
    private servicoEstado: EstadoService
  ) {}

  ngOnInit(): void {
    // Carregar a lista de estados
    this.servicoEstado.get().subscribe({
      next: (resposta: RespostaPaginada<Estado>) => {
        this.estados = resposta.content;
      }
    });
  
    // Obter o ID da proposta da query string
    const idProposta = this.route.snapshot.queryParamMap.get('id');
    
    if (idProposta) {
      // Buscar a proposta pelo ID e preencher o campo 'proposta' no formulário
      this.servicoProposta.getById(+idProposta).subscribe({
        next: (proposta: Proposta) => {
          this.formAbrangencias.patchValue({
            proposta: proposta
          });
        },
        error: (err) => {
          console.error('Erro ao buscar proposta:', err);
          this.servicoAlerta.enviarAlerta({
            tipo: ETipoAlerta.ERRO,
            mensagem: 'Erro ao carregar proposta.'
          });
        }
      });
    }
  }

  // Método para carregar municípios usando a API do IBGE
  onEstadoChange(): void {
    // Limpar a lista de municípios selecionados
    this.municipiosSelecionados = [];
  
    const estadoSelecionado = this.formAbrangencias.get('estado')?.value;
    if (estadoSelecionado) {
      const siglaEstado = this.converterNomeParaUf(estadoSelecionado.nomeEstado);
      if (siglaEstado) {
        // Fazer a chamada para a API do IBGE para obter municípios com base no código da UF
        this.http.get<any[]>(`https://servicodados.ibge.gov.br/api/v1/localidades/estados/${siglaEstado}/municipios`)
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
  }

  // Função para converter nome do estado para sigla UF
  converterNomeParaUf(nomeEstado: string): string | null {
    const estados = [
      { nome: 'Acre', uf: 'AC' },
      { nome: 'Alagoas', uf: 'AL' },
      { nome: 'Amapá', uf: 'AP' },
      { nome: 'Amazonas', uf: 'AM' },
      { nome: 'Bahia', uf: 'BA' },
      { nome: 'Ceará', uf: 'CE' },
      { nome: 'Distrito Federal', uf: 'DF' },
      { nome: 'Espírito Santo', uf: 'ES' },
      { nome: 'Goiás', uf: 'GO' },
      { nome: 'Maranhão', uf: 'MA' },
      { nome: 'Mato Grosso', uf: 'MT' },
      { nome: 'Mato Grosso do Sul', uf: 'MS' },
      { nome: 'Minas Gerais', uf: 'MG' },
      { nome: 'Pará', uf: 'PA' },
      { nome: 'Paraíba', uf: 'PB' },
      { nome: 'Paraná', uf: 'PR' },
      { nome: 'Pernambuco', uf: 'PE' },
      { nome: 'Piauí', uf: 'PI' },
      { nome: 'Rio de Janeiro', uf: 'RJ' },
      { nome: 'Rio Grande do Norte', uf: 'RN' },
      { nome: 'Rio Grande do Sul', uf: 'RS' },
      { nome: 'Rondônia', uf: 'RO' },
      { nome: 'Roraima', uf: 'RR' },
      { nome: 'Santa Catarina', uf: 'SC' },
      { nome: 'São Paulo', uf: 'SP' },
      { nome: 'Sergipe', uf: 'SE' },
      { nome: 'Tocantins', uf: 'TO' }
    ];

    const estadoEncontrado = estados.find(estado => estado.nome.toLowerCase() === nomeEstado.toLowerCase());
    return estadoEncontrado ? estadoEncontrado.uf : null;
  }


  adicionarMunicipio(): void {
    const municipioSelecionado = this.formAbrangencias.get('municipioAbrangenciaProposta')?.value;
    if (municipioSelecionado && !this.municipiosSelecionados.includes(municipioSelecionado)) {
      this.municipiosSelecionados.push(municipioSelecionado);
  
      // Salva os dados automaticamente após adicionar um município
      this.registro = Object.assign(this.registro, this.formAbrangencias.value);
      console.log(this.registro)
      this.servico.save(this.registro).subscribe({
        complete: () => {
          this.servicoAlerta.enviarAlerta({
            tipo: ETipoAlerta.SUCESSO,
            mensagem: 'Município adicionado e salvo com sucesso!'
          });
        },
        error: (err) => {
          console.error('Erro ao salvar o município:', err);
          this.servicoAlerta.enviarAlerta({
            tipo: ETipoAlerta.ERRO,
            mensagem: 'Erro ao salvar o município.'
          });
        }
      });
    }
  }

  removerMunicipio(): void {
    const municipioSelecionado = this.formAbrangencias.get('municipio')?.value;
    if (municipioSelecionado) {
      this.municipiosSelecionados = this.municipiosSelecionados.filter(
        municipio => municipio !== municipioSelecionado
      );
    }
  }
  removerMunicipioSelecionado(municipio: string) {
    this.municipiosSelecionados = this.municipiosSelecionados.filter(m => m !== municipio);
  }

  save(): void {
    const idProposta = this.route.snapshot.queryParamMap.get('id');
    this.registro = Object.assign(this.registro, this.formAbrangencias.value);
    this.servico.save(this.registro).subscribe({
      complete: () => {
        this.router.navigate(['/'], { queryParams: { id: idProposta } });
        this.servicoAlerta.enviarAlerta({
          tipo: ETipoAlerta.SUCESSO,
          mensagem: 'Operação realizada com sucesso!'
        });
      }
    });
  }
}
