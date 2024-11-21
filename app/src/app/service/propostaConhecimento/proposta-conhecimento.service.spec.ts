import { TestBed } from '@angular/core/testing';

import { PropostaConhecimentoService } from './proposta-conhecimento.service';

describe('PropostaConhecimentoService', () => {
  let service: PropostaConhecimentoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PropostaConhecimentoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
