import { Isbn } from './isbn';
import { IsbnService } from './isbn.service';

import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpErrorResponse } from '@angular/common/http';

describe('IsbnService', () => {
    let httpTestingController: HttpTestingController;
    let isbnService: IsbnService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [HttpClientTestingModule],
            providers: [IsbnService],
            declarations: []
        });

        httpTestingController = TestBed.inject(HttpTestingController);
        isbnService = TestBed.inject(IsbnService);
    });

    afterEach(() => {
        httpTestingController.verify();
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

        for (let i: number = 0; i < validated.length; i++) {
            expect(isbns[i]).toEqual(validated[i]);
        }
    });

    it('should return the correct array of Isbn objects when validateIsbns is called', (done: DoneFn) => {
        const validated: Isbn[] = [
            new Isbn("0-06-097329-3", true),
            new Isbn("9781621291657", true),
            new Isbn("5", false),
            new Isbn("a", false),
            new Isbn("", false),
            new Isbn("hi", false),
            new Isbn("1-1-1", false)];

        isbnService.validateIsbns("0-06-097329-3,9781621291657,5,a,,hi,1-1-1").subscribe((result: Isbn[]) => {
            expect(result.length).toEqual(validated.length);
            expect(Array.isArray(result)).toBeTruthy();
            for (let i: number = 0; i < validated.length; i++) {
                expect(result[i].equals(validated[i])).toBeTruthy();
            }
            done();
        });
        
        const req = httpTestingController.expectOne('http://localhost:8080/api/validate');
        expect(req.request.method).toEqual('POST');
        req.flush(validated);
    });

    it('should handle network errors', () => {
        const csvIsbns = "0-06-097329-3,9781621291657,5,a,,hi,1-1-1";
        const errorMessage = 'There was a problem connecting to the server. Please try again later.';
    
        spyOn(console, 'error');

        isbnService.validateIsbns(csvIsbns).subscribe(
          () => fail('Expected an error, but got a successful response'),
          (error: HttpErrorResponse) => {
            expect(error.message).toEqual(errorMessage);
            expect(console.error).toHaveBeenCalledWith('Network error:', jasmine.any(String));
          }
        );
          
        const testRequest = httpTestingController.expectOne('http://localhost:8080/api/validate');
        testRequest.error(new ErrorEvent('Network error'));
    
      });
    
      it('should handle HTTP errors', () => {
        const csvIsbns = "0-06-097329-3,9781621291657,5,a,,hi,1-1-1";
        const errorMessage = 'There was a problem processing your request. Please try again later.';
    
        spyOn(console, 'error');

        isbnService.validateIsbns(csvIsbns).subscribe(
          () => fail('Expected an error, but got a successful response'),
          (error: HttpErrorResponse) => expect(error.message).toEqual(errorMessage)
        );
    
        const req = httpTestingController.expectOne('http://localhost:8080/api/validate');
        req.flush({}, { status: 404, statusText: 'Not Found' });
    
        expect(console.error).toHaveBeenCalledWith('HTTP error 404: Not Found');
      });
});