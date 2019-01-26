import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

import{SignUpValidator} from '../shared/sign-up.validator';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {

  signupFormGroup: FormGroup;
  passwordFormGroup:FormGroup;
  submitted = false;

constructor(private formBuilder: FormBuilder) { 
  this.passwordFormGroup=this.formBuilder.group({
    password: ['', [Validators.required, Validators.minLength(6)]],
    confirm_password:['',Validators.required]},
    {validator:SignUpValidator.validate.bind(this)
  });
this.signupFormGroup = this.formBuilder.group({
    email: ['', [Validators.required, Validators.email]],
    passwordFormGroup:this.passwordFormGroup  
 });
}

ngOnInit() {
 
}

onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.signupFormGroup.invalid) {
        return;
    }

}

}
