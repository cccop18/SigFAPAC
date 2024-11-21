import { TestBed } from '@angular/core/testing';

import { BancoEditalService } from './banco-edital.service';

describe('BancoEditalService', () => {
  let service: BancoEditalService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BancoEditalService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
