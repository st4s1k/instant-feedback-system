import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss']
})
export class NavComponent implements OnInit {
  public navbarCollapsed = true;
  public authenticated = false;
  public mailProfile = 'AMa@inther.com';

  constructor(private auth: AuthenticationService) { }

  ngOnInit() {
    // this.authenticated = this.auth.authSuccess;
    this.authenticated = this.auth.authSuccess;
  }
  signOut() {
    this.authenticated = false;
    this.auth.logout();
  }
}

