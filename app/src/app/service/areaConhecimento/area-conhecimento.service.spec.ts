import { TestBed } from '@angular/core/testing';

import { AreaConhecimentoService } from './area-conhecimento.service';

describe('AreaConhecimentoService', () => {
  let service: AreaConhecimentoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AreaConhecimentoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
