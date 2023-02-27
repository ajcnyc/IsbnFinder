import { Component } from '@angular/core';
import { IsbnService } from './isbn.service';
import { Isbn } from './isbn';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  output: Isbn[] = [];

  constructor(private isbnService: IsbnService) { }

  validate() {
    let requestCsv: string = (<HTMLInputElement>document.getElementById('isbn-field')).value;
    if (requestCsv.length > 0) {
      this.isbnService.validateIsbns(requestCsv).subscribe((result: Isbn[]) => {
        this.output = result;
      });
    }
  }
}
