import { TestBed } from '@angular/core/testing';

import { AbrangenciaPropostaService } from './abrangencia-proposta.service';

describe('AbrangenciaPropostaService', () => {
  let service: AbrangenciaPropostaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AbrangenciaPropostaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
