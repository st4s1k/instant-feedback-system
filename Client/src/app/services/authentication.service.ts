import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment.prod';
import { User } from '../models/user.model';
import { GlobalServUserService } from '../global-serv-user.service';
import { UserService } from './user.service';
import { UserDTO } from '../models/dtos/user.dto';


const SERVER_URL = environment.serverUrl;
const SIGNIN_API = environment.signinApiRoute;
const SIGNUP_API = environment.signupApiRoute;
@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  constructor(
    private http: HttpClient,
    private globalSrv: GlobalServUserService,
    private us: UserService
  ) { }

  userLocal: User;

  signup(user: User) {
    return this.http.post<UserDTO>(SERVER_URL + SIGNUP_API, User.toDTO(user));
  }

  signin(email: string, password: string) {


    // const fd = new FormData();

    // fd.append('email', email);
    // fd.append('password', password);

    return this.http.post<any>(SERVER_URL + SIGNIN_API, {email: email, password: password})
      .pipe(map(user => {
        // login successful if there's a user in the response
        if (user) {
          // store user details and basic auth credentials in local storage
          // to keep user logged in between page refreshes
          user.authdata = window.btoa(email + ':' + password);
          localStorage.setItem('currentUser', JSON.stringify(user));
          alert('Success!');
          alert(JSON.stringify(user));
          this.userLocal = JSON.parse(user);
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
    this.globalSrv.setSessionID(undefined);
    localStorage.removeItem('currentUser');
    localStorage.removeItem('email');
    localStorage.removeItem('userId');
    localStorage.removeItem('userRole');
    // this.globalSrv.setUserRole('NoRole');
  }
}
