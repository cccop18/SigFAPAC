import { Component } from '@angular/core';
import { ActivatedRoute, Router, RouterLink, RouterLinkActive } from '@angular/router';
import { PropostaService } from '../../../service/proposta/proposta.service';
import { Proposta } from '../../../model/Proposta';
import { Edital } from '../../../model/Edital';
import { EditalService } from '../../../service/edital/edital.service';
import { OrcamentoProposta } from '../../../model/OrcamentoProposta';
import { OrcamentoPropostaService } from '../../../service/orcamentoProposta/orcamento-proposta.service';

@Component({
  selector: 'app-menu-proposta',
  standalone: true,
  imports: [RouterLink,RouterLinkActive],
  templateUrl: './menu-proposta.component.html',
  styleUrl: './menu-proposta.component.css'
})
export class MenuPropostaComponent {

  registro: Proposta = <Proposta>{};
  registroEdital: Edital = <Edital>{};
  registroOrcamento: OrcamentoProposta = <OrcamentoProposta>{};

  constructor(private router: Router, private route: ActivatedRoute, private servico: PropostaService, private servicoEdital: EditalService, private servicoOrcamento: OrcamentoPropostaService) {}

  ngOnInit(): void {
    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      this.servico.getById(+id).subscribe({
        next: (resposta: Proposta) => {
          this.registro = resposta;
        }
      });
    }

    const idEdital = this.route.snapshot.queryParamMap.get('idEdital');
    if (idEdital) {
      this.servicoEdital.getById(+idEdital).subscribe({
        next: (resposta: Edital) => {
          this.registroEdital = resposta;
        }
      });
    }

    const orcamentoId = this.route.snapshot.queryParamMap.get('orcamentoId');
    if (orcamentoId) {
      this.servicoOrcamento.getById(+orcamentoId).subscribe({
        next: (resposta: OrcamentoProposta) => {
          this.registroOrcamento = resposta;
        }
      });
    }
  }

  isRouteActive(route: string): boolean {
    return this.router.url.includes(route);
  }
}

