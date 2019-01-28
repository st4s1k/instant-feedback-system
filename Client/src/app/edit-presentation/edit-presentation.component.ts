import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormArray, Validators, FormControl } from '@angular/forms';
import { PresentationService } from '../services/presentation.service';
import { PresentationDTO } from '../dto/presentation.dto';

@Component({
  selector: 'app-edit-presentation',
  templateUrl: './edit-presentation.component.html',
  styleUrls: ['./edit-presentation.component.scss']
})
export class EditPresentationComponent implements OnInit {

  editPresentationForm: FormGroup;

  title: FormControl;
  description: FormControl;
  time: FormControl;
  duration: FormControl;
  date: FormControl;
  location: FormControl;
  emailInvitations: FormArray = this.fb.array([]);

  submitted = false;

  invite_touched = false;

  constructor(private data: PresentationService, private fb: FormBuilder) { }

  ngOnInit() {

    // if ( edit existing presentation ) {
    //     assign form group existing values
    // }

    this.editPresentationForm = this.fb.group({
      title: [this.title, [Validators.required]],
      description: [this.description, [Validators.required]],
      time: [this.time, [Validators.required]],
      duration: [this.duration, [Validators.required]],
      date: [this.date, [Validators.required]],
      location: [this.location, [Validators.required]],
      emailInvitations: this.emailInvitations
    });
  }

  get emailInvitationsFormArray() {
    return this.editPresentationForm.get('emailInvitations') as FormArray;
  }

  addEmailInvitation() {
    this.invite_touched = true;
    const emailInvitation = this.fb.group({
      emailInvitation: 'new email invitation',
    });

    this.emailInvitationsFormArray.push(emailInvitation);
  }

  deleteEmailInvitation(i) {
    this.emailInvitationsFormArray.removeAt(i);
  }
  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.editPresentationForm.invalid) {
      return;
    }

    this.data.createPresentation(new PresentationDTO({
      title: this.editPresentationForm.get('title').value,
      description: this.editPresentationForm.get('description').value,
      time: this.editPresentationForm.get('time').value,
      duration: this.editPresentationForm.get('duration').value,
      location: this.editPresentationForm.get('location').value,
      emailInvitations: this.emailInvitationsFormArray.controls.values
    }));

  }

}
