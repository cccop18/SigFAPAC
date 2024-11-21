import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastroAreaconhecimentoPesquisadorComponent } from './cadastro-areaconhecimento-pesquisador.component';

describe('CadastroAreaconhecimentoPesquisadorComponent', () => {
  let component: CadastroAreaconhecimentoPesquisadorComponent;
  let fixture: ComponentFixture<CadastroAreaconhecimentoPesquisadorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CadastroAreaconhecimentoPesquisadorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CadastroAreaconhecimentoPesquisadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
