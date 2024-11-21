import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { IForm } from '../../../i-form';
import { DocPessoal } from '../../../../model/DocPessoal';
import { DocpessoalService } from '../../../../service/docpessoal/docpessoal.service';
import { AlertaService } from '../../../../service/alerta/alerta.service';
import { ETipoAlerta } from '../../../../model/ETipoAlerta';

@Component({
  selector: 'app-doc-pessoais-form',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule],
  templateUrl: './doc-pessoais-form.component.html',
  styleUrl: './doc-pessoais-form.component.css'
})
export class DocPessoaisFormComponent implements IForm<DocPessoal> {

  constructor(
    private servico: DocpessoalService,
    private servicoAlerta: AlertaService,
    private router: Router,
    private route: ActivatedRoute) { }


    ngOnInit(): void {

      const id = this.route.snapshot.queryParamMap.get('id');
      if (id) {
        this.servico.getById(+id).subscribe({
          next: (resposta: DocPessoal) => {
            console.log(resposta);
            this.registro = resposta;
            this.formDocPessoal.patchValue(this.registro);
          }
        });
      }
  
    }
    
  formDocPessoal = new FormGroup({
      idPessoais: new FormControl<number | null>(null),
      nomeDocPessoal: new FormControl<string | null>(null),
      ativoDocPessoal: new FormControl<boolean | null>(null), // TODO: Verificar se é realmente string e se é fileArquivo ou ativoArquivo
    });
  
    get form() {
      return this.formDocPessoal.controls;
    }


  registro: DocPessoal = <DocPessoal>{};


  save(): void {
    this.registro = Object.assign(this.registro, this.formDocPessoal.value);
    this.servico.save(this.registro).subscribe({
      complete: () => {
        this.router.navigate(['/editais/']);
        this.servicoAlerta.enviarAlerta({
          tipo: ETipoAlerta.SUCESSO,
          mensagem: "Operação realizada com sucesso!"
        });
      }
    });
  }

}
