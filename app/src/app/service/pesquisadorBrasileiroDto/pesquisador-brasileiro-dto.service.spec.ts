import { TestBed } from '@angular/core/testing';

import { PesquisadorBrasileiroDtoService } from './pesquisador-brasileiro-dto.service';

describe('PesquisadorBrasileiroDtoService', () => {
  let service: PesquisadorBrasileiroDtoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PesquisadorBrasileiroDtoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
