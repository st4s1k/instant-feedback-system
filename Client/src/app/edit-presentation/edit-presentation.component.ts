import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormArray, Validators } from '@angular/forms';

@Component({
  selector: 'app-edit-presentation',
  templateUrl: './edit-presentation.component.html',
  styleUrls: ['./edit-presentation.component.scss']
})
export class EditPresentationComponent implements OnInit {

  edit_presForm: FormGroup;
  submitted = false;

  invite_touched = false;
  constructor(private fb: FormBuilder) { }

  ngOnInit() {
    this.edit_presForm = this.fb.group({
      title: ['', [Validators.required]],
      description: ['', [Validators.required]],
      time: ['', [Validators.required]],
      duration: ['', [Validators.required]],
      location: ['', [Validators.required]],
      emailInvitation: ['', [Validators.email]],
      emailInvitations: this.fb.array([])
    });
  }

  get f() {
    return this.edit_presForm.controls;
  }

  get emailInvitationForms() {
    return this.edit_presForm.get('emailInvitations') as FormArray;
  }

  addEmailInvitation() {
    this.invite_touched = true;
    const emailInvitation = this.fb.group({
      emailInvitations: [],
    });

    this.emailInvitationForms.push(emailInvitation);
  }

  deleteEmailInvitation(i) {
    this.emailInvitationForms.removeAt(i);
  }
  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.edit_presForm.invalid) {
      return;
    }

    

  }

}
