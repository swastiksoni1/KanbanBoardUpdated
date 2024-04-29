import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogboxTeamComponent } from './dialogbox-team.component';

describe('DialogboxTeamComponent', () => {
  let component: DialogboxTeamComponent;
  let fixture: ComponentFixture<DialogboxTeamComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DialogboxTeamComponent]
    });
    fixture = TestBed.createComponent(DialogboxTeamComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
