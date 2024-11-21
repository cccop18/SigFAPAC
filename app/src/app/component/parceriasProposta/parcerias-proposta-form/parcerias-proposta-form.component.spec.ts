import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParceriasPropostaFormComponent } from './parcerias-proposta-form.component';

describe('ParceriasPropostaFormComponent', () => {
  let component: ParceriasPropostaFormComponent;
  let fixture: ComponentFixture<ParceriasPropostaFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ParceriasPropostaFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ParceriasPropostaFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
