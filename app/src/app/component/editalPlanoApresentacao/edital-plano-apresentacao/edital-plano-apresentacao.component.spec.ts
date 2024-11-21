import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditalPlanoApresentacaoFormComponent } from './edital-plano-apresentacao.component';

describe('EditalPlanoApresentacaoComponent', () => {
  let component: EditalPlanoApresentacaoFormComponent;
  let fixture: ComponentFixture<EditalPlanoApresentacaoFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditalPlanoApresentacaoFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EditalPlanoApresentacaoFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
