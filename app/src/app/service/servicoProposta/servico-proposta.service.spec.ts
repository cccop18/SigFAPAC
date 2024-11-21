import { TestBed } from '@angular/core/testing';

import { ServicoPropostaService } from './servico-proposta.service';

describe('ServicoPropostaService', () => {
  let service: ServicoPropostaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServicoPropostaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
