import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import { GlobalServUserService } from '../global-serv-user.service';


@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss']
})
export class NavComponent implements OnInit {
  public navbarCollapsed = true;
  // public authenticated;
  // public UserEmail;
  // public UserId;
  public authenticated = !Number.isNaN(+localStorage.getItem('sessionID'));
  // public authenticated = localStorage.getItem('auth');
  public UserEmail = localStorage.getItem('email');
  public UserId = +localStorage.getItem('userId');

  constructor(private auth: AuthenticationService, private globalSrv: GlobalServUserService) {
    this.globalSrv.NavAuthenticated.subscribe((aut) => this.authenticated = aut);
    this.globalSrv.SessionID.subscribe((sessId) => this.authenticated = !Number.isNaN(sessId));
  }


  ngOnInit() {

  }

  signOut() {
    this.auth.logout();
  }
}

