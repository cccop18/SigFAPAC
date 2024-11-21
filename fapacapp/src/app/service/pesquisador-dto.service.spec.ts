import { TestBed } from '@angular/core/testing';

import { PesquisadorDtoService } from './pesquisador-dto.service';

describe('PesquisadorDtoService', () => {
  let service: PesquisadorDtoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PesquisadorDtoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
