import { TestBed } from '@angular/core/testing';

import { FaixaFinanciamentoService } from './faixa-financiamento.service';

describe('FaixaFinanciamentoService', () => {
  let service: FaixaFinanciamentoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FaixaFinanciamentoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
