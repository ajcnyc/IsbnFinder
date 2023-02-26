import { Isbn } from "./isbn";

/**
 * Service to validate ISBNs by sending them to the server for validation
 * 
 * @Author Alex Cohen
 */
export class IsbnService {

    /**
     * Validates the given ISBNs
     * 
     * @param csvIsbns A csv string of ISBNs to validate
     * 
     * @return An array of validated Isbn objects 
     */
    public validateIsbns(csvIsbns: string): Isbn[] {
        // TODO: Implement
        let isbns: Isbn[] = [];
        return isbns;
    }
}