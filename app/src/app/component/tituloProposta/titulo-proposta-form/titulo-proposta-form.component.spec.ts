import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TituloPropostaFormComponent } from './titulo-proposta-form.component';

describe('TituloPropostaFormComponent', () => {
  let component: TituloPropostaFormComponent;
  let fixture: ComponentFixture<TituloPropostaFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TituloPropostaFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TituloPropostaFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
