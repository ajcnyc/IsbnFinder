import { Isbn } from './isbn';
import { IsbnService } from './isbn.service';

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IsbnTableComponent } from './isbn-table/isbn-table.component';
import { HomeComponent } from './home.component';


describe('HomeComponent', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [IsbnService],
      declarations: [HomeComponent, IsbnTableComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(HomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('Integration Test: It should populate the table on validate button click', (done: DoneFn) => {

    let csvInput = fixture.nativeElement.querySelector('#isbn-field');
    csvInput.value = "0-06-097329-3,9781621291657,5,a,,hi,1-1-1";

    let btn = fixture.nativeElement.querySelector('#validate-btn');
    btn.click();

    let correct: Isbn[] = [
      new Isbn("0-06-097329-3", true),
      new Isbn("9781621291657", true),
      new Isbn("5", false),
      new Isbn("a", false),
      new Isbn("", false),
      new Isbn("hi", false),
      new Isbn("1-1-1", false)];

      const httpTestingController = TestBed.inject(HttpTestingController);
      const req = httpTestingController.expectOne('http://localhost:8080/api/validate');
      expect(req.request.method).toEqual('POST');
      req.flush(correct);
    
      fixture.detectChanges();
      validateTable(correct);
      done();
  });

  function validateTable(correct: Isbn[]) {
    const rows = fixture.nativeElement.querySelectorAll('tr'); // select all rows in the table
    expect(rows.length).toBe(8); // 1 Extra row for headers
    for (let i: number = 1; i < rows.length; i++) {
      const row = rows[i];
      const cells = row.querySelectorAll('td'); // select all cells in the row
      expect(cells.length).toBe(2);
      expect(cells[0].textContent.trim()).toEqual(correct[i - 1].getIsbn());
      expect(cells[1].textContent.trim()).toEqual("" + correct[i - 1].getValidity());
    }
  }

});
