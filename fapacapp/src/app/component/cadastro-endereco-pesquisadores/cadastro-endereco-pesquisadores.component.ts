import { Component, OnInit, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';

@Component({
  selector: 'app-cadastro-endereco-pesquisadores',
  standalone: true,
  templateUrl: './cadastro-endereco-pesquisadores.component.html',
  styleUrls: ['./cadastro-endereco-pesquisadores.component.css']
})
export class CadastroEnderecoPesquisadoresComponent implements OnInit {

  constructor(@Inject(PLATFORM_ID) private platformId: Object) {}

  ngOnInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      // Carrega países nos selects
      this.fetchCountries('pais');
      this.fetchCountries('pais-prof');

      // Inicializa a alternância de campos com base na seleção do país
      this.initializeFieldToggle();

      // Carrega estados e municípios para os selects
      this.loadStatesAndMunicipios();
    }
  }

  // Função para carregar países
  async fetchCountries(selectId: string) {
    if (isPlatformBrowser(this.platformId)) {
      try {
        const response = await fetch('https://restcountries.com/v3.1/all');
        const countries = await response.json();
        const paisSelect = document.getElementById(selectId) as HTMLSelectElement;

        countries.sort((a: any, b: any) => a.name.common.localeCompare(b.name.common));

        countries.forEach((country: any) => {
          const option = document.createElement('option');
          option.value = country.cca2;
          option.textContent = country.name.common;
          paisSelect.appendChild(option);
        });

        // Adiciona eventos para alternância dos campos ao mudar o país
        this.addToggleFieldsEventListener(selectId);
      } catch (error) {
        console.error('Erro ao carregar países:', error);
      }
    }
  }

  // Adiciona eventos de alternância de campos com base na seleção de país
  addToggleFieldsEventListener(selectId: string) {
    if (isPlatformBrowser(this.platformId)) {
      const paisSelect = document.getElementById(selectId) as HTMLSelectElement;
      const camposBrasileiroClass = selectId === 'pais' ? 'brasileiro-endR' : 'brasileiro-endP';
      const camposEstrangeiroClass = selectId === 'pais' ? 'estrangeiro-endR' : 'estrangeiro-endP';

      paisSelect.addEventListener('change', () => {
        this.toggleFields(paisSelect, camposBrasileiroClass, camposEstrangeiroClass);
      });
    }
  }

  // Função para alternar campos brasileiros e estrangeiros
  toggleFields(paisSelect: HTMLSelectElement, camposBrasileiroClass: string, camposEstrangeiroClass: string) {
    const camposBrasileiro = document.querySelectorAll(`.${camposBrasileiroClass}`) as NodeListOf<HTMLElement>;
    const camposEstrangeiro = document.querySelectorAll(`.${camposEstrangeiroClass}`) as NodeListOf<HTMLElement>;

    if (paisSelect.value === 'BR') {
      camposBrasileiro.forEach(campo => {
        campo.classList.remove('desativar');
        (campo as HTMLInputElement).required = true;
      });
      camposEstrangeiro.forEach(campo => {
        campo.classList.add('desativar');
        (campo as HTMLInputElement).required = false;
      });
    } else {
      camposBrasileiro.forEach(campo => {
        campo.classList.add('desativar');
        (campo as HTMLInputElement).required = false;
      });
      camposEstrangeiro.forEach(campo => {
        campo.classList.remove('desativar');
        (campo as HTMLInputElement).required = true;
      });
    }
  }

  // Função para inicializar a alternância de campos
  initializeFieldToggle() {
    if (isPlatformBrowser(this.platformId)) {
      document.addEventListener('DOMContentLoaded', () => {
        this.toggleFields(document.getElementById('pais') as HTMLSelectElement, 'brasileiro-endR', 'estrangeiro-endR');
        this.toggleFields(document.getElementById('pais-prof') as HTMLSelectElement, 'brasileiro-endP', 'estrangeiro-endP');
      });
    }
  }

  // Função para carregar estados e municípios
  async loadStatesAndMunicipios() {
    if (isPlatformBrowser(this.platformId)) {
      const estadosSelects = document.querySelectorAll('.uf') as NodeListOf<HTMLSelectElement>;

      try {
        const response = await fetch('https://servicodados.ibge.gov.br/api/v1/localidades/estados');
        const estados = await response.json();

        estados.sort((a: any, b: any) => a.nome.localeCompare(b.nome));

        estados.forEach((estado: any) => {
          const option = document.createElement('option');
          option.value = estado.id;
          option.textContent = estado.nome;

          estadosSelects.forEach(select => {
            select.appendChild(option.cloneNode(true));
          });
        });

        // Carrega municípios ao selecionar um estado
        document.getElementById('uf1')?.addEventListener('change', () => {
          const estadoId = (document.getElementById('uf1') as HTMLSelectElement).value;
          this.loadMunicipios(estadoId, 'municipio1');
        });

        document.getElementById('uf2')?.addEventListener('change', () => {
          const estadoId = (document.getElementById('uf2') as HTMLSelectElement).value;
          this.loadMunicipios(estadoId, 'municipio2');
        });

      } catch (error) {
        console.error('Erro ao carregar os estados:', error);
      }
    }
  }

  // Função para carregar municípios com base no estado selecionado
  async loadMunicipios(estadoId: string, municipioSelectId: string) {
    if (isPlatformBrowser(this.platformId)) {
      const municipioSelect = document.getElementById(municipioSelectId) as HTMLSelectElement;

      municipioSelect.innerHTML = '<option value="">Selecione o Município</option>';

      if (estadoId) {
        try {
          const response = await fetch(`https://servicodados.ibge.gov.br/api/v1/localidades/estados/${estadoId}/municipios`);
          const municipios = await response.json();

          municipios.forEach((municipio: any) => {
            const option = document.createElement('option');
            option.value = municipio.id;
            option.textContent = municipio.nome;
            municipioSelect.appendChild(option);
          });

        } catch (error) {
          console.error('Erro ao carregar os municípios:', error);
        }
      }
    }
  }
}
