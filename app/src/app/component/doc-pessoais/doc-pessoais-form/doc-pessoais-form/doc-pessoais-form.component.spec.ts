import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DocPessoaisFormComponent } from './doc-pessoais-form.component';

describe('DocPessoaisFormComponent', () => {
  let component: DocPessoaisFormComponent;
  let fixture: ComponentFixture<DocPessoaisFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DocPessoaisFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DocPessoaisFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
