import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { IForm } from '../i-form';
import { InstituicaoService } from '../../service/instituicao.service';
import { EnderecoUnidade } from '../../model/EnderecoUnidade';
import { Instituicao } from '../../model/Instituicao';
import { ETipoAlerta } from '../../model/ETipoAlerta';
import { RespostaPaginada } from '../../model/RespostaPaginada';
import { UnidadeInstituicaoDto } from '../../model/UnidadeInstituicaoDto';
import { UnidadeInstituicaoDtoService } from '../../service/unidade-instituicao-dto.service';
import { CepService } from '../../service/api/cep-service.service';  // Importando o serviço de CEP
import { AlertaService } from '../../service/alerta/alerta.service';
import { AlertaComponent } from '../alerta/alerta.component';

@Component({
  selector: 'app-cadastro-unidade',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule, AlertaComponent],
  templateUrl: './cadastro-unidade.component.html',
  styleUrls: ['./cadastro-unidade.component.css']  // Corrigido para styleUrls
})
export class CadastroUnidadeComponent implements IForm<UnidadeInstituicaoDto>{

  constructor(
    private servico: UnidadeInstituicaoDtoService,
    private servicoAlerta: AlertaService,
    private servicoInstituicao: InstituicaoService,
    private cepService: CepService,  // Injetando o serviço de CEP
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.servicoInstituicao.get().subscribe({
      next: (resposta: RespostaPaginada<Instituicao>) => {
        this.instituicao = resposta.content;
      }
    });

    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      this.servico.getById(+id).subscribe({
        next: (resposta: UnidadeInstituicaoDto) => {
          this.registro = resposta;
          this.formUnidade.patchValue(this.registro);
        }
      });
    }
  }

  registro: UnidadeInstituicaoDto = <UnidadeInstituicaoDto>{};
  endereco: EnderecoUnidade = <EnderecoUnidade>{};
  instituicao: Instituicao[] = [];

  formUnidade = new FormGroup({
    nomeUnidade: new FormControl<string | null>(null),
    emailUnidade: new FormControl<string | null>(null, Validators.email),
    telefoneUnidade: new FormControl<string | null>(null, Validators.pattern("^\\(\\d{2}\\) \\d{4,5}-\\d{4}$")),
    idInstituicao: new FormControl<number | null>(null),
    cepEnderecoUnidade: new FormControl<string | null>(null, 
      [Validators.required, Validators.pattern(/^[0-9]{5}-?[0-9]{3}$/)]),  // Adicionado required
    logradouroEnderecoUnidade: new FormControl<string | null>(null),
    numeroEnderecoUnidade: new FormControl<string | null>(null),
    bairroEnderecoUnidade: new FormControl<string | null>(null),
    estadoEnderecoUnidade: new FormControl<string | null>(null),
    paisEnderecoUnidade: new FormControl<string | null>(null),
  });  

  get form() {
    return this.formUnidade.controls;
  }

  verificarCep(): void {
    const cep = this.formUnidade.get('cepEnderecoUnidade')?.value;
    if (cep && this.formUnidade.get('cepEnderecoUnidade')?.valid) {
      this.cepService.consultarCep(cep).subscribe({
        next: (dados) => {
          if (dados.erro) {
            this.formUnidade.get('cepEnderecoUnidade')?.setErrors({ cepInvalido: true });
          } else {
            // Preencher os campos de endereço automaticamente com base no CEP
            this.formUnidade.patchValue({
              logradouroEnderecoUnidade: dados.logradouro,
              bairroEnderecoUnidade: dados.bairro,
              estadoEnderecoUnidade: dados.uf,
              paisEnderecoUnidade: 'Brasil'
            });
          }
        },
        error: () => {
          this.formUnidade.get('cepEnderecoUnidade')?.setErrors({ cepInvalido: true });
        }
      });
    }
  }

  save(): void {
    this.registro = Object.assign(this.registro, this.formUnidade.value);
    this.servico.save(this.registro).subscribe({
      complete: () => {
        this.router.navigate(['/cadastro/instituicao']);
        this.servicoAlerta.enviarAlerta({
          tipo: ETipoAlerta.SUCESSO,
          mensagem: "Operação realizada com sucesso!"
        });
      }
    });
  }
}
