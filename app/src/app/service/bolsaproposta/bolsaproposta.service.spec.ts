import { TestBed } from '@angular/core/testing';

import { BolsapropostaService } from './bolsaproposta.service';

describe('BolsapropostaService', () => {
  let service: BolsapropostaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BolsapropostaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
