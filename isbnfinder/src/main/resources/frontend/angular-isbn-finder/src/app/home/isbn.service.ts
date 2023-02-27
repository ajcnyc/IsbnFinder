import { Isbn } from "./isbn";
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

/**
 * Service to validate ISBNs by sending them to the server for validation
 * 
 * @Author Alex Cohen
 */
@Injectable({
    providedIn: 'root'
})
export class IsbnService {

    constructor(private http: HttpClient) { }

    /**
     * Validates the given ISBNs
     * 
     * @param csvIsbns A csv string of ISBNs to validate
     * 
     * @return An array of validated Isbn objects 
     */
    public validateIsbns(csvIsbns: string): any {
        return this.http.post('http://localhost:8080/api/validate', csvIsbns);
    }
}