import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AbrangenciaPropostaFormComponent } from './abrangencia-proposta-form.component';

describe('AbrangenciaPropostaFormComponent', () => {
  let component: AbrangenciaPropostaFormComponent;
  let fixture: ComponentFixture<AbrangenciaPropostaFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AbrangenciaPropostaFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AbrangenciaPropostaFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
