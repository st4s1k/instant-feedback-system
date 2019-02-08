import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { first } from 'rxjs/operators';
import { MustMatch } from '../shared/sign-up.validator';
import { User } from '../models/user.model';
import { AuthenticationService } from '../services/authentication.service';

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
    private auth: AuthenticationService) {

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

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.signupForm.invalid) {
      return;
    }

    this.loading = true;
    this.auth.signup(<User> {
      email: this.signupForm.get('email').value,
      password: this.signupForm.get('password').value
    }).pipe(first())
      .subscribe(
        data => {
          console.log('Succes Registration');
          alert('Success');
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
