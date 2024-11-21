import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { MoedaService } from '../../../service/moeda/moeda.service';
import { AlertaService } from '../../../service/alerta/alerta.service';
import { Moeda } from '../../../model/Moeda';
import { ETipoAlerta } from '../../../model/ETipoAlerta';

@Component({
  selector: 'app-moeda-form',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule],
  templateUrl: './moeda-form.component.html',
  styleUrl: './moeda-form.component.css'
})
export class MoedaFormComponent implements OnInit {
  constructor(
    private servico: MoedaService,
    private router: Router,
    private route: ActivatedRoute,
    private servicoAlerta: AlertaService
  ) {}

  ngOnInit(): void {
    // Verifica se há um ID nos parâmetros para carregar uma moeda existente
    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      this.servico.getById(+id).subscribe({
        next: (resposta: Moeda) => {
          this.registro = resposta;
          this.formMoeda.patchValue(this.registro);
        }
      });
    }
  }

  formMoeda = new FormGroup({
    nomeMoeda: new FormControl<string | null>(null, Validators.required),
    ativaMoeda: new FormControl<boolean>(true)
  });

  get form() {
    return this.formMoeda.controls;
  }

  registro: Moeda = <Moeda>{};

  save(): void {
    this.registro = Object.assign(this.registro, this.formMoeda.value);
    this.servico.save(this.registro).subscribe({
      complete: () => {
        this.router.navigate(['/moeda']);
        this.servicoAlerta.enviarAlerta({
          tipo: ETipoAlerta.SUCESSO,
          mensagem: 'Moeda salva com sucesso!'
        });
      }
    });
  }
}

