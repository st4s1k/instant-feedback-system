import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPresComponent } from './edit-presentation.component';

describe('AddPresComponent', () => {
  let component: AddPresComponent;
  let fixture: ComponentFixture<AddPresComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddPresComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddPresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
