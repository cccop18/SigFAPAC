import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { MenuEditalComponent } from '../../menu-edital/menu-edital.component';
import { AlertaService } from '../../../service/alerta/alerta.service';
import { EditalPlanoApresentacaoService } from '../../../service/editalPlanoApresentacao/edital-plano-apresentacao.service';
import { ETipoAlerta } from '../../../model/ETipoAlerta';
import { EditalPlanoApresentacao } from '../../../model/EditalPlanoApresentacao';

@Component({
  selector: 'app-edital-plano-apresentacao',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule, MenuEditalComponent],
  templateUrl: './edital-plano-apresentacao.component.html',
  styleUrl: './edital-plano-apresentacao.component.css'
})
export class EditalPlanoApresentacaoFormComponent {
  constructor(
    private servico: EditalPlanoApresentacaoService,
    private servicoAlerta: AlertaService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      this.servico.getById(+id).subscribe({
        next: (resposta: EditalPlanoApresentacao) => {
          this.registro = resposta;
          console.log('Registro carregado:', this.registro);
          this.formEditalPlanoApresentacao.patchValue(this.registro);
          console.log('Estado do formulário após patchValue:', this.formEditalPlanoApresentacao.value);
        }
      });
    }
  }

  registro: EditalPlanoApresentacao = <EditalPlanoApresentacao>{};

  formEditalPlanoApresentacao = new FormGroup({
    experienciaCordenadorEdital: new FormControl<boolean | null>(null),
    objetivoGeralEdital: new FormControl<boolean | null>(null),
    objetivoEspecificoEdital: new FormControl<boolean | null>(null),
    metodologiaEdital: new FormControl<boolean | null>(null),
    metodoMatEdital: new FormControl<boolean | null>(null),
    resultadosEdital: new FormControl<boolean | null>(null),
    impactoEsperadoEdital: new FormControl<boolean | null>(null),
    impactoDiscriminadoEdital: new FormControl<boolean | null>(null),
    riscoAtividadeEdital: new FormControl<boolean | null>(null),
    referenciaBlibEdital: new FormControl<boolean | null>(null),
    estadoArteEdital: new FormControl<boolean | null>(null),
    justCopInterEdital: new FormControl<boolean | null>(null),
    qualiParceriasEdital: new FormControl<boolean | null>(null),
    obrasInstalEdital: new FormControl<boolean | null>(null),
    prodBlibEdital: new FormControl<boolean | null>(null),
    prodCulturaEdital: new FormControl<boolean | null>(null),
    prodTecEdital: new FormControl<boolean | null>(null),
    prodDifuEdital: new FormControl<boolean | null>(null)
  });

  get form() {
    return this.formEditalPlanoApresentacao.controls;
  }

  save(): void {
    this.registro = Object.assign(this.registro, this.formEditalPlanoApresentacao.value);
    this.servico.save(this.registro).subscribe({
      complete: () => {
        this.router.navigate(['/editais/']);
        this.servicoAlerta.enviarAlerta({
          tipo: ETipoAlerta.SUCESSO,
          mensagem: "Operação realizada com sucesso!"
        });
        this.router.navigate(['/editalmodelo/form'],{
          queryParams: {id: this.registro.idEdital}
        } );
      }
    });
  }
}