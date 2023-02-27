import { Component, ChangeDetectionStrategy, Input } from '@angular/core';
import { Isbn } from '../isbn'
@Component({
  selector: 'app-isbn-table',
  templateUrl: './isbn-table.component.html',
  styleUrls: ['./isbn-table.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush

})
export class IsbnTableComponent {
  @Input() output: Isbn[];

  constructor() {
    this.output = [];
  }

}
