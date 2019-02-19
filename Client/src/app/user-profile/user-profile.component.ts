import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { User } from '../models/user.model';
import { Router, ActivatedRoute } from '@angular/router';
import { GlobalServUserService } from '../services/global-serv-user.service';
import { MustMatch } from '../shared/sign-up.validator';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { first} from 'rxjs/operators';
import { PresentationService } from '../services/presentation.service';
import { Presentation } from '../models/presentation.model';
import { PresentationDTO } from '../models/dtos/presentation.dto';
import { NotifierService } from 'angular-notifier';
import {environment} from "../../environments/environment.prod";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

  user: User;
  presentations: Presentation[];
  btnChange = false;

  changePassForm: FormGroup;
  submitted = false;
  loading = false;
  id = localStorage.getItem('userId');
  email = localStorage.getItem('email');
  message: String;

  pageSize = environment.defaultPageSize;
  currentPage = 1;
  numberOfPages = 0;
  totalElements = 0;


  constructor(
    private userService: UserService,
    private presentationService: PresentationService,
    private router: Router,
    private route: ActivatedRoute,
    private globUser: GlobalServUserService,
    private formBuilder: FormBuilder,
    private notifier: NotifierService
  ) { }

  ngOnInit() {
    // this.getUserProfile();
    this.route.data.subscribe((data: { user: User, presentations : Presentation}) => {
      this.user = data.user;
      // this.presentations = data.presentations;
    });
    this.openUserPresentationPage();
    this.route.data.subscribe((data: { pages: any }) => {
        console.log('paginated presentations: ' + JSON.stringify(data.pages));
        if (data.pages && data.pages.content) {
          this.presentations = data.pages.content
            .map(presentationDto => PresentationDTO.toModel(presentationDto));
          this.numberOfPages = data.pages.totalPages;
          this.totalElements = data.pages.totalElements;
        }
      }
    );

    this.changePassForm = this.formBuilder.group({
      NewPass: ['', [Validators.required, Validators.minLength(6)]],
      ConfirmNewPass: ['', Validators.required]
    }, {
      validator: MustMatch('NewPass', 'ConfirmNewPass')
    });

  }
  openPresentationPage(i: number) {
    // console.log('Trying to open presentation ' + this.presentations[i].id);
    this.router.navigate([`/presentation-page/${this.presentations[i].id}`]);
  }
  editPresentationPage(i: number) {
    this.router.navigate([`/edit-presentation/${this.presentations[i].id}`]);
  }
  deletePresentationPage(i: number) {
    if (confirm('Are you sure that you want to delete ' + this.presentations[i].title + ' presentation ?')) {
      this.presentationService.deletePresentation(this.presentations[i].id)
        .pipe(first())
        .subscribe(
          data => {
            console.log('data: ' + JSON.stringify(data));
            this.openUserPresentationPage();
            this.message = 'Presentation: ' + this.presentations[i].title + 'successfully deleted!';
            this.notifier.notify('info', this.message.toString());
          },
          error => {
            this.notifier.notify('error', 'Error on delete');
            console.log('error: ' + error);
          }
        );
    }
  }

  getUserProfile(): void {
    const id = localStorage.getItem('userId');
    this.userService.getUserById(id)
      .subscribe(user => this.user = user);
    console.log(id);
  }

  changePass() {
    this.btnChange = true;
  }

  onSubmit() {
    this.submitted = true;
    // if it is not valid return
    if (this.changePassForm.invalid) {
      return;
    }
    this.loading = true;
    this.userService.updateUser(<User>{
      id: localStorage.getItem('userId'),
      role: localStorage.getItem('userRole'),
      email: localStorage.getItem('email'),
      password: this.changePassForm.get('NewPass').value
    }).pipe(first())
      .subscribe(
        () => {
          console.log('Succes Update password');
          this.notifier.notify('info', 'Password changed successfully');
        },
        () => {
          console.log('Succes Update password');
          this.notifier.notify('info', 'Password changed successfully');
        });
    this.loading = false;
    this.submitted = false;
    this.btnChange = false;
    this.changePassForm.reset();
  }
  onCancel() {
    this.btnChange = false;
    this.changePassForm.reset();
  }

  openUserPresentationPage() {
    this.presentationService.getPresentationByPageAndUser(this.currentPage - 1,this.email)
      .pipe(first()).subscribe(pages => {
        console.log('openPage: ' + JSON.stringify(pages));
        if (pages && pages.content) {
          this.presentations = pages.content
            .map(presentationDto => PresentationDTO.toModel(presentationDto));
          this.numberOfPages = pages.totalPages;
          this.totalElements = pages.totalElements;
        }
      }
    );
  }

}
