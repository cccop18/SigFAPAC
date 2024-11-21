import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RetificacaoFormComponent } from './retificacao-form.component';

describe('RetificacaoFormComponent', () => {
  let component: RetificacaoFormComponent;
  let fixture: ComponentFixture<RetificacaoFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RetificacaoFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RetificacaoFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
