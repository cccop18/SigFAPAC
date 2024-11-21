import { TestBed } from '@angular/core/testing';

import { HospedagemAlimenPropostaService } from './hospedagem-alimen-proposta.service';

describe('HospedagemAlimenPropostaService', () => {
  let service: HospedagemAlimenPropostaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HospedagemAlimenPropostaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
