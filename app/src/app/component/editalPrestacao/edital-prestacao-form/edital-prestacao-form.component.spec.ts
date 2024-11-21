import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditalPrestacaoFormComponent } from './edital-prestacao-form.component';

describe('EditalPrestacaoFormComponent', () => {
  let component: EditalPrestacaoFormComponent;
  let fixture: ComponentFixture<EditalPrestacaoFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditalPrestacaoFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EditalPrestacaoFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
