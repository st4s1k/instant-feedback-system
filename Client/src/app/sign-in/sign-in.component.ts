import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';


@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent implements OnInit {
  
singinForm: FormGroup;
submitted = false;

constructor(private formBuilder: FormBuilder) { }

ngOnInit() {
    this.singinForm = this.formBuilder.group({
        email: ['', [Validators.required, Validators.email]],
        password: ['', [Validators.required, Validators.minLength(6)]]
    });
}

// convenience getter for easy access to form fields
get f() { return this.singinForm.controls; }

onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.singinForm.invalid) {
        return;
    }

}
}
