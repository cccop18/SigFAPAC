import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditalModeloFormComponent } from './edital-modelo-form.component';

describe('EditalModeloFormComponent', () => {
  let component: EditalModeloFormComponent;
  let fixture: ComponentFixture<EditalModeloFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditalModeloFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EditalModeloFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
