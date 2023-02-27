/**
 * Represents an ISBN number
 * 
 * @Author Alex Cohen
 */
export class Isbn {

    public isbn: string;
    public validity: boolean;

    /**
     * Creates a new ISBN number with the given isbn string and a validity for whether the ISBN is valid
     * 
     * @param isbn The ISBN number as a string
     * @param validity True if the ISBN is valid, false if not
     */
    constructor(isbn: string, validity: boolean) { 
        this.isbn = isbn;
        this.validity = validity;
    }

    /**
     * Gets the ISBN number
     * 
     * @returns The ISBN number as a string
     */
    public getIsbn(): string {
        return this.isbn;
    }

    /**
     * Sets the ISBN number
     * 
     * @param isbn The ISBN number to set 
     */
    public setIsbn(isbn: string) {
        this.isbn = isbn;
    }

    /**
     * Gets the validity
     * 
     * @returns True if the ISBN is valid, false if not
     */
    public getValidity(): boolean {
        return this.validity;
    }

    /**
     * Sets the validity
     * 
     * @param validity The validity to set
     */
    public setValidity(validity: boolean) {
        this.validity = validity;
    }

    public equals(other: any): boolean {
        if (other instanceof Isbn) {
            let otherIsbn: Isbn = <Isbn>other;
            if (otherIsbn.getIsbn() === this.getIsbn() &&
                otherIsbn.getValidity() === this.getValidity()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}