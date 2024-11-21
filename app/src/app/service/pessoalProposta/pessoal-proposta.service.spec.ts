import { TestBed } from '@angular/core/testing';

import { PessoalPropostaService } from './pessoal-proposta.service';

describe('PessoalPropostaService', () => {
  let service: PessoalPropostaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PessoalPropostaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
