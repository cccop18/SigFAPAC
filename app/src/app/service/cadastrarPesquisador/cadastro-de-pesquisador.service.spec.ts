import { TestBed } from '@angular/core/testing';

import { CadastroDePesquisadorService } from './cadastro-de-pesquisador.service';

describe('CadastroDePesquisadorService', () => {
  let service: CadastroDePesquisadorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CadastroDePesquisadorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
