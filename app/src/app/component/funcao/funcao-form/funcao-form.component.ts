import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Router, ActivatedRoute, RouterLink } from '@angular/router';
import { Funcao } from '../../../model/Funcao';
import { FuncaoService } from '../../../service/funcao/funcao.service';
import { AlertaService } from '../../../service/alerta/alerta.service';
import { ETipoAlerta } from '../../../model/ETipoAlerta';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-funcao-form',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule],
  templateUrl: './funcao-form.component.html',
  styleUrls: ['./funcao-form.component.css']
})
export class FuncaoFormComponent implements OnInit {

  constructor(
    private servico: FuncaoService,
    private router: Router,
    private route: ActivatedRoute,
    private servicoAlerta: AlertaService
  ) {}

  ngOnInit(): void {
    // Verifica se há um ID nos parâmetros para carregar uma função existente
    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      this.servico.getById(+id).subscribe({
        next: (resposta: Funcao) => {
          this.registro = resposta;
          this.formFuncao.patchValue(this.registro);
        }
      });
    }
  }

  formFuncao = new FormGroup({
    nomeFuncao: new FormControl<string | null>(null, Validators.required),
    ativoFuncao: new FormControl<boolean>(true)
  });

  get form() {
    return this.formFuncao.controls;
  }

  registro: Funcao = <Funcao>{};

  save(): void {
    this.registro = Object.assign(this.registro, this.formFuncao.value);
    this.servico.save(this.registro).subscribe({
      complete: () => {
        this.router.navigate(['/funcao']);
        this.servicoAlerta.enviarAlerta({
          tipo: ETipoAlerta.SUCESSO,
          mensagem: 'Função salva com sucesso!'
        });
      }
    });
  }
}
