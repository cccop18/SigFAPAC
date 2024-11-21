import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PesquisadorEsFormComponent } from './pesquisador-es-form.component';

describe('PesquisadorEsFormComponent', () => {
  let component: PesquisadorEsFormComponent;
  let fixture: ComponentFixture<PesquisadorEsFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PesquisadorEsFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PesquisadorEsFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
