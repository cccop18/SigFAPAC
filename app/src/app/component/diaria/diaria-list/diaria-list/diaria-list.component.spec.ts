import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DiariaListComponent } from './diaria-list.component';

describe('DiariaListComponent', () => {
  let component: DiariaListComponent;
  let fixture: ComponentFixture<DiariaListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DiariaListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DiariaListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
