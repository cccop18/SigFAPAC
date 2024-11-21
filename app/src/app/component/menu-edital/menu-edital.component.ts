import { Component } from '@angular/core';
import { ActivatedRoute, Router, RouterLink, RouterLinkActive } from '@angular/router';
import { EditaldadosFormComponent } from '../editalDados/editaldados-form/editaldados-form.component';
import { EditaldadosListComponent } from '../editalDados/editaldados-list/editaldados-list.component';
import { EditalService } from '../../service/edital/edital.service';
import { Edital } from '../../model/Edital';

@Component({
  selector: 'app-menu-edital',
  standalone: true,
  imports: [RouterLink,RouterLinkActive],
  templateUrl: './menu-edital.component.html',
  styleUrl: './menu-edital.component.css'
})
export class MenuEditalComponent {

  registro: Edital = <Edital>{};

  constructor(private router: Router, private route: ActivatedRoute, private servico: EditalService) {}

  ngOnInit(): void {
    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      this.servico.getById(+id).subscribe({
        next: (resposta: Edital) => {
          this.registro = resposta;
        }
      });
    }
  }

  isRouteActive(route: string): boolean {
    return this.router.url.includes(route);
  }
}

