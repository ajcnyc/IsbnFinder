import { Component, ChangeDetectionStrategy, Input } from '@angular/core';
import { Isbn } from '../isbn'

/**
 * Component that represents a table of ISBNs.
 */
@Component({
  selector: 'app-isbn-table',
  templateUrl: './isbn-table.component.html',
  styleUrls: ['./isbn-table.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush

})
export class IsbnTableComponent {

  /**
   * The ISBN objects to display
   */
  @Input() output: Isbn[];

  /**
   * Creates a new IsbnTableComponent
   */
  constructor() {
    this.output = [];
  }

}
