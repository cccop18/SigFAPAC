import { TestBed } from '@angular/core/testing';

import { VinculoPropostaService } from './vinculo-proposta.service';

describe('VinculoPropostaService', () => {
  let service: VinculoPropostaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VinculoPropostaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
