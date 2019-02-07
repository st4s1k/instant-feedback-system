import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { User } from '../models/user.model';
import { Router, ActivatedRoute } from '@angular/router';
import { GlobalServUserService } from '../global-serv-user.service';
import { MustMatch } from '../shared/sign-up.validator';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { PresentationService } from '../services/presentation.service';
import { Presentation } from '../models/presentation.model';
import { HttpClient } from '@angular/common/http';

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

  constructor(
    private userService: UserService,
    private presentationService: PresentationService,
    private router: Router,
    private route: ActivatedRoute,
    private globUser: GlobalServUserService,
    private formBuilder: FormBuilder

  ) { }

  ngOnInit() {
    // this.getUserProfile();
    this.route.data.subscribe((data: { user: User }) => {
      this.user = data.user;
    });
    this.route.data.subscribe((data: { presentations: Presentation[] }) => {
      this.presentations = data.presentations;
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
      this.presentationService.deletePresentation(this.presentations[i].id);
      alert('Deleted');
    }
  }

    getUserProfile(): void {
      // const id = +this.route.snapshot.paramMap.get('id');
      const id = JSON.parse(localStorage.getItem('userId'));
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
        // id: localStorage.getItem('userId'),
        id: 4,
        // email: localStorage.getItem('email'),
        password: this.changePassForm.get('NewPass').value
      }).pipe(first())
        .subscribe(
          data => {
            console.log('Succes Update');
            alert('Success');
            this.router.navigate(['/']);
          },
          error => {
            alert(error);
            console.log(error);
            this.loading = false;
          });
    }
    onCancel() {
      this.btnChange = false;
      this.changePassForm.reset();
    }


  }
