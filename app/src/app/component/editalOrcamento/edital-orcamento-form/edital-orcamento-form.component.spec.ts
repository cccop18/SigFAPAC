import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditalOrcamentoFormComponent } from './edital-orcamento-form.component';

describe('EditalOrcamentoFormComponent', () => {
  let component: EditalOrcamentoFormComponent;
  let fixture: ComponentFixture<EditalOrcamentoFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditalOrcamentoFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EditalOrcamentoFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
