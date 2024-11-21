import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditalInfoListComponent } from './edital-info-list.component';

describe('EditalInfoListComponent', () => {
  let component: EditalInfoListComponent;
  let fixture: ComponentFixture<EditalInfoListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditalInfoListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EditalInfoListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
