import { TestBed } from '@angular/core/testing';

import { EnderecoUnidadeService } from './endereco-unidade.service';

describe('EnderecoUnidadeService', () => {
  let service: EnderecoUnidadeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EnderecoUnidadeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
