import { Isbn } from './isbn';

describe('Isbn', () => {
    let isbn: Isbn;
    let isbn2: Isbn;

    it('should construct', () => {
        isbn = new Isbn("test",false);
        expect(isbn.getIsbn()).toEqual("test");
        expect(isbn.getValidity()).toEqual(false);
    });

    it('should get', () => {
        isbn = new Isbn("test",false);
        expect(isbn.getIsbn()).toEqual("test");
        expect(isbn.getValidity()).toEqual(false);
    });

    it('should set', () => {
        isbn = new Isbn("test",false);
        isbn.setIsbn("test2");
        expect(isbn.getIsbn()).toEqual("test2");
        isbn.setValidity(true);
        expect(isbn.getValidity()).toEqual(true);
    });

    it('should equal only when equals', () => {
        isbn = new Isbn("test",false);

        isbn2 = new Isbn("test2",false);
        expect(isbn.equals(isbn2)).toEqual(false);
        isbn2 = new Isbn("test",true);
        expect(isbn.equals(isbn2)).toEqual(false);
        isbn2 = new Isbn("test2",true);
        expect(isbn.equals(isbn2)).toEqual(false);
        isbn2 = new Isbn("test",false);
        expect(isbn.equals(isbn2)).toEqual(true);

        let strIsbn: string = "test";
        expect(isbn.equals(strIsbn)).toEqual(false);

    });
});
