import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PassagemPropostaFormComponent } from './passagem-proposta-form.component';

describe('PassagemPropostaFormComponent', () => {
  let component: PassagemPropostaFormComponent;
  let fixture: ComponentFixture<PassagemPropostaFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PassagemPropostaFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PassagemPropostaFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
