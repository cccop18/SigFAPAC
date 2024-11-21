import { TestBed } from '@angular/core/testing';

import { PrimeiraSubAreaService } from './primeira-sub-area.service';

describe('PrimeiraSubAreaService', () => {
  let service: PrimeiraSubAreaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PrimeiraSubAreaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
