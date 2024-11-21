import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PesquisadorMainComponent } from './pesquisador-main.component';

describe('PesquisadorMainComponent', () => {
  let component: PesquisadorMainComponent;
  let fixture: ComponentFixture<PesquisadorMainComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PesquisadorMainComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PesquisadorMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
