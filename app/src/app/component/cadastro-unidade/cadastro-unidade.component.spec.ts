import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastroUnidadeComponent } from './cadastro-unidade.component';

describe('CadastroUnidadeComponent', () => {
  let component: CadastroUnidadeComponent;
  let fixture: ComponentFixture<CadastroUnidadeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CadastroUnidadeComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CadastroUnidadeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
