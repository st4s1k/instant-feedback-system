import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: [
    './app.component.scss',
    '../../node_modules/angular-notifier/styles.scss']
})
export class AppComponent {
  title = 'Client';
  email = new FormControl('');

  constructor() { }
}
