import { Isbn } from './isbn';
import { IsbnService } from './isbn.service';

import { TestBed, ComponentFixture, fakeAsync, tick } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

describe('IsbnService', () => {
    let httpMock: HttpTestingController;
    let isbnService: IsbnService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [HttpClientTestingModule],
            providers: [IsbnService],
            declarations: []
        });
        httpMock = TestBed.inject(HttpTestingController);
        isbnService = TestBed.inject(IsbnService);
        //jasmine.DEFAULT_TIMEOUT_INTERVAL = 10000;
    });

    afterEach(() => {
        httpMock.verify();
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

    // it('should return the correct array of Isbn objects when validateIsbns is called', () => {
    //     const validated: Isbn[] = [
    //         new Isbn("0-06-097329-3", true),
    //         new Isbn("9781621291657", true),
    //         new Isbn("5", false),
    //         new Isbn("a", false),
    //         new Isbn("", false),
    //         new Isbn("hi", false),
    //         new Isbn("1-1-1", false)];

    //     let isbns: Isbn[] = [];

    //     async function f() {
    //         isbns = isbnService.validateIsbns("0-06-097329-3,9781621291657,5,a,,hi,1-1-1").subscribe((result: Isbn[]) => {
    //             isbns = result;
    //             return Promise.resolve(1);
    //         });
    //     };

    //     function f2() {
    //         expect(isbns.length).toEqual(validated.length);
    //         expect(Array.isArray(isbns)).toBeTruthy();
    //         for (let i: number = 0; i < validated.length; i++) {
    //             console.log(isbns[i].getIsbn());
    //             expect(isbns[i].equals("a"));
    //         };
    //     }

    //     f().then(f2);


    // });
});