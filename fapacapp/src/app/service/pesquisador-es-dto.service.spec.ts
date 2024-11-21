import { TestBed } from '@angular/core/testing';

import { PesquisadorEsDtoService } from './pesquisador-es-dto.service';

describe('PesquisadorEsDtoService', () => {
  let service: PesquisadorEsDtoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PesquisadorEsDtoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
