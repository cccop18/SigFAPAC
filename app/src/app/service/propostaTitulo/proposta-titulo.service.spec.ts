import { TestBed } from '@angular/core/testing';

import { PropostaTituloService } from './proposta-titulo.service';

describe('PropostaTituloService', () => {
  let service: PropostaTituloService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PropostaTituloService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
