import { Component, OnInit } from '@angular/core';
import { User } from '../models/user.model';
import { Router, ActivatedRoute } from '@angular/router';
import { Presentation } from '../models/presentation.model';
import { PresentationService } from '../services/presentation.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-admin-profile',
  templateUrl: './admin-profile.component.html',
  styleUrls: ['./admin-profile.component.scss']
})
export class AdminProfileComponent implements OnInit {
  users: User[];
  presentations: Presentation[];
  arrEditUserbtn = new Array() as Array<boolean>;
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private userService: UserService,
    private presentationService: PresentationService
  ) { }
  ngOnInit() {
    this.route.data.subscribe((data: { presentations: Presentation[], users: User[] }) => {
      this.presentations = data.presentations;
      this.users = data.users;
    });
  }
  editUser(i: number) {
    console.log(this.users[i].id);
    if (this.arrEditUserbtn[i] === true) {
      this.arrEditUserbtn[i] = false;
    } else {
      this.arrEditUserbtn[i] = true;
    }

  }
  deleteUser(i: number) {
    // this.presentationService.deletePresentation(this.presentations[i].id);
  }
  openPresentationPage(i: number) {
    // console.log('Trying to open presentation ' + this.presentations[i].id);
    this.router.navigate([`/presentation-page/${this.presentations[i].id}`]);
  }
  editPresentationPage(i: number) {
    this.router.navigate([`/edit-presentation/${this.presentations[i].id}`]);
  }
  deletePresentationPage(i: number) {
    this.presentationService.deletePresentation(this.presentations[i].id);
  }
}
