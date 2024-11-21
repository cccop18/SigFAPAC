import { TestBed } from '@angular/core/testing';

import { UnidadeInstituicaoService } from './unidade-instituicao.service';

describe('UnidadeInstituicaoService', () => {
  let service: UnidadeInstituicaoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UnidadeInstituicaoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
