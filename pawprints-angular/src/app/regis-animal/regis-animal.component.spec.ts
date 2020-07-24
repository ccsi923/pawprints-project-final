import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisAnimalComponent } from './regis-animal.component';

describe('RegisAnimalComponent', () => {
  let component: RegisAnimalComponent;
  let fixture: ComponentFixture<RegisAnimalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisAnimalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisAnimalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
