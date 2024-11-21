import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MoedaListComponent } from './moeda-list.component';

describe('MoedaListComponent', () => {
  let component: MoedaListComponent;
  let fixture: ComponentFixture<MoedaListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MoedaListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MoedaListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
