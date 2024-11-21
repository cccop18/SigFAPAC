import { TestBed } from '@angular/core/testing';

import { DiariaPropostaService } from './diaria-proposta.service';

describe('DiariaPropostaService', () => {
  let service: DiariaPropostaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DiariaPropostaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
