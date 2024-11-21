import { TestBed } from '@angular/core/testing';

import { DocpessoalService } from './docpessoal.service';

describe('DocpessoalService', () => {
  let service: DocpessoalService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DocpessoalService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
