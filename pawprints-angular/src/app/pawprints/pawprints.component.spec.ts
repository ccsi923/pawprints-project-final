import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PawprintsComponent } from './pawprints.component';

describe('PawprintsComponent', () => {
  let component: PawprintsComponent;
  let fixture: ComponentFixture<PawprintsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PawprintsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PawprintsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
