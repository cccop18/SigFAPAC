import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PessoalPropostaFormComponent } from './pessoal-proposta-form.component';

describe('PessoalPropostaFormComponent', () => {
  let component: PessoalPropostaFormComponent;
  let fixture: ComponentFixture<PessoalPropostaFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PessoalPropostaFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PessoalPropostaFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
