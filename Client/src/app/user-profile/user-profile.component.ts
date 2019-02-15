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
  presFound = true;
  notifier: NotifierService;
  message: String;

  constructor(
    private userService: UserService,
    private presentationService: PresentationService,
    private router: Router,
    private route: ActivatedRoute,
    private globUser: GlobalServUserService,
    private formBuilder: FormBuilder,
    notifierService: NotifierService
  ) {
    this.notifier = notifierService;
  }

  ngOnInit() {
    // this.getUserProfile();
    this.route.data.subscribe((data: { user: User }) => {
      this.user = data.user;
    });

    this.presentationService.getPresentationsByEmail(this.email)
      .subscribe(presentationList => {
        this.presentations = presentationList;

        this.presFound = !!presentationList;
      },
        error => {
          alert(error);
          this.presFound = false;
        });

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
            this.presentationService.getPresentations().subscribe(
              presentationDtoList => this.presentations = presentationDtoList.map(
                presentationDto => PresentationDTO.toModel(presentationDto)
              )
            );
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


}
