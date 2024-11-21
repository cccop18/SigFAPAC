import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastroDadosProfissionaisComponent } from './cadastro-dados-profissionais.component';

describe('CadastroDadosProfissionaisComponent', () => {
  let component: CadastroDadosProfissionaisComponent;
  let fixture: ComponentFixture<CadastroDadosProfissionaisComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CadastroDadosProfissionaisComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CadastroDadosProfissionaisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
