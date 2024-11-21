import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BolsaPropostaFormComponent } from './bolsa-proposta-form.component';

describe('BolsaPropostaFormComponent', () => {
  let component: BolsaPropostaFormComponent;
  let fixture: ComponentFixture<BolsaPropostaFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BolsaPropostaFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BolsaPropostaFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
