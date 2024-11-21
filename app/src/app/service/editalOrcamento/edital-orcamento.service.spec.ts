import { TestBed } from '@angular/core/testing';

import { EditalOrcamentoService } from './edital-orcamento.service';

describe('EditalOrcamentoService', () => {
  let service: EditalOrcamentoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EditalOrcamentoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
