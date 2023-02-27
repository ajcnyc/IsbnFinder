import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import {HttpClientModule} from '@angular/common/http';

import { HomeComponent } from './home.component';
import { IsbnService } from './isbn.service';

describe('HomeComponent', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [IsbnService],
      declarations: [HomeComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(HomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  // it('should display the JSON response on validate button click', () => {
  //   fixture.debugElement.nativeElement.querySelector('#isbn-field').value = '0-06-097329-3,9781621291657,5,a,,hi,1-1-1';

  //   let button = fixture.debugElement.nativeElement.querySelector('#validate-btn');
  //   button.click();

  //   let testP = fixture.debugElement.nativeElement.querySelector('#test');

  //   let correctResult = [{ "isbn": "0-06-097329-3", "validity": true }, 
  //   { "isbn": "9781621291657", "validity": true }, 
  //   { "isbn": "5", "validity": false }, 
  //   { "isbn": "a", "validity": false }, 
  //   { "isbn": "", "validity": false }, 
  //   { "isbn": "hi", "validity": false }, 
  //   { "isbn": "1-1-1", "validity": false }];

  //   let correctResultStr = JSON.stringify(correctResult);

  //   fixture.whenStable().then(() => {
  //     expect(testP.innerHtml).toEqual(correctResultStr);
  //   });
  // });

  // it('should', () => {

  // });
});
