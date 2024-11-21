import { TestBed } from '@angular/core/testing';

import { SegundaSubAreaService } from './segunda-sub-area.service';

describe('SegundaSubAreaService', () => {
  let service: SegundaSubAreaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SegundaSubAreaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
