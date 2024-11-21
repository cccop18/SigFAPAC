import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuEditalComponent } from './menu-edital.component';

describe('MenuEditalComponent', () => {
  let component: MenuEditalComponent;
  let fixture: ComponentFixture<MenuEditalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MenuEditalComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MenuEditalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
