import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArquivoListComponent } from './arquivo-list.component';

describe('ArquivoListComponent', () => {
  let component: ArquivoListComponent;
  let fixture: ComponentFixture<ArquivoListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ArquivoListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ArquivoListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
