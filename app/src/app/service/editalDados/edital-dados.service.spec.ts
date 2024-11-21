import { TestBed } from '@angular/core/testing';

import { EditalDadosService } from './edital-dados.service';

describe('EditalDadosService', () => {
  let service: EditalDadosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EditalDadosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
