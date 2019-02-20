import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import { GlobalServUserService } from '../services/global-serv-user.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss']
})
export class NavComponent implements OnInit {
  public adminRole = environment.adminRole;
  public navbarCollapsed = true;
  public authenticated = localStorage.getItem('sessionID');
  public UserEmail = localStorage.getItem('email');
  public UserId = localStorage.getItem('userId');
  public UserRole = localStorage.getItem('userRole');

  constructor(private auth: AuthenticationService, private globalSrv: GlobalServUserService) {
    this.globalSrv.SessionID.subscribe((sessId) => this.authenticated = sessId);
    this.globalSrv.navEmail.subscribe((email) => this.UserEmail = email);
    this.globalSrv.UserRole.subscribe((role) => this.UserRole = role);
  }


  ngOnInit() {

  }

  signOut() {
    this.auth.logout();
  }
}

