import { TestBed } from '@angular/core/testing';

import { EditalChmadasService } from './edital-chmadas.service';

describe('EditalChmadasService', () => {
  let service: EditalChmadasService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EditalChmadasService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
