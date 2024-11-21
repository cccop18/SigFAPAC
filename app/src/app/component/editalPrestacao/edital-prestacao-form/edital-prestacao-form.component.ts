import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { MenuEditalComponent } from '../../menu-edital/menu-edital.component';
import { BancoEditalService } from '../../../service/bancoEdital/banco-edital.service';
import { FuncaoEditalService } from '../../../service/funcaoEdital/funcao-edital.service';
import { BancoService } from '../../../service/banco.service';
import { FuncaoService } from '../../../service/funcao/funcao.service';
import { AlertaService } from '../../../service/alerta/alerta.service';
import { Banco } from '../../../model/Banco';
import { BancoEdital } from '../../../model/BancoEdital';
import { Funcao } from '../../../model/Funcao';
import { RespostaPaginada } from '../../../model/RespostaPaginada';
import { FuncaoEdital } from '../../../model/FuncaoEdital';
import { ETipoAlerta } from '../../../model/ETipoAlerta';

@Component({
  selector: 'app-edital-prestacao-form',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule, MenuEditalComponent],
  templateUrl: './edital-prestacao-form.component.html',
  styleUrl: './edital-prestacao-form.component.css'
})
export class EditalPrestacaoFormComponent {

  bancos: Banco[] = [];
  bancosVinculados: BancoEdital[] = [];
  funcoes: Funcao[] = [];
  funcoesVinculadas: FuncaoEdital[] = [];
  idEdital: number | undefined; // ID do edital atual, defina isso conforme necessário

  constructor(
    private servicoBancoEdital: BancoEditalService,
    private servicoFuncEdital: FuncaoEditalService,
    private servicoBanco: BancoService,
    private servicoFuncao: FuncaoService,
    private router: Router,
    private route: ActivatedRoute,
    private servicoAlerta: AlertaService
  ){}

  ngOnInit(): void {
    const idEditalParam = this.route.snapshot.queryParamMap.get('id');
    this.idEdital = idEditalParam ? +idEditalParam : 0;

    // Carregar todos os bancos e funções disponíveis
    this.servicoBanco.get().subscribe({
      next: (resposta: RespostaPaginada<Banco>) => {
        // Filtra apenas os bancos que têm a situação 'Ativo'
        this.bancos = resposta.content.filter(banco => banco.ativoBanco === true);
      }
    });

    this.servicoFuncao.get().subscribe({
      next: (resposta: RespostaPaginada<Funcao>) => {
        this.funcoes = resposta.content.filter(funcao => funcao.ativoFuncao === true);
      }
    });

    // Carregar bancos e funções já vinculados ao edital
    this.servicoBancoEdital.get().subscribe({
      next: (resposta: RespostaPaginada<BancoEdital>) => {
        this.bancosVinculados = resposta.content;
      }
    });

    this.servicoFuncEdital.get().subscribe({
      next: (resposta: RespostaPaginada<FuncaoEdital>) => {
        this.funcoesVinculadas = resposta.content;
      }
    });
  }

  // Funções para adicionar e remover bancos
  adicionarBanco(banco: Banco): void {
    const jaVinculado = this.bancosVinculados.some(item => item.banco.idBanco === banco.idBanco);
    if (jaVinculado) {
      this.servicoAlerta.enviarAlerta({ tipo: ETipoAlerta.ERRO, mensagem: 'Este banco já foi vinculado ao edital.' });
      return;
    }

    const novoBancoEdital: BancoEdital = {
      idBancoEdital: 0,
      banco: banco,
      edital: { idEdital: this.idEdital } as any // Defina a estrutura do Edital conforme seu sistema
    };
    this.bancosVinculados.push(novoBancoEdital);
    console.log(novoBancoEdital)
    this.salvarBancoEdital(novoBancoEdital);
  }

  removerBanco(bancoEdital: BancoEdital): void {
    this.bancosVinculados = this.bancosVinculados.filter(item => item !== bancoEdital);
    this.servicoBancoEdital.delete(bancoEdital.idBancoEdital).subscribe({
      complete: () => this.servicoAlerta.enviarAlerta({ tipo: ETipoAlerta.SUCESSO, mensagem: 'Banco removido com sucesso!' })
    });
  }

  // Funções para adicionar e remover funções
  adicionarFuncao(funcao: Funcao): void {
    const jaVinculado = this.funcoesVinculadas.some(item => item.funcao.idFuncao === funcao.idFuncao);
    if (jaVinculado) {
      this.servicoAlerta.enviarAlerta({ tipo: ETipoAlerta.ERRO, mensagem: 'Esta função já foi vinculada ao edital.' });
      return;
    }

    const novaFuncaoEdital: FuncaoEdital = {
      idFuncaoEdital: 0,
      funcao: funcao,
      edital: { idEdital: this.idEdital } as any // Defina a estrutura do Edital conforme seu sistema
    };
    this.funcoesVinculadas.push(novaFuncaoEdital);
    console.log(novaFuncaoEdital)
    this.salvarFuncaoEdital(novaFuncaoEdital);
  }

  removerFuncao(funcaoEdital: FuncaoEdital): void {
    this.funcoesVinculadas = this.funcoesVinculadas.filter(item => item !== funcaoEdital);
    this.servicoFuncEdital.delete(funcaoEdital.idFuncaoEdital).subscribe({
      complete: () => this.servicoAlerta.enviarAlerta({ tipo: ETipoAlerta.SUCESSO, mensagem: 'Função removida com sucesso!' })
    });
  }

  // Funções de salvamento para Banco e Função
  private salvarBancoEdital(bancoEdital: BancoEdital): void {
    this.servicoBancoEdital.save(bancoEdital).subscribe({
      complete: () => this.servicoAlerta.enviarAlerta({ tipo: ETipoAlerta.SUCESSO, mensagem: 'Banco vinculado com sucesso!' })
    });
  }

  private salvarFuncaoEdital(funcaoEdital: FuncaoEdital): void {
    this.servicoFuncEdital.save(funcaoEdital).subscribe({
      complete: () => this.servicoAlerta.enviarAlerta({ tipo: ETipoAlerta.SUCESSO, mensagem: 'Função vinculada com sucesso!' })
    });
  }

  // Redirecionar para próxima página com o ID do edital
  redirecionarComId(): void {
    this.router.navigate(['/editalplanoapresentacao/form'], { queryParams: { id: this.idEdital } });
  }
}
