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

  // public authenticated = JSON.parse(localStorage.getItem('sessionID'));
  // public UserId = JSON.parse(localStorage.getItem('userId'));
  // public UserEmail = JSON.parse(localStorage.getItem('email'));
  public authenticated = +localStorage.getItem('sessionID');
  public UserId = +localStorage.getItem('userId');
  public UserEmail = localStorage.getItem('email');

  constructor(private auth: AuthenticationService, private globalSrv: GlobalServUserService) {
    this.globalSrv.NavAuthenticated.subscribe((aut) => this.authenticated = aut);
    this.globalSrv.navUser.subscribe((id) => this.UserId = id);
    this.globalSrv.navEmail.subscribe((email) => this.UserEmail = email);
  }


  ngOnInit() {

  }

  signOut() {
    this.auth.logout();
  }
}

