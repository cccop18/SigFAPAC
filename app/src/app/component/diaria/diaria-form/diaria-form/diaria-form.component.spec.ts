import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DiariaFormComponent } from './diaria-form.component';

describe('DiariaFormComponent', () => {
  let component: DiariaFormComponent;
  let fixture: ComponentFixture<DiariaFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DiariaFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DiariaFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
