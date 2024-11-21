import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DiariaPropostaFormComponent } from './diaria-proposta-form.component';

describe('DiariaPropostaFormComponent', () => {
  let component: DiariaPropostaFormComponent;
  let fixture: ComponentFixture<DiariaPropostaFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DiariaPropostaFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DiariaPropostaFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
