import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FaixafinanciamentoFormComponent } from './faixafinanciamento-form.component';

describe('FaixafinanciamentoFormComponent', () => {
  let component: FaixafinanciamentoFormComponent;
  let fixture: ComponentFixture<FaixafinanciamentoFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FaixafinanciamentoFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FaixafinanciamentoFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
