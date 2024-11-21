import { TestBed } from '@angular/core/testing';

import { RetificacoesService } from './retificacoes.service';

describe('RetificacoesService', () => {
  let service: RetificacoesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RetificacoesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
