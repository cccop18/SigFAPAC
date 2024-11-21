import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastroSenhaPesquisadorComponent } from './cadastro-senha-pesquisador.component';

describe('CadastroSenhaPesquisadorComponent', () => {
  let component: CadastroSenhaPesquisadorComponent;
  let fixture: ComponentFixture<CadastroSenhaPesquisadorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CadastroSenhaPesquisadorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CadastroSenhaPesquisadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
