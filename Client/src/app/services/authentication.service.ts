import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment.prod';
import { Observable } from 'rxjs';
import { UserDTO } from '../dto/user.dto';
import { GlobalServUserService } from '../global-serv-user.service';


const API_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  constructor(private http: HttpClient, private globalSrv: GlobalServUserService) { }

  userLocal: UserDTO;

  // real api
  // login(email: string, password: string) {


  //   const fd = new FormData();

  //   fd.append('email', email);
  //   fd.append('password', password);

  //   // return this.http.post<any>(`${API_URL}/authentication`, fd)
  //   return this.http.post<any>(`http://172.17.41.122:80/authentication`, fd)
  //     .pipe(map(user => {
  //       // login successful if there's a user in the response
  //       if (user) {
  //         // store user details and basic auth credentials in local storage
  //         // to keep user logged in between page refreshes
  //         user.authdata = window.btoa(email + ':' + password);
  //         localStorage.setItem('currentUser', JSON.stringify(user));
  //         alert('Success!');
  //        alert(JSON.stringify(user));
  //         this.userLocal = JSON.parse(localStorage.getItem('currentUser'));
  //         this.globalSrv.setNavEmail(this.userLocal.email);
  //         this.globalSrv.setNavUserId(this.userLocal.id);
  //         localStorage.setItem('auth', JSON.stringify(true));
  //         localStorage.setItem('email', JSON.stringify(this.userLocal.email));
  //         const authe = JSON.parse(localStorage.getItem('auth'));
  //         this.globalSrv.setNavAuthenticated(authe);
  //       }
  //       // alert()
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
          // user.authdata = window.btoa(email + ':' + password);
          user.authdata = window.btoa(email + ':' + password);
          localStorage.setItem('currentUser', JSON.stringify(user));
          alert('Success!');
          alert(user);
          this.userLocal = JSON.parse(localStorage.getItem('currentUser'));
          this.globalSrv.setNavEmail(this.userLocal.email);
          this.globalSrv.setNavUserId(this.userLocal.id);
          localStorage.setItem('auth', JSON.stringify(true));
          localStorage.setItem('email', JSON.stringify(this.userLocal.email));
          localStorage.setItem('userId', JSON.stringify(this.userLocal.id));
          const authe = JSON.parse(localStorage.getItem('auth'));
          this.globalSrv.setNavAuthenticated(authe);

        }

        return user;
      }));
  }

  logout() {
    // remove user from local storage to log user out
    // localStorage.removeItem('auth');
    localStorage.setItem('auth', JSON.stringify(false));
    this.globalSrv.setNavAuthenticated(false);
    localStorage.removeItem('currentUser');
    localStorage.removeItem('email');
    localStorage.removeItem('userId');
  }
}
