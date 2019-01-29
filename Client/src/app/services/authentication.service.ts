import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment.prod';

const API_URL = environment.apiUrl;
@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  constructor(private http: HttpClient) { }

  login(email: string, password: string) {
    //   return this.http.post<any>(`${API_URL}/users/authenticate`, { email, password })
         return this.http.post<any>(`${API_URL}/userLogin`, { email, password })
          .pipe(map(user => {
              // login successful if there's a user in the response
              if (user) {
                  // store user details and basic auth credentials in local storage 
                  // to keep user logged in between page refreshes
                  user.authdata = window.btoa(email + ':' + password);
                  localStorage.setItem('currentUser', JSON.stringify(user));
              }

              return user;
          }));
  }

  logout() {
      // remove user from local storage to log user out
      localStorage.removeItem('currentUser');
  }
}
