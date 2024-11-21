import { TestBed } from '@angular/core/testing';

import { EnderecoPesquisadorService } from './endereco-pesquisador.service';

describe('EnderecoPesquisadorService', () => {
  let service: EnderecoPesquisadorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EnderecoPesquisadorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
