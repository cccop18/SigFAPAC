import { TestBed } from '@angular/core/testing';

import { OrcamentoPropostaService } from './orcamento-proposta.service';

describe('OrcamentoPropostaService', () => {
  let service: OrcamentoPropostaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OrcamentoPropostaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
