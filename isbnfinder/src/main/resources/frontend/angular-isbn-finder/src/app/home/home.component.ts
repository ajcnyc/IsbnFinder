import { Component } from '@angular/core';
import { IsbnService } from './isbn.service';
import { Isbn } from './isbn';

/**
 * The main component of the application representing the home page of the app
 */
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  /**
   * The Isbn objects to display in the IsbnTableComponent
   */
  output: Isbn[] = [];

  /**
   * 
   * Creates a new HomeComponent with an IsbnService injected
   * 
   * @param isbnService The injected IsbnService
   */
  constructor(private isbnService: IsbnService) { }

  /**
   * Validates the input in the isbn-field by sendint to the backend, and adds the results to the output array
   */
  validate() {
    let requestCsv: string = (<HTMLInputElement>document.getElementById('isbn-field')).value;
    if (requestCsv.length > 0) {
      this.isbnService.validateIsbns(requestCsv).subscribe((result: Isbn[]) => {
        this.output = result;
      });
    }
  }
}
