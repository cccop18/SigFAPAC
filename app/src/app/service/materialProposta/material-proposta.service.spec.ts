import { TestBed } from '@angular/core/testing';

import { MaterialPropostaService } from './material-proposta.service';

describe('MaterialPropostaService', () => {
  let service: MaterialPropostaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MaterialPropostaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
