import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogboxBoardComponent } from './dialogbox-board.component';

describe('DialogboxBoardComponent', () => {
  let component: DialogboxBoardComponent;
  let fixture: ComponentFixture<DialogboxBoardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DialogboxBoardComponent]
    });
    fixture = TestBed.createComponent(DialogboxBoardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
