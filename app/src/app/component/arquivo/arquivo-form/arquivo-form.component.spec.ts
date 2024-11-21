import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArquivoFormComponent } from './arquivo-form.component';

describe('ArquivoFormComponent', () => {
  let component: ArquivoFormComponent;
  let fixture: ComponentFixture<ArquivoFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ArquivoFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ArquivoFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
