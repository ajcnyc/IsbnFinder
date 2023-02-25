
export class Isbn {

    constructor(private isbn: string, private validity: boolean) {}

    public getIsbn(): string {
        return this.isbn;
    }
    
    public setIsbn(isbn: string) {
        this.isbn = isbn;
    }

    public getValidity(): boolean {
        return this.validity;
    }

    public setValidity(validity: boolean) {
        this.validity = validity;
    }
}