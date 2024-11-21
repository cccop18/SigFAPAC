import { Component } from '@angular/core';
import { IForm } from '../../i-form';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { MenuEditalComponent } from '../../menu-edital/menu-edital.component';
import { ArquivoEdital } from '../../../model/ArquivoEdital';
import { Arquivo } from '../../../model/Arquivo';
import { ArquivoeditalService } from '../../../service/arquivoedital/arquivoedital.service';
import { AlertaService } from '../../../service/alerta/alerta.service';
import { DocPessoalEdital } from '../../../model/DocPessoalEdital';
import { ArquivoService } from '../../../service/arquivo/arquivo.service';
import { DocPessoal } from '../../../model/DocPessoal';
import { DocpessoalService } from '../../../service/docpessoal/docpessoal.service';
import { RespostaPaginada } from '../../../model/RespostaPaginada';
import { DocpessoaleditService } from '../../../service/docpessoaledital/docpessoaledit.service';
import { ETipoAlerta } from '../../../model/ETipoAlerta';
import { Edital } from '../../../model/Edital';

@Component({
  selector: 'app-edital-modelo-form',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule, MenuEditalComponent],
  templateUrl: './edital-modelo-form.component.html',
  styleUrl: './edital-modelo-form.component.css'
})
export class EditalModeloFormComponent {
  arquivosObrigatorios: Arquivo[] = [];
  arquivosVinculados: ArquivoEdital[] = [];
  documentosPessoais: DocPessoal[] = [];
  documentosPessoaisVinculados: DocPessoalEdital[] = [];
  idEdital: number | undefined; // ID do edital atual, defina isso conforme necessário

  constructor(
    private servicoArqEdital: ArquivoeditalService,
    private servicoDocPessoalEdital: DocpessoaleditService,
    private servicoArquvio: ArquivoService,
    private servicoDocPessoal: DocpessoalService,
    private router: Router,
    private route: ActivatedRoute,
    private servicoAlerta: AlertaService
  ) {}

  ngOnInit(): void {

    const idEditalParam = this.route.snapshot.queryParamMap.get('id');
    this.idEdital = idEditalParam ? +idEditalParam : 0;
    // Carregar todos os arquivos obrigatórios e documentos pessoais disponíveis
    this.servicoArquvio.get().subscribe({
      next: (resposta: RespostaPaginada<Arquivo>) => {
        this.arquivosObrigatorios = resposta.content.filter(arquivosObrigatorios => arquivosObrigatorios.ativoArquivo === true);
      }
    });

    this.servicoDocPessoal.get().subscribe({
      next: (resposta: RespostaPaginada<DocPessoal>) => {
        this.documentosPessoais = resposta.content;
      }
    });

    // Carregar os itens já vinculados ao edital
    this.servicoArqEdital.get().subscribe({
      next: (resposta: RespostaPaginada<ArquivoEdital>) => {
        this.arquivosVinculados = resposta.content;
      }
    });

    this.servicoDocPessoalEdital.get().subscribe({
      next: (resposta: RespostaPaginada<DocPessoalEdital>) => {
        this.documentosPessoaisVinculados = resposta.content;
      }
    });
  }

  // Funções para adicionar e remover arquivos obrigatórios
  adicionarArquivo(arquivo: Arquivo): void {
    // Verificar se o arquivo já está vinculado, usando o ID como critério de comparação.
    const jaVinculado = this.arquivosVinculados.some(
      (item) => item.arquivo.idArquivo === arquivo.idArquivo
    );
  
    if (jaVinculado) {
      this.servicoAlerta.enviarAlerta({ 
        tipo: ETipoAlerta.ERRO, 
        mensagem: 'Este arquivo já foi vinculado ao edital.' 
      });
      return;
    }
  
    // Criar um novo ArquivoEdital com o objeto `arquivo` completo e o `edital`
    const novoArquivoEdital: ArquivoEdital = {
      idArquivoEdital: 0, 
      arquivo: arquivo, 
      edital: { idEdital: this.idEdital } as Edital // Preencha o `Edital` com o ID atual
    };
    this.arquivosVinculados.push(novoArquivoEdital);
    console.log('Objeto que será enviado no save:', novoArquivoEdital);
    this.salvarArquivoEdital(novoArquivoEdital);
  }
  removerArquivo(arquivoEdital: ArquivoEdital): void {
    this.arquivosVinculados = this.arquivosVinculados.filter(item => item !== arquivoEdital);
    this.servicoArqEdital.delete(arquivoEdital.idArquivoEdital).subscribe({
      complete: () => this.servicoAlerta.enviarAlerta({ tipo: ETipoAlerta.SUCESSO, mensagem: 'Arquivo removido com sucesso!' })
    });
  }

  // Funções para adicionar e remover documentos pessoais
  adicionarDocumentoPessoal(docPessoal: DocPessoal): void {
    // Verificar se o documento pessoal já está vinculado, usando o ID como critério de comparação.
    const jaVinculado = this.documentosPessoaisVinculados.some(
      (item) => item.pessoal.idPessoais === docPessoal.idPessoais
    );
  
    if (jaVinculado) {
      this.servicoAlerta.enviarAlerta({ 
        tipo: ETipoAlerta.ERRO, 
        mensagem: 'Este documento pessoal já foi vinculado ao edital.' 
      });
      return;
    }
  
    // Criar um novo DocPessoalEdital com o objeto `docPessoal` completo e o `edital`
    const novoDocPessoalEdital: DocPessoalEdital = {
      idDocPessoalEdital: 0,
      pessoal: docPessoal,
      edital: { idEdital: this.idEdital } as Edital // Preencha o `Edital` com o ID atual
    };
    this.documentosPessoaisVinculados.push(novoDocPessoalEdital);
    console.log('Objeto que será enviado no save:', novoDocPessoalEdital);
    this.salvarDocPessoalEdital(novoDocPessoalEdital);
  }
  

  removerDocumentoPessoal(docPessoalEdital: DocPessoalEdital): void {
    this.documentosPessoaisVinculados = this.documentosPessoaisVinculados.filter(item => item !== docPessoalEdital);
    this.servicoDocPessoalEdital.delete(docPessoalEdital.idDocPessoalEdital).subscribe({
      complete: () => this.servicoAlerta.enviarAlerta({ tipo: ETipoAlerta.SUCESSO, mensagem: 'Documento pessoal removido com sucesso!' })
    });
  }

  // Função para salvar um ArquivoEdital
  private salvarArquivoEdital(arquivoEdital: ArquivoEdital): void {
    this.servicoArqEdital.save(arquivoEdital).subscribe({
      complete: () => this.servicoAlerta.enviarAlerta({ tipo: ETipoAlerta.SUCESSO, mensagem: 'Arquivo vinculado com sucesso!' })
    });
  }

  // Função para salvar um DocPessoalEdital
  private salvarDocPessoalEdital(docPessoalEdital: DocPessoalEdital): void {
    this.servicoDocPessoalEdital.save(docPessoalEdital).subscribe({
      complete: () => this.servicoAlerta.enviarAlerta({ tipo: ETipoAlerta.SUCESSO, mensagem: 'Documento pessoal vinculado com sucesso!' })
    });
  }
  redirecionarComId() {
    // Redireciona para a próxima página com o ID do edital como query param
    this.router.navigate(['/retificacao/form'], { queryParams: { id: this.idEdital } });
  }
}
