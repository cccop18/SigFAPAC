import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnderecoUnidadeFormComponent } from './endereco-unidade-form.component';

describe('EnderecoUnidadeFormComponent', () => {
  let component: EnderecoUnidadeFormComponent;
  let fixture: ComponentFixture<EnderecoUnidadeFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EnderecoUnidadeFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EnderecoUnidadeFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
