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
  public authenticated = +localStorage.getItem('sessionID');
  public UserEmail = localStorage.getItem('email');
  public UserId = +localStorage.getItem('userId');

  constructor(private auth: AuthenticationService, private globalSrv: GlobalServUserService) {
    this.globalSrv.SessionID.subscribe((sessId) => this.authenticated = sessId);
    this.globalSrv.navEmail.subscribe((email) => this.UserEmail = email);
  }


  ngOnInit() {

  }

  signOut() {
    this.auth.logout();
  }
}

