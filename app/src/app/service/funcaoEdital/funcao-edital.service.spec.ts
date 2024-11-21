import { TestBed } from '@angular/core/testing';

import { FuncaoEditalService } from './funcao-edital.service';

describe('FuncaoEditalService', () => {
  let service: FuncaoEditalService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FuncaoEditalService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
