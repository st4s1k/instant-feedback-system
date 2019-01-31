import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { UserDTO } from '../dto/user.dto';
import { Router, ActivatedRoute } from '@angular/router';
import { GlobalServUserService } from '../global-serv-user.service';
// import { userInfo } from 'os';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

  user: UserDTO;

  constructor(
    private userService: UserService,
    private router: Router,
    private route: ActivatedRoute,
    private globUser: GlobalServUserService
  ) { }

  ngOnInit() {
    this.getUserProfile();
  }

  getUserProfile(): void {
    // const id = +this.globUser.navUser
    const id = +this.route.snapshot.paramMap.get('id');
    this.userService.getUserById(id)
      .subscribe(user => this.user = user);
    console.log(id);
    // .subscribe(presentation => this.presentation = presentation);
  }

}
