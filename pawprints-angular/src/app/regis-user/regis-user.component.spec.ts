import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisUserComponent } from './regis-user.component';

describe('RegisUserComponent', () => {
  let component: RegisUserComponent;
  let fixture: ComponentFixture<RegisUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisUserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
