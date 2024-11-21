import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EncargoPropostaFormComponent } from './encargo-proposta.component';

describe('EncargoPropostaComponent', () => {
  let component: EncargoPropostaFormComponent;
  let fixture: ComponentFixture<EncargoPropostaFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EncargoPropostaFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EncargoPropostaFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
