import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor() { }

  saveBtnClicked() {
    console.log('Save button clicked!');
  }

  discardBtnClicked() {
    console.log('Discard button clicked!');
  }
}
