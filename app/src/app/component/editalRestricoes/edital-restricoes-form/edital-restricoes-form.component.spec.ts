import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditalRestricoesFormComponent } from './edital-restricoes-form.component';

describe('EditalRestricoesFormComponent', () => {
  let component: EditalRestricoesFormComponent;
  let fixture: ComponentFixture<EditalRestricoesFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditalRestricoesFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EditalRestricoesFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
