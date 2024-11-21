import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtividadePropostaFormComponent } from './atividade-proposta-form.component';

describe('AtividadePropostaFormComponent', () => {
  let component: AtividadePropostaFormComponent;
  let fixture: ComponentFixture<AtividadePropostaFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AtividadePropostaFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AtividadePropostaFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
