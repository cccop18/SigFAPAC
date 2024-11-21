import { TestBed } from '@angular/core/testing';

import { DocpessoaleditService } from './docpessoaledit.service';

describe('DocpessoaleditService', () => {
  let service: DocpessoaleditService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DocpessoaleditService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
