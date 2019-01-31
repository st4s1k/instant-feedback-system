import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment.prod';
<<<<<<< HEAD
import { UserDTO } from '../dto/user.dto';
=======
import { Observable } from 'rxjs';
import { UserDTO } from '../dto/user.dto';
import { GlobalServUserService } from '../global-serv-user.service';
>>>>>>> fbb950323db589193751efe91c73a8d663224a56


const httpOptions = {
  headers: new HttpHeaders({
    // 'Content-Type': 'multipart/form-data',
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,OPTIONS',
    'Access-Control-Allow-Headers': 'Content-Type',
    // 'Access-Control-Allow-Credentials': 'true'
  })
};
const API_URL = environment.apiUrl;
@Injectable({
  providedIn: 'root'
})

export class AuthenticationService {
  constructor(private http: HttpClient, private globalSrv: GlobalServUserService) { }
  public authSuccess = false;
  userLocal: UserDTO;
  // RealBack

  // login(email: string, password: string) {

  //   const fd = new FormData();

  //   fd.append('email', email);
  //   fd.append('password', password);

  //   // working part
  //   // return this.http.post<any>(`${API_URL}/authentication`, JSON.stringify({ email: email, password: password }), httpOptions)
  //   // return this.http.post<any>(`${API_URL}/authentication`, fd, httpOptions)
  //   return this.http.post<any>(`${API_URL}/authentication`, fd)

<<<<<<< HEAD
    return this.http.post<any>(`${API_URL}/authenticate`, {email: email, password: password}, {
      headers: {
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,OPTIONS',
        'Access-Control-Allow-Headers': 'Content-Type',
      }
    })
=======
  //     .pipe(map(user => {
  //       // login successful if there's a user in the response
  //       if (user) {
  //         // store user details and basic auth credentials in local storage
  //         // to keep user logged in between page refreshes
  //         user.authdata = window.btoa(email + ':' + password);
  //         localStorage.setItem('currentUser', JSON.stringify(user));
  //         alert('Success!');
  //         alert(user);
  //         this.userLocal = JSON.parse(localStorage.getItem('currentUser'));
  //         this.globalSrv.setNavEmail(this.userLocal.email);
  //         this.globalSrv.setNavUserId(this.userLocal.id);
  //         localStorage.setItem('auth', JSON.stringify(true));
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
>>>>>>> fbb950323db589193751efe91c73a8d663224a56
      .pipe(map(user => {
        // login successful if there's a user in the response
        if (user) {
          // store user details and basic auth credentials in local storage
          // to keep user logged in between page refreshes
          user.authdata = window.btoa(email + ':' + password);
          localStorage.setItem('currentUser', JSON.stringify(user));
          alert('Success!');
          this.userLocal = JSON.parse(localStorage.getItem('currentUser'));
          this.globalSrv.setNavEmail(this.userLocal.email);
          this.globalSrv.setNavUserId(this.userLocal.id);
          localStorage.setItem('auth', JSON.stringify(true));
          const authe = JSON.parse(localStorage.getItem('auth'));
          this.globalSrv.setNavAuthenticated(authe);
        }

        return user;
      }));
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    // localStorage.removeItem('auth');
    localStorage.setItem('auth', JSON.stringify(false));
    this.globalSrv.setNavAuthenticated(false);
  }
}
