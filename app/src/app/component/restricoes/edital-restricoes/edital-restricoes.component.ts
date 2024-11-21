import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MenuEditalComponent } from "../../menu-edital/menu-edital.component";
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { EditalRestricoesService } from '../../../service/editalRestricoes/edital-restricoes.service';
import { AlertaService } from '../../../service/alerta/alerta.service';
import { EditalRestricoes } from '../../../model/EditalRestricoes';

@Component({
  selector: 'app-edital-restricoes',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, MenuEditalComponent, ReactiveFormsModule],
  templateUrl: './edital-restricoes.component.html',
  styleUrls: ['./edital-restricoes.component.css']
})
export class EditalRestricoesComponent implements OnInit {
  restricoesForm!: FormGroup;

  constructor(private fb: FormBuilder, private servico: EditalRestricoesService,
    private servicoAlerta: AlertaService,
    private router: Router,
    private route: ActivatedRoute) {}

  ngOnInit(): void {
    // Inicializa o FormGroup com os controles para cada checkbox e radio button

    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      this.servico.getById(+id).subscribe({
        next: (resposta: EditalRestricoes) => {
          this.registro = resposta;
          this.restricoesForm.patchValue(this.registro);
        }
      });
    }

    this.restricoesForm = this.fb.group({
      proponenteCurriculoEdital: [false], // Checkbox - Proponente ter Currículo Lattes
      equipeCurriculoEdital: [false], // Checkbox - Equipe ter Currículo Lattes
      habilitarVinculoEmpregaticio: [false], // Checkbox - Habilitar restrição de vínculo empregatício
      proponenteComVinculo: [{ value: '', disabled: true }], // Radio - Valor selecionado (COM_VINCULO ou SEM_VINCULO)
      proponenteSemVinculo: [{ value: '', disabled: true }], // Radio - Valor selecionado (COM_VINCULO ou SEM_VINCULO)
      habilitaVinculoInstituicaoEdital: [false], // Checkbox - Habilitar restrição de vínculo institucional
      proponenteVinculoInstituicaoEdital: [{ value: '', disabled: true }], // Radio - Valor selecionado (COM_VINCULO ou SEM_VINCULO)
      proponenteNivelAcademicoEdital: [{ value: '', disabled: true }] // Radio - Valor selecionado (COM_VINCULO ou SEM_VINCULO)
    });

    // Observa mudanças nos checkboxes para habilitar ou desabilitar os radio buttons
    this.restricoesForm.get('habilitarVinculoEmpregaticio')?.valueChanges.subscribe((checked: boolean) => {
      if (checked) {
        this.restricoesForm.get('proponenteComVinculo')?.enable();
        this.restricoesForm.get('proponenteSemVinculo')?.enable();
      } else {
        this.restricoesForm.get('proponenteComVinculo')?.disable();
        this.restricoesForm.get('proponenteSemVinculo')?.disable();
        this.restricoesForm.get('proponenteComVinculo')?.setValue(null);
        this.restricoesForm.get('proponenteSemVinculo')?.setValue(null);
      }
    });

    this.restricoesForm.get('habilitaVinculoInstituicaoEdital')?.valueChanges.subscribe((checked: boolean) => {
      if (checked) {
        this.restricoesForm.get('proponenteVinculoInstituicaoEdital')?.enable();
        this.restricoesForm.get('proponenteNivelAcademicoEdital')?.enable();
      } else {
        this.restricoesForm.get('proponenteVinculoInstituicaoEdital')?.disable();
        this.restricoesForm.get('proponenteNivelAcademicoEdital')?.disable();
        this.restricoesForm.get('proponenteVinculoInstituicaoEdital')?.setValue(null);
        this.restricoesForm.get('proponenteNivelAcademicoEdital')?.setValue(null);
      }
    });
  }

  registro: EditalRestricoes = <EditalRestricoes>{};

  // Método para salvar as informações do formulário
  save(): void {
    if (this.restricoesForm.valid) {
      console.log('Formulário de restrições do edital:', this.restricoesForm.value);
      // Aqui você pode chamar o serviço para salvar os dados no backend
    }
  }

  // Método para cancelar e resetar o formulário
  cancel(): void {
    this.restricoesForm.reset(); // Reseta o formulário para o estado inicial
  }

  empregadoAtivo: boolean = false;
  gestorAtivo: boolean = false;


  toggleEmpregadoSection(event: Event): void {
    this.empregadoAtivo = (event.target as HTMLInputElement).checked;
    if (!this.empregadoAtivo) {
      this.desmarcarRadiosEmpregado();
    }
  }

  desmarcarRadiosEmpregado(): void {
    const radios = document.querySelectorAll<HTMLInputElement>('input[name="proponenteComVinculo"]');
    radios.forEach(radio => radio.checked = false);
  }

  toggleGestorSection(event: Event): void {
    this.gestorAtivo = (event.target as HTMLInputElement).checked;
    if (!this.gestorAtivo) {
      this.desmarcarRadiosGestor();
    }
  }

  desmarcarRadiosGestor(): void {
    const radios = document.querySelectorAll<HTMLInputElement>('input[name="proponenteVinculoInstituicaoEdital"]');
    radios.forEach(radio => radio.checked = false);
  }
}
