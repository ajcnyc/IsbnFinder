import { Isbn } from "./isbn";
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

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
        return this.http.post('http://localhost:8080/api/validate', csvIsbns).pipe(
            catchError((error: HttpErrorResponse) => {
              if (error.error instanceof ErrorEvent) {
                console.error('Network error:', error.error.message);
                return throwError(() => new Error('There was a problem connecting to the server. Please try again later.'));
              } else {
                console.error(`HTTP error ${error.status}: ${error.statusText}`);
                return throwError(() => new Error('There was a problem processing your request. Please try again later.'));
              }
            }));
    }
}