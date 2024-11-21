import { TestBed } from '@angular/core/testing';

import { AtividadePropostaService } from './atividade-proposta.service';

describe('AtividadePropostaService', () => {
  let service: AtividadePropostaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AtividadePropostaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
