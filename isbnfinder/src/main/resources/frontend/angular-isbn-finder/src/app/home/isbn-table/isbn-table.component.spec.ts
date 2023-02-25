import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IsbnTableComponent } from './isbn-table.component';

describe('IsbnTableComponent', () => {
  let component: IsbnTableComponent;
  let fixture: ComponentFixture<IsbnTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IsbnTableComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IsbnTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
