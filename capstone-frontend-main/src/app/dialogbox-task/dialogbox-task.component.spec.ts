import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogboxTaskComponent } from './dialogbox-task.component';

describe('DialogboxTaskComponent', () => {
  let component: DialogboxTaskComponent;
  let fixture: ComponentFixture<DialogboxTaskComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DialogboxTaskComponent]
    });
    fixture = TestBed.createComponent(DialogboxTaskComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
