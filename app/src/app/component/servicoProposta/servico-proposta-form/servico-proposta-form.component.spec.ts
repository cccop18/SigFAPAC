import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ServicoPropostaFormComponent } from './servico-proposta-form.component';

describe('ServicoPropostaFormComponent', () => {
  let component: ServicoPropostaFormComponent;
  let fixture: ComponentFixture<ServicoPropostaFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ServicoPropostaFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ServicoPropostaFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
