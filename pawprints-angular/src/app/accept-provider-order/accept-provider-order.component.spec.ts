import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AcceptProviderOrderComponent } from './accept-provider-order.component';

describe('AcceptProviderOrderComponent', () => {
  let component: AcceptProviderOrderComponent;
  let fixture: ComponentFixture<AcceptProviderOrderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AcceptProviderOrderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AcceptProviderOrderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
