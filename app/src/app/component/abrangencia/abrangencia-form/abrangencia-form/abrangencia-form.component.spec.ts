import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AbrangenciaFormComponent } from './abrangencia-form.component';

describe('AbrangenciaFormComponent', () => {
  let component: AbrangenciaFormComponent;
  let fixture: ComponentFixture<AbrangenciaFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AbrangenciaFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AbrangenciaFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
