import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateboardComponent } from './createboard.component';

describe('CreateboardComponent', () => {
  let component: CreateboardComponent;
  let fixture: ComponentFixture<CreateboardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CreateboardComponent]
    });
    fixture = TestBed.createComponent(CreateboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
