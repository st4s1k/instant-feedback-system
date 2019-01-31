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
  public authenticated;
  public UserEmail = 'test';
  public UserId;

  constructor(private auth: AuthenticationService, private globalSrv: GlobalServUserService) {
    this.globalSrv.navEmail.subscribe((email) => this.UserEmail = email);
    this.globalSrv.navUser.subscribe((id) => this.UserId = id);
    this.globalSrv.NavAuthenticated.subscribe((aut) => this.authenticated = aut);
  }


  ngOnInit() {

  }

  signOut() {
    this.auth.logout();
  }
}

