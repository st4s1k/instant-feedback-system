import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { first } from 'rxjs/operators';
import { MustMatch } from '../shared/sign-up.validator';
import { UserService } from '../services/user.service';
import { from } from 'rxjs';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {

  signupForm: FormGroup;
  submitted = false;

  loading = false;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService) {

  }

  ngOnInit() {

    this.signupForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirm_password: ['', Validators.required]
    }, {
        validator: MustMatch('password', 'confirm_password')
      });

  }

  // get f() { return this.signupForm.controls; }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.signupForm.invalid) {
      return;
    }

    this.loading = true;
    this.userService.createUser(this.signupForm.value).pipe(first())
      .subscribe(
        data => {
          console.log('Succes Registration');
          // alert('Success');
          this.router.navigate(['/sign-in']);
        },
        error => {
          alert(error);
          console.log(error);
          this.loading = false;
        }
      );

  }

}
