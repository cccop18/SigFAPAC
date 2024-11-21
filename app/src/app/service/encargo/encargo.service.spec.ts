import { TestBed } from '@angular/core/testing';

import { EncargoService } from './encargo.service';

describe('EncargoService', () => {
  let service: EncargoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EncargoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
