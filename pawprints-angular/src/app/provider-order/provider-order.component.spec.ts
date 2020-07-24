import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProviderOrderComponent } from './provider-order.component';

describe('ProviderOrderComponent', () => {
  let component: ProviderOrderComponent;
  let fixture: ComponentFixture<ProviderOrderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProviderOrderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProviderOrderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
