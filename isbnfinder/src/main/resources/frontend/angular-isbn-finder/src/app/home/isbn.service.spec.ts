import { IsbnService } from './isbn.service';
import { Isbn } from './isbn';

describe('IsbnService', () => {
    let isbnService: IsbnService;

    beforeEach(() => {
        isbnService = new IsbnService();
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
    
    it('should return the correct array of Isbn objects when validateIsbns is called', () => {
        let validated: Isbn[] = [
            new Isbn("0-06-097329-3", true),
            new Isbn("9781621291657", true),
            new Isbn("5", false),
            new Isbn("a", false),
            new Isbn("", false),
            new Isbn("hi", false),
            new Isbn("1-1-1", false)];

        let isbns: Isbn[] = isbnService.validateIsbns("0-06-097329-3,9781621291657,5,a,,hi,1-1-1");

        for (let i: number = 0; i < validated.length; i++) {
            expect(isbns[i]).toEqual(validated[i]);
        }
    });
});