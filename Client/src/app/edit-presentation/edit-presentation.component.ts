import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormArray, Validators } from '@angular/forms';
import { DataService } from '../services/data.service';
import { Presentation } from '../models/presentation.model';

@Component({
  selector: 'app-edit-presentation',
  templateUrl: './edit-presentation.component.html',
  styleUrls: ['./edit-presentation.component.scss']
})
export class EditPresentationComponent implements OnInit {

  editPresentationForm: FormGroup;
  submitted = false;

  invite_touched = false;
  constructor(private data: DataService, private fb: FormBuilder) { }

  ngOnInit() {
    this.editPresentationForm = this.fb.group({
      title: ['', [Validators.required]],
      description: ['', [Validators.required]],
      time: ['', [Validators.required]],
      duration: ['', [Validators.required]],
      date: ['', [Validators.required]],
      location: ['', [Validators.required]],
      emailInvitation: ['', [Validators.email]],
      emailInvitations: this.fb.array([])
    });
  }

  get f() {
    return this.editPresentationForm.controls;
  }

  get emailInvitationForms() {
    return this.editPresentationForm.get('emailInvitations') as FormArray;
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
    if (this.editPresentationForm.invalid) {
      return;
    }

    this.data.createPresentation(<Presentation> {
      title: this.editPresentationForm.get('title').value,
      description: this.editPresentationForm.get('description').value,
      time: this.editPresentationForm.get('time').value,
      duration: this.editPresentationForm.get('duration').value,
      location: this.editPresentationForm.get('location').value,
      emailInvitations: this.editPresentationForm.get('emailInvitations').value
    });

  }

}
