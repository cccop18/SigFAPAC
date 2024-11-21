import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastroDadosEsComponent } from './cadastro-dados-es.component';

describe('CadastroDadosEsComponent', () => {
  let component: CadastroDadosEsComponent;
  let fixture: ComponentFixture<CadastroDadosEsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CadastroDadosEsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CadastroDadosEsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
