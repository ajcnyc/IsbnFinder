import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

/**
 * Service to validate ISBNs by sending them to the server for validation
 * 
 * @Author Alex Cohen
 */
@Injectable({
  providedIn: 'root'
})
export class IsbnService {

  /**
   * Creates a new IsbnService an HttpClient injected
   * 
   * @param http The injected HttpClient
   */
  constructor(private http: HttpClient) { }

  /**
   * Validates the given ISBNs
   * 
   * @param csvIsbns A csv string of ISBNs to validate
   * 
   * @return An array of validated Isbn objects 
   */
  public validateIsbns(csvIsbns: string): any {
    // POST and return the response from the server
    return this.http.post('http://localhost:8080/api/validate', csvIsbns).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.error instanceof ErrorEvent) { // If network error
          console.error('Network error:', error.error.message);
          return throwError(() => new Error('There was a problem connecting to the server. Please try again later.'));
        } else { // If http error
          console.error(`HTTP error ${error.status}: ${error.statusText}`);
          return throwError(() => new Error('There was a problem processing your request. Please try again later.'));
        }
      }));
  }
}