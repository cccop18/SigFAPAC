import { TestBed } from '@angular/core/testing';

import { UnidadeInstituicaoDtoService } from './unidade-instituicao-dto.service';

describe('UnidadeInstituicaoDtoService', () => {
  let service: UnidadeInstituicaoDtoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UnidadeInstituicaoDtoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
