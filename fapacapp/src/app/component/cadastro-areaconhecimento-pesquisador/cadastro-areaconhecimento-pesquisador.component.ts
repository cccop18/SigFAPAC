import { Component, OnInit, Renderer2, Inject, PLATFORM_ID, ViewEncapsulation } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';

@Component({
  selector: 'app-cadastro-areaconhecimento-pesquisador',
  standalone: true,
  imports: [],
  templateUrl: './cadastro-areaconhecimento-pesquisador.component.html',
  styleUrls: ['./cadastro-areaconhecimento-pesquisador.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class CadastroAreaconhecimentoPesquisadorComponent implements OnInit {
  areaConhecimentoCount = 1;
  
  constructor(
    private renderer: Renderer2,
    @Inject(PLATFORM_ID) private platformId: Object
  ) { }

  ngOnInit(): void {
    // Verifica se está no navegador antes de acessar o DOM
    if (isPlatformBrowser(this.platformId)) {
      const addEspecialidadeBtn = this.renderer.selectRootElement('#addEspecialidadeBtn');
      this.renderer.listen(addEspecialidadeBtn, 'click', (event) => this.addEspecialidade(event));
    }
  }

  addEspecialidade(event: Event): void {
    event.preventDefault(); // Evita o comportamento padrão do botão

    // Aumenta o contador para a nova área de conhecimento
    this.areaConhecimentoCount++;

    // Verifica se está no navegador antes de acessar o DOM
    if (isPlatformBrowser(this.platformId)) {
      const formSection = document.querySelector('.dados.conhecimento');

      if (formSection) {
        // Cria os novos campos e insere-os diretamente dentro da seção existente
        const newFields = `
          <label for="areaConhecimento${this.areaConhecimentoCount}" class="obrigatorio lbn-cmt">Área Principal</label>
          <select id="areaConhecimento${this.areaConhecimentoCount}" name="areaConhecimento${this.areaConhecimentoCount}" class="obrigatorio" required>
            <option value="">Escolher</option>
            <option value="exatas">Ciências Exatas</option>
            <option value="humanas">Ciências Humanas</option>
          </select>

          <label for="subArea1C${this.areaConhecimentoCount}" class="obrigatorio lbn-cmt">Sub Área 1</label>
          <select id="subArea1C${this.areaConhecimentoCount}" name="subArea1C${this.areaConhecimentoCount}" class="obrigatorio" required>
            <option value="">Escolher</option>
          </select>

          <label for="subArea2C${this.areaConhecimentoCount}" class="obrigatorio lbn-cmt">Sub Área 2</label>
          <select id="subArea2C${this.areaConhecimentoCount}" name="subArea2C${this.areaConhecimentoCount}" class="obrigatorio" required>
            <option value="">Escolher</option>
          </select>

          <label for="subArea3C${this.areaConhecimentoCount}" class="obrigatorio lbn-cmt">Sub Área 3</label>
          <select id="subArea3C${this.areaConhecimentoCount}" name="subArea3C${this.areaConhecimentoCount}" class="obrigatorio" required>
            <option value="">Escolher</option>
          </select>
        `;

        // Insere os novos campos dentro da seção "dados conhecimento"
        formSection.insertAdjacentHTML('beforeend', newFields);
      } else {
        console.error('Seção .dados.conhecimento não encontrada');
      }
    }
  }
}
