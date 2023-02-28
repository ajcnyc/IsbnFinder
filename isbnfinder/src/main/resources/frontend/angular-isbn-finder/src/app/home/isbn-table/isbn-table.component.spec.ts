import { Isbn } from '../isbn';
import { IsbnService } from '../isbn.service';

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';


import { IsbnTableComponent } from './isbn-table.component';

describe('IsbnTableComponent', () => {
  let component: IsbnTableComponent;
  let fixture: ComponentFixture<IsbnTableComponent>;

  let httpTestingController: HttpTestingController;
  let isbnService: IsbnService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [IsbnService],
      declarations: [IsbnTableComponent]
    }).compileComponents();

    fixture = TestBed.createComponent(IsbnTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();

    httpTestingController = TestBed.inject(HttpTestingController);
    isbnService = TestBed.inject(IsbnService);
  });

  afterEach(() => {
    httpTestingController.verify();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should mock return the correct array of Isbn objects when validateIsbns is called', () => {
    let validated: Isbn[] = [
      new Isbn("0-06-097329-3", true),
      new Isbn("9781621291657", true),
      new Isbn("5", false),
      new Isbn("a", false),
      new Isbn("", false),
      new Isbn("hi", false),
      new Isbn("1-1-1", false)];

    const validateSpy = spyOn(isbnService, 'validateIsbns').and.returnValue(validated);

    let isbns: Isbn[] = isbnService.validateIsbns("0-06-097329-3,9781621291657,5,a,,hi,1-1-1");

    expect(validateSpy).toHaveBeenCalled();

    populateTable(isbns);
    validateTable(isbns);
  });

  function validateTable(isbns: Isbn[]) {
    const rows = fixture.nativeElement.querySelectorAll('tr'); // select all rows in the table
    expect(rows.length).toBe(8); // 1 Extra row for headers
    for(let i:number =1; i<rows.length; i++){
      const row = rows[i];
      const cells = row.querySelectorAll('td'); // select all cells in the row
      expect(cells.length).toBe(2);
      expect(cells[0].textContent.trim()).toEqual(isbns[i-1].getIsbn());
      expect(cells[1].textContent.trim()).toEqual(""+isbns[i-1].getValidity());
    }
  }

  function populateTable(isbns: Isbn[]): void {
    let tableString = buildTableString(isbns);

    let tbody = fixture.debugElement.nativeElement.querySelector('tbody');
    tbody.innerHTML = tableString;
  }

  function buildTableString(isbns: Isbn[]) {
    let tableString: string = "";

    for(let i:number =0; i<isbns.length; i++){
      tableString += '<tr><td>'+isbns[i].getIsbn()+'</td><td>'+isbns[i].getValidity()+'</td></tr>';
    }

    return tableString;
  }

});
