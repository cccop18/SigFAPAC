import { TestBed } from '@angular/core/testing';

import { ArquivoeditalService } from './arquivoedital.service';

describe('ArquivoeditalService', () => {
  let service: ArquivoeditalService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ArquivoeditalService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
