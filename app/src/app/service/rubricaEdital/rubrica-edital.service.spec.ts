import { TestBed } from '@angular/core/testing';

import { RubricaEditalService } from './rubrica-edital.service';

describe('RubricaEditalService', () => {
  let service: RubricaEditalService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RubricaEditalService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
