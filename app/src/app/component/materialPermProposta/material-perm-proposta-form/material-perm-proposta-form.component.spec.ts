import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MaterialPermPropostaFormComponent } from './material-perm-proposta-form.component';

describe('MaterialPermPropostaFormComponent', () => {
  let component: MaterialPermPropostaFormComponent;
  let fixture: ComponentFixture<MaterialPermPropostaFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MaterialPermPropostaFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MaterialPermPropostaFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
