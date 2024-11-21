import { TestBed } from '@angular/core/testing';

import { TipoPesquisadorService } from './tipo-pesquisador.service';

describe('TipoPesquisadorService', () => {
  let service: TipoPesquisadorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TipoPesquisadorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
