import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { first } from 'rxjs/operators';
import { AuthenticationService } from '../services/authentication.service';



@Component({
    selector: 'app-sign-in',
    templateUrl: './sign-in.component.html',
    styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent implements OnInit {

    signinForm: FormGroup;
    passwordFormGroup: FormGroup;
    submitted = false;
    loading = false;
    returnUrl: string;
    error = '';

    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private authenticationService: AuthenticationService) { }

    ngOnInit() {
        this.signinForm = this.formBuilder.group({
            email: ['', [Validators.required, Validators.email]],
            password: ['', [Validators.required, Validators.minLength(6)]]
        });

        // reset login status
        this.authenticationService.logout();

        // get return url from route parameters or default to '/'
        this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    }



    onSubmit() {
        this.submitted = true;

        // stop here if form is invalid
        if (this.signinForm.invalid) {
            return;
        }
        this.loading = true;
        this.authenticationService.login(this.signinForm.controls.email.value, this.signinForm.controls.password.value)
            .pipe(first())
            .subscribe(
                data => {
                    this.router.navigate([this.returnUrl]);

                    alert('Succes');

                },
                error => {
                    this.error = error;
                    this.loading = false;
                    alert('Error log');
                });
    }
}
