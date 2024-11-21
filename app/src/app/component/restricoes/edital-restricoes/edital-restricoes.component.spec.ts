import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditalRestricoesComponent } from './edital-restricoes.component';

describe('EditalRestricoesComponent', () => {
  let component: EditalRestricoesComponent;
  let fixture: ComponentFixture<EditalRestricoesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditalRestricoesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EditalRestricoesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
