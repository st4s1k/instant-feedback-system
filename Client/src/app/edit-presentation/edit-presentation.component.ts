import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormArray } from '@angular/forms';

@Component({
  selector: 'app-edit-presentation',
  templateUrl: './edit-presentation.component.html',
  styleUrls: ['./edit-presentation.component.scss']
})
export class AddPresComponent implements OnInit {

  myForm: FormGroup;

  constructor(private fb: FormBuilder) { }

  ngOnInit() {
    this.myForm = this.fb.group({
      email: '',
      emails: this.fb.array([])
    });

  }

  get emailForms() {
    return this.myForm.get('emails') as FormArray;
  }

  addEmail() {

    const email = this.fb.group({
      email: [],
    });

    this.emailForms.push(email);
  }

  deleteEmail(i) {
    this.emailForms.removeAt(i);
  }

}
