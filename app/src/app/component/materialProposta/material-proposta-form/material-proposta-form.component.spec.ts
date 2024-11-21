import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MaterialPropostaFormComponent } from './material-proposta-form.component';

describe('MaterialPropostaFormComponent', () => {
  let component: MaterialPropostaFormComponent;
  let fixture: ComponentFixture<MaterialPropostaFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MaterialPropostaFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MaterialPropostaFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
