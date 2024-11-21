import { TestBed } from '@angular/core/testing';

import { MaterialPermPropostaService } from './material-perm-proposta.service';

describe('MaterialPermPropostaService', () => {
  let service: MaterialPermPropostaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MaterialPermPropostaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
