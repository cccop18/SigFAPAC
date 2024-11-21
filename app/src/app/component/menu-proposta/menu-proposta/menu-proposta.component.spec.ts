import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuPropostaComponent } from './menu-proposta.component';

describe('MenuPropostaComponent', () => {
  let component: MenuPropostaComponent;
  let fixture: ComponentFixture<MenuPropostaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MenuPropostaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MenuPropostaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
