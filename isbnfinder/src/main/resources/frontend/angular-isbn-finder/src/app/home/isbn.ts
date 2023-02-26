/**
 * Represents an ISBN number
 * 
 * @Author Alex Cohen
 */
export class Isbn {

    /**
     * Creates a new ISBN number with the given isbn string and a validity for whether the ISBN is valid
     * 
     * @param isbn The ISBN number as a string
     * @param validity True if the ISBN is valid, false if not
     */
    constructor(private isbn: string, private validity: boolean) { }

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
}