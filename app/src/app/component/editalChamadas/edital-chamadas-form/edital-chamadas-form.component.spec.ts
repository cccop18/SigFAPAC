import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditalChamadasFormComponent } from './edital-chamadas-form.component';

describe('EditalChamadasFormComponent', () => {
  let component: EditalChamadasFormComponent;
  let fixture: ComponentFixture<EditalChamadasFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditalChamadasFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EditalChamadasFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
