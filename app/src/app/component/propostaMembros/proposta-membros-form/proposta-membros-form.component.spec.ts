import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PropostaMembrosFormComponent } from './proposta-membros-form.component';

describe('PropostaMembrosFormComponent', () => {
  let component: PropostaMembrosFormComponent;
  let fixture: ComponentFixture<PropostaMembrosFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PropostaMembrosFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PropostaMembrosFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
