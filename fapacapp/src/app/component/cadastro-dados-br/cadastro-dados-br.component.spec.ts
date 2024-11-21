import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastroDadosBrComponent } from './cadastro-dados-br.component';

describe('CadastroDadosBrComponent', () => {
  let component: CadastroDadosBrComponent;
  let fixture: ComponentFixture<CadastroDadosBrComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CadastroDadosBrComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CadastroDadosBrComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
