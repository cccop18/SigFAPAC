import { TestBed } from '@angular/core/testing';

import { EditalRestricoesService } from './edital-restricoes.service';

describe('EditalRestricoesService', () => {
  let service: EditalRestricoesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EditalRestricoesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
