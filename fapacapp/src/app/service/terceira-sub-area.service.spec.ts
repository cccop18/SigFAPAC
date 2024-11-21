import { TestBed } from '@angular/core/testing';

import { TerceiraSubAreaService } from './terceira-sub-area.service';

describe('TerceiraSubAreaService', () => {
  let service: TerceiraSubAreaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TerceiraSubAreaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
