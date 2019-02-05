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
    this.router.navigate([`/edit-presentation/${this.presentations[i].id}`]);
  }
  deleteUser(i: number) {
    this.presentationService.deletePresentation(this.presentations[i].id);
  }
}
