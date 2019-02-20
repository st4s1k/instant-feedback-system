import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { first } from 'rxjs/operators';
import { AuthenticationService } from '../services/authentication.service';
import { NotifierService } from 'angular-notifier';


@Component({
    selector: 'app-sign-in',
    templateUrl: './sign-in.component.html',
    styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent implements OnInit {

    signInForm: FormGroup;
    submitted = false;
    loading = false;
    returnUrl: string;
    error = '';
    private readonly notifier: NotifierService;

    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private authenticationService: AuthenticationService,
        notifierService: NotifierService) {
            this.notifier = notifierService;
         }

    ngOnInit() {
        this.signInForm = this.formBuilder.group({
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
        if (this.signInForm.invalid) {
            return;
        }
        this.loading = true;
        this.authenticationService.signin(this.signInForm.controls.email.value, this.signInForm.controls.password.value)
            .pipe(first())
            .subscribe(
                data => {
                    this.router.navigate([this.returnUrl]);
                    this.notifier.notify('success', 'Succes Sign In');
                },
                error => {
                    console.log(error);
                    this.notifier.notify('error', 'Sign in failed');
                    this.notifier.notify('error', error);
                    this.loading = false;
                    // alert(error);
                });
    }
}
