import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecommendFormComponent } from './recommend-form.component';

describe('RecommendFormComponent', () => {
  let component: RecommendFormComponent;
  let fixture: ComponentFixture<RecommendFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecommendFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RecommendFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
