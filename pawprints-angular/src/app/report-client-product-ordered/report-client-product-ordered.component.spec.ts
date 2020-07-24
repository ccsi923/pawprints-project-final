import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportClientProductOrderedComponent } from './report-client-product-ordered.component';

describe('ReportClientProductOrderedComponent', () => {
  let component: ReportClientProductOrderedComponent;
  let fixture: ComponentFixture<ReportClientProductOrderedComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReportClientProductOrderedComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReportClientProductOrderedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
