/**
 * Represents an ISBN number
 * 
 * @Author Alex Cohen
 */
export class Isbn {

    /**
     * The string representation of the ISBN number
     */
    public isbn: string;

    /**
     * Whether the ISBN number is valid
     */
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

    /**
     * Checks if the other object passed in equals this ISBN object 
     * 
     * @param other The other object
     * @returns True if both objects are Isbn objects with the same isbn and validity. False otherwise.
     */
    public equals(other: any): boolean {
        // If other is an Isbn
        if (other instanceof Isbn) {
            let otherIsbn: Isbn = <Isbn>other; // Cast to Isbn

            // If it has the same isbn and validity, it's equal
            if (otherIsbn.getIsbn() === this.getIsbn() &&
                otherIsbn.getValidity() === this.getValidity()) {
                return true;
            } else { // If not, it isn't equal
                return false;
            }
        } else { // If other isn't an Isbn, it doesn't equal this Isbn
            return false;
        }
    }

}