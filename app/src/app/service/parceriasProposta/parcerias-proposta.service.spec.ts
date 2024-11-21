import { TestBed } from '@angular/core/testing';

import { ParceriasPropostaService } from './parcerias-proposta.service';

describe('ParceriasPropostaService', () => {
  let service: ParceriasPropostaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ParceriasPropostaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
