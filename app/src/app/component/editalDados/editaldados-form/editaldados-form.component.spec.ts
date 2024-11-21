import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditaldadosFormComponent } from './editaldados-form.component';

describe('EditaldadosFormComponent', () => {
  let component: EditaldadosFormComponent;
  let fixture: ComponentFixture<EditaldadosFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditaldadosFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EditaldadosFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
