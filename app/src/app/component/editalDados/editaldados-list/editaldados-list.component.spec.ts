import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditaldadosListComponent } from './editaldados-list.component';

describe('EditaldadosListComponent', () => {
  let component: EditaldadosListComponent;
  let fixture: ComponentFixture<EditaldadosListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditaldadosListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EditaldadosListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
