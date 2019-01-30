import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment.prod';
import { Observable } from 'rxjs';
import { UserDTO } from '../dto/user.dto';


const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE',
    'Access-Control-Allow-Headers': 'Content-Type'
  })
};
const API_URL = environment.apiUrl;
@Injectable({
  providedIn: 'root'
})

export class AuthenticationService {
  constructor(private http: HttpClient) { }
  public authSuccess = false;

  // RealBack

  login(email: string, password: string) {
    // return this.http.post<any>(`${API_URL}/users/authenticate`, { email: email, password: password }, httpOptions)

    //   // const fd = new FormData();

    //   // fd.append('email', email);
    //   // fd.append('password', password);

    // working part
    return this.http.post<any>(`${API_URL}/userLogin`, JSON.stringify({ email: email, password: password }), httpOptions)
      .pipe(map(user => {
        // login successful if there's a user in the response
        if (user) {
          // store user details and basic auth credentials in local storage
          // to keep user logged in between page refreshes
          user.authdata = window.btoa(email + ':' + password);
          alert('Success!');
          this.authSuccess = true;
          alert(JSON.stringify(user));
          localStorage.setItem('currentUser', JSON.stringify(user));
        }

        return user;
      }));
  }

  // Fake backend
  // login(email: string, password: string) {
  //   return this.http.post<any>(`${API_URL}/users/authenticate`, { email, password })
  //       .pipe(map(user => {
  //           // login successful if there's a user in the response
  //           if (user) {
  //               // store user details and basic auth credentials in local storage
  //               // to keep user logged in between page refreshes
  //               user.authdata = window.btoa(email + ':' + password);
  //               localStorage.setItem('currentUser', JSON.stringify(user));
  //           }

  //           return user;
  //       }));
  // }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    this.authSuccess = false;
  }
}
