import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { IForm } from '../../../i-form';
import { Abrangencia } from '../../../../model/Abrangencia';
import { AbrangenciaService } from '../../../../service/abrangencia/abrangencia.service';
import { AlertaService } from '../../../../service/alerta/alerta.service';
import { EditalService } from '../../../../service/edital/edital.service';
import { EstadoService } from '../../../../service/estado/estado.service';
import { RespostaPaginada } from '../../../../model/RespostaPaginada';
import { Estado } from '../../../../model/Estado';
import { Edital } from '../../../../model/Edital';
import { ETipoAlerta } from '../../../../model/ETipoAlerta';
import { MenuEditalComponent } from '../../../menu-edital/menu-edital.component';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-abrangencia-form',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule, MenuEditalComponent],
  templateUrl: './abrangencia-form.component.html',
  styleUrls: ['./abrangencia-form.component.css']
})
export class AbrangenciaFormComponent implements OnInit, IForm<Abrangencia> {

  constructor(
    private servico: AbrangenciaService,
    private router: Router,
    private route: ActivatedRoute,
    private servicoAlerta: AlertaService,
    private servicoEdital: EditalService,
    private servicoEstado: EstadoService
  ) { }

  ngOnInit(): void {
    // Carregar a lista de estados
    this.servicoEstado.get().subscribe({
      next: (resposta: RespostaPaginada<Estado>) => {
        this.estados = resposta.content;
  
        // Verificar se há um ID para carregar as informações existentes
        const id = this.route.snapshot.queryParamMap.get('id');
        if (id) {
          this.servico.getById(+id).subscribe({
            next: (resposta: Abrangencia) => {
              this.registro = resposta;
  
              // Aplicar valores do registro ao formulário
              this.formAbrangencias.patchValue({
                idAbrangencia: this.registro.idAbrangencia,
                edital: this.registro.edital,
                estados: [] // Temporariamente vazio até carregar os estados corretamente
              });
  
              // Encontrar os estados correspondentes aos armazenados no registro
              const estadosSelecionados = this.estados.filter(estado =>
                this.registro.estados.some((e: Estado) => e.idEstado === estado.idEstado)
              );
  
              // Definir os estados no controle do formulário
              this.formAbrangencias.get('estados')?.setValue(estadosSelecionados);
              this.selectedEstados = estadosSelecionados; // Atualizar a visualização dos estados selecionados
            }
          });
        } else {
          // Se não há ID, resetar os estados
          this.formAbrangencias.get('estados')?.setValue([]);
          this.selectedEstados = [];
        }
      }
    });
  
    // Atualizar a lista de estados selecionados ao alterar o valor do controle
    this.formAbrangencias.get('estados')?.valueChanges.subscribe(selectedEstados => {
      if (selectedEstados) {
        this.selectedEstados = selectedEstados;
      }
    });
  }
    

  formAbrangencias = new FormGroup({
    idAbrangencia: new FormControl<number | null>(null),
    edital: new FormControl<number>(1),
    estados: new FormControl<Estado[] | null>([]), // Controle de múltiplos estados
  });

  get form() {
    return this.formAbrangencias.controls;
  }

  registro: Abrangencia = <Abrangencia>{};
  estados: Estado[] = [];
  selectedEstados: Estado[] = [];
  estadosSelecionados: Estado = <Estado>{};
  
  addEstados(){
      this.estados.push(this.estadosSelecionados);

  }
  
  save(): void {
    const idEdital = this.route.snapshot.queryParamMap.get('id'); // Captura o idEdital da query string
  
    this.registro = Object.assign(this.registro, this.formAbrangencias.value);
    console.log(this.registro);
  
    this.servico.save(this.registro).subscribe({
      complete: () => {
        // Navega para a página de formulário, passando o idEdital na query string
        this.router.navigate(['/editalorcamento/form'], {
          queryParams: { id: idEdital }
        });
  
        this.servicoAlerta.enviarAlerta({
          tipo: ETipoAlerta.SUCESSO,
          mensagem: 'Operação realizada com sucesso!'
        });
      }
    });
  }

  onAbrangenciaChange(valor: string): void {
    // Se "NACIONAL" for selecionado, desabilita a seleção de estados
    if (valor === 'NACIONAL') {
      this.formAbrangencias.get('estados')?.disable();
    } else {
      this.formAbrangencias.get('estados')?.enable();
    }
  }  

}
