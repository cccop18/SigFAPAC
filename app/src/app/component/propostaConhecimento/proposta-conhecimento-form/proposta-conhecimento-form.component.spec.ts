import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PropostaConhecimentoFormComponent } from './proposta-conhecimento-form.component';

describe('PropostaConhecimentoFormComponent', () => {
  let component: PropostaConhecimentoFormComponent;
  let fixture: ComponentFixture<PropostaConhecimentoFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PropostaConhecimentoFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PropostaConhecimentoFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
