import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastroEnderecoPesquisadoresComponent } from './cadastro-endereco-pesquisadores.component';

describe('CadastroEnderecoPesquisadoresComponent', () => {
  let component: CadastroEnderecoPesquisadoresComponent;
  let fixture: ComponentFixture<CadastroEnderecoPesquisadoresComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CadastroEnderecoPesquisadoresComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CadastroEnderecoPesquisadoresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
