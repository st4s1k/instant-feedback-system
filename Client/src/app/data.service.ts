import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private http: HttpClient) { }

  protocol  = 'http';
  ipAdress  = 'localhost';
  port      = '8080';

  baseUrl   = `${this.protocol}://${this.ipAdress}:${this.port}`;

  saveBtnClicked() {
    // send data to server
    return this.http.post(`${this.baseUrl}/presentation`, this.protocol);
  }

  discardBtnClicked() {
    // Clean fields
    // go back
    return this.http.delete(`${this.baseUrl}/presentation`);
  }

  getUsers() {
  }
}
