import { Component, OnInit } from '@angular/core';
import { User } from '../models/user.model';
import { Router, ActivatedRoute } from '@angular/router';
import { Presentation } from '../models/presentation.model';
import { PresentationService } from '../services/presentation.service';
import { UserService } from '../services/user.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MustMatch } from '../shared/sign-up.validator';
import { first } from 'rxjs/operators';
import { UserDTO } from '../models/dtos/user.dto';
import { PresentationDTO } from '../models/dtos/presentation.dto';
import { NotifierService } from 'angular-notifier';
import { environment } from '../../environments/environment.prod';

@Component({
  selector: 'app-admin-profile',
  templateUrl: './admin-profile.component.html',
  styleUrls: ['./admin-profile.component.scss']
})
export class AdminProfileComponent implements OnInit {
  users: User[];
  presentations: Presentation[];
  arrEditUserbtn = [] as Array<boolean>;
  submitted = false;
  loading = false;
  editUserForm: FormGroup;
  addUserBtn = false;
  notifier: NotifierService;
  message: String;
  roleUser = environment.userRole;
  roleAdmin = environment.adminRole;

  pageSize = environment.defaultPageSize;
  currentPage = 1;
  numberOfPages = 0;
  totalElements = 0;

  pageAdminSize = environment.defaultUserPageSize;
  currentAdminPage = 1;
  numberOfAdminPages = 0;
  totalAdminElements = 0;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private userService: UserService,
    private presentationService: PresentationService,
    private formBuilder: FormBuilder,
    notifierService: NotifierService
  ) {
    this.notifier = notifierService;
  }
  ngOnInit() {
    this.openPresentationAdminPage();
    this.openUserAdminPage();
    this.editUserForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      userGroup: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirm_password: ['', Validators.required]
    }, {
        validator: MustMatch('password', 'confirm_password')
      });
  }

  editUser(i: number) {
    console.log(this.users[i].id);

    this.arrEditUserbtn[i] = this.arrEditUserbtn[i] !== true;

    this.editUserForm = this.formBuilder.group({
      email: this.users[i].email,
      userGroup: this.users[i].role,
      password: this.users[i].password,
      confirm_password: this.users[i].password
    }, {
        validator: MustMatch('password', 'confirm_password')
      });
  }
  addUser() {
    this.addUserBtn = this.addUserBtn === false;
  }
  submitAddUser() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.editUserForm.invalid) {
      return;
    }
    this.loading = true;
    this.userService.createUser(<User>{
      email: this.editUserForm.get('email').value,
      role: this.editUserForm.get('userGroup').value,
      password: this.editUserForm.get('password').value
    }).pipe(first()).subscribe(
      data => {
        console.log('data: ' + JSON.stringify(data));
        this.openUserAdminPage();
        this.loading = false;
        this.addUserBtn = false;
        this.submitted = false;
        this.notifier.notify('success', 'User successfully added');
      },
      error => {
        this.notifier.notify('error', 'Error on adding user');
        this.notifier.notify('error', error);
      }
    );
  }
  onCancelAdd() {
    this.addUserBtn = false;
    this.editUserForm.reset();
  }
  onSubmitEdit(i: number) {
    this.submitted = true;
    // stop here if form is invalid
    if (this.editUserForm.invalid) {
      return;
    }
    this.loading = true;

    const editingUser = <User>{
      id: this.users[i].id,
      email: this.editUserForm.get('email').value,
      role: this.editUserForm.get('userGroup').value,
      password: this.editUserForm.get('password').value
    };

    this.userService.updateUser(editingUser).pipe(first())
      .subscribe(
        () => {
          console.log('Succes');
          this.message = editingUser.email + 'information successfuly updated';
          this.notifier.notify('success', this.message.toString());
          this.submitted = false;
          this.editUserForm.reset();
          this.arrEditUserbtn[i] = false;
          this.users[i] = UserDTO.toModel(editingUser);
          this.loading = false;
        },
        error => {
          this.notifier.notify('error', 'Something wrong');
          this.notifier.notify('error', error);
          this.loading = false;
        }
      );
  }
  onCancel(i: number) {
    this.arrEditUserbtn[i] = false;
    this.editUserForm.reset();
  }
  deleteUser(i: number) {
    if (confirm('Are you sure that you want to delete ' + this.users[i].email + ' ?')) {
      this.userService.deleteUser(this.users[i].id)
        .pipe(first()).subscribe(
          data => {
            console.log('data: ' + JSON.stringify(data));
            this.openUserAdminPage();
            this.message = this.users[i].email + 'successfuly deleted';
            this.notifier.notify('info', this.message.toString());
          },
          error => {
            this.notifier.notify('error', 'Error on delete');
            console.log('error: ' + error);
          }
        );
    }
  }
  openPresentationPage(i: number) {
    this.router.navigate([`/presentation-page/${this.presentations[i].id}`]);
  }
  editPresentationPage(i: number) {
    this.router.navigate([`/edit-presentation/${this.presentations[i].id}`]);
  }
  deletePresentationPage(i: number) {
    if (confirm('Are you sure that you want to delete ' + this.presentations[i].title + ' presentationId ?')) {
      this.presentationService.deletePresentation(this.presentations[i].id)
        .pipe(first())
        .subscribe(
          data => {
            this.notifier.notify('info', data);
            this.openPresentationAdminPage();
          },
          error => {
            this.notifier.notify('error', 'Error on delete ' + error);
            console.log('error: ' + error);
          }
        );
    }
  }
  openUserAdminPage() {
    this.userService.getUsersByPage(this.currentAdminPage -1)
      .pipe(first()).subscribe(pages => {
        console.log('openPage: ' + JSON.stringify(pages));
        if (pages && pages.content) {
          this.users = pages.content
            .map(userDto => UserDTO.toModel(userDto));
          this.numberOfAdminPages = pages.totalPages;
          this.totalAdminElements = pages.totalElements;
        } else {
          this.users = [];
          this.numberOfPages = 0;
          this.totalElements = 0;
        }
      }
    );
  }

  openPresentationAdminPage() {
    this.presentationService.getPresentationsByPage(this.currentPage -1)
      .pipe(first()).subscribe(pages => {
        console.log('openPage: ' + JSON.stringify(pages));
        if (pages && pages.content) {
          this.presentations = pages.content
            .map(presentationDto => PresentationDTO.toModel(presentationDto));
          this.numberOfPages = pages.totalPages;
          this.totalElements = pages.totalElements;
        } else {
          this.presentations = [];
          this.numberOfPages = 0;
          this.totalElements = 0;
        }
      }
    );
  }
}
