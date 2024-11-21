import { TestBed } from '@angular/core/testing';

import { VinculoInstitucionalService } from './vinculo-institucional.service';

describe('VinculoInstitucionalService', () => {
  let service: VinculoInstitucionalService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VinculoInstitucionalService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
