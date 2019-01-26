import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormArray } from '@angular/forms';

@Component({
  selector: 'app-edit-presentation',
  templateUrl: './edit-presentation.component.html',
  styleUrls: ['./edit-presentation.component.scss']
})
export class AddPresComponent implements OnInit {

  emailInvitationForm: FormGroup;

  constructor(private fb: FormBuilder) { }

  ngOnInit() {
    this.emailInvitationForm = this.fb.group({
      emailInvitation: '',
      emailInvitations: this.fb.array([])
    });

  }

  get emailInvitationForms() {
    return this.emailInvitationForm.get('emailInvitations') as FormArray;
  }

  addEmailInvitation() {

    const emailInvitation = this.fb.group({
      emailInvitations: [],
    });

    this.emailInvitationForms.push(emailInvitation);
  }

  deleteEmailInvitation(i) {
    this.emailInvitationForms.removeAt(i);
  }

}
