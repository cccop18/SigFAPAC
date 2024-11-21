import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HospedagemAlimentacaoPropostaComponent } from './hospedagem-alimentacao-proposta.component';

describe('HospedagemAlimentacaoPropostaComponent', () => {
  let component: HospedagemAlimentacaoPropostaComponent;
  let fixture: ComponentFixture<HospedagemAlimentacaoPropostaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HospedagemAlimentacaoPropostaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HospedagemAlimentacaoPropostaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
