import { TestBed } from '@angular/core/testing';

import { PesquisadorConhecimentoService } from './pesquisador-conhecimento.service';

describe('PesquisadorConhecimentoService', () => {
  let service: PesquisadorConhecimentoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PesquisadorConhecimentoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
