import { TestBed } from '@angular/core/testing';

import { EditalPlanoApresentacaoService } from './edital-plano-apresentacao.service';

describe('EditalPlanoApresentacaoService', () => {
  let service: EditalPlanoApresentacaoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EditalPlanoApresentacaoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
