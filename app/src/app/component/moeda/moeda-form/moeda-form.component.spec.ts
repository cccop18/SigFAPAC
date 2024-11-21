import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MoedaFormComponent } from './moeda-form.component';

describe('MoedaFormComponent', () => {
  let component: MoedaFormComponent;
  let fixture: ComponentFixture<MoedaFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MoedaFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MoedaFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
