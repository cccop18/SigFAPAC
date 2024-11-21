import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PesquisadorBrFormComponent } from './pesquisador-br-form.component';

describe('PesquisadorBrFormComponent', () => {
  let component: PesquisadorBrFormComponent;
  let fixture: ComponentFixture<PesquisadorBrFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PesquisadorBrFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PesquisadorBrFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
