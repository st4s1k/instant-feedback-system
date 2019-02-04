import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment.prod';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';
import { GlobalServUserService } from '../global-serv-user.service';


const API_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  constructor(private http: HttpClient, private globalSrv: GlobalServUserService) { }

  userLocal: User;

  // // real api
  // login(email: string, password: string) {


  //   const fd = new FormData();

  //   fd.append('email', email);
  //   fd.append('password', password);

  //   // return this.http.post<any>(`${API_URL}/authentication`, fd)
  //   return this.http.post<any>(`http://172.17.41.68:9000/authentication`, fd)
  //     .pipe(map(user => {
  //       // login successful if there's a user in the response
  //       if (user) {
  //         // store user details and basic auth credentials in local storage
  //         // to keep user logged in between page refreshes
  //         user.authdata = window.btoa(email + ':' + password);
  //         localStorage.setItem('currentUser', JSON.stringify(user));
  //         alert('Success!');
  //         alert(JSON.parse(user));
  //         this.userLocal = JSON.parse(localStorage.getItem('currentUser'));
  //         this.globalSrv.setNavEmail(this.userLocal.email);
  //         this.globalSrv.setNavUserId(this.userLocal.id);
  //         this.globalSrv.setSessionID(this.userLocal.id);
  //         this.globalSrv.setUserRole(`${this.userLocal.type}`);

  //         localStorage.setItem('sessionID', `${this.userLocal.id}`);
  //         localStorage.setItem('email', this.userLocal.email);
  //         localStorage.setItem('userId', `${this.userLocal.id}`);
  //         localStorage.setItem('userRole', `${this.userLocal.type}`);
  //       }
  //       return user;
  //     }));
  // }

  // Fake backend
  login(email: string, password: string) {
    return this.http.post<any>(`${API_URL}/users/authenticate`, { email, password })
      .pipe(map(user => {
        // login successful if there's a user in the response
        if (user) {
          // store user details and basic auth credentials in local storage
          // to keep user logged in between page refreshes
          user.authdata = window.btoa(email + ':' + password);
          localStorage.setItem('currentUser', JSON.stringify(user));
          alert('Success!');
          alert(JSON.stringify(user));
          this.userLocal = JSON.parse(localStorage.getItem('currentUser'));

          this.globalSrv.setNavEmail(this.userLocal.email);
          this.globalSrv.setNavUserId(this.userLocal.id);
          this.globalSrv.setSessionID(this.userLocal.id);
          this.globalSrv.setUserRole(`${this.userLocal.type}`);

          localStorage.setItem('sessionID', `${this.userLocal.id}`);
          localStorage.setItem('email', this.userLocal.email);
          localStorage.setItem('userId', `${this.userLocal.id}`);
          localStorage.setItem('userRole', `${this.userLocal.type}`);
        }

        return user;
      }));
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.setItem('sessionID', '0');
    this.globalSrv.setSessionID(0);
    localStorage.removeItem('currentUser');
    localStorage.removeItem('email');
    localStorage.removeItem('userId');
    // localStorage.removeItem('userRole');
    localStorage.setItem('userRole', 'NoRole');
    this.globalSrv.setUserRole('NoRole');
  }
}
