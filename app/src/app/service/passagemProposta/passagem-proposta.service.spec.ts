import { TestBed } from '@angular/core/testing';

import { PassagemPropostaService } from './passagem-proposta.service';

describe('PassagemPropostaService', () => {
  let service: PassagemPropostaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PassagemPropostaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
