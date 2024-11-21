import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { MenuEditalComponent } from '../../menu-edital/menu-edital.component';
import { EditalRestricoesService } from '../../../service/editalRestricoes/edital-restricoes.service';
import { AlertaService } from '../../../service/alerta/alerta.service';
import { EditalRestricoes } from '../../../model/EditalRestricoes';
import { ETipoAlerta } from '../../../model/ETipoAlerta';

@Component({
  selector: 'app-edital-restricoes-form',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, MenuEditalComponent, ReactiveFormsModule],
  templateUrl: './edital-restricoes-form.component.html',
  styleUrl: './edital-restricoes-form.component.css'
})
export class EditalRestricoesFormComponent implements OnInit {
  constructor(
    private servico: EditalRestricoesService,
    private servicoAlerta: AlertaService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      this.servico.getById(+id).subscribe({
        next: (resposta: EditalRestricoes) => {
          this.registro = resposta;
          console.log('Registro carregado:', this.registro);
          this.formEditalRestricoes.patchValue(this.registro);
          console.log('Estado do formulário após patchValue:', this.formEditalRestricoes.value);
        }
      });
    }
  
    // Monitorar o checkbox 'habilitaVinculoEmpregaEdital'
    this.formEditalRestricoes.get('habilitaVinculoEmpregaEdital')?.valueChanges.subscribe((valor: boolean | null) => {
      if (!valor) {
        // Desmarcar os radios relacionados a 'Vínculo Empregatício'
        this.formEditalRestricoes.get('proponenteVinculoEmpregaEdital')?.setValue(null);
        // Também podemos desabilitar os rádios para impedir o clique
        this.formEditalRestricoes.get('proponenteVinculoEmpregaEdital')?.disable();
      } else {
        // Habilitar novamente os rádios se o checkbox for marcado
        this.formEditalRestricoes.get('proponenteVinculoEmpregaEdital')?.enable();
      }
    });
  
    // Monitorar o checkbox 'habilitaVinculoInstituicaoEdital'
    this.formEditalRestricoes.get('habilitaVinculoInstituicaoEdital')?.valueChanges.subscribe((valor: boolean | null) => {
      if (!valor) {
        // Desmarcar os radios relacionados a 'Vínculo Institucional'
        this.formEditalRestricoes.get('proponenteVinculoInstituicaoEdital')?.setValue(null);
        this.formEditalRestricoes.get('proponenteVinculoInstituicaoEdital')?.disable();
      } else {
        this.formEditalRestricoes.get('proponenteVinculoInstituicaoEdital')?.enable();
      }
    });
  }  

  registro: EditalRestricoes = <EditalRestricoes>{};

  formEditalRestricoes = new FormGroup({
    proponenteCurriculoEdital: new FormControl<boolean | null>(null),
    membroCurriculoEdital: new FormControl<boolean | null>(null),
    habilitaVinculoEmpregaEdital: new FormControl<boolean | null>(null),
    proponenteVinculoEmpregaEdital: new FormControl<boolean | null>(null),
    habilitaVinculoInstituicaoEdital: new FormControl<boolean | null>(null),
    proponenteVinculoInstituicaoEdital: new FormControl<boolean | null>(null),
    proponenteNivelAcademicoEdital:  new FormControl<string | null>(null)
  });

  get form() {
    return this.formEditalRestricoes.controls;
  }

  save(): void {
    this.registro = Object.assign(this.registro, this.formEditalRestricoes.value);
    this.servico.save(this.registro).subscribe({
      complete: () => {
        this.router.navigate(['/edital/']);
        this.servicoAlerta.enviarAlerta({
          tipo: ETipoAlerta.SUCESSO,
          mensagem: "Operação realizada com sucesso!"
        });
        this.router.navigate(['/editalprestacao/form'],{
          queryParams: {id: this.registro.idEdital}
        } );
      }
    });
  }
}
