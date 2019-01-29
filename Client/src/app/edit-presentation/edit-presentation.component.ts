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

  editPresentationFormGroup: FormGroup;

  title: FormControl = new FormControl('Template Title', [Validators.required]);
  description: FormControl = new FormControl('Template Description', [Validators.required]);
  time: FormControl = new FormControl('23:00', [Validators.required]);
  duration: FormControl = new FormControl('12:00', [Validators.required]);
  date: FormControl = new FormControl('1996-12-12', [Validators.required]);
  location: FormControl = new FormControl('Template Location', [Validators.required]);
  emailInvitations: FormArray = this.fb.array([]);

  presentation: PresentationDTO;
  presentationJSON: string;

  submitted = false;


  invite_touched = false;

  constructor(private data: PresentationService, private fb: FormBuilder) { }

  ngOnInit() {

    // if ( edit existing presentation ) {
    //     assign form group existing values
    // }

    this.emailInvitations.push(this.fb.group({
      emailInvitation: ['test@email.com', [Validators.required, Validators.email]]
    })); // ONLY FOR TESTING

    this.editPresentationFormGroup = this.fb.group({
      title: this.title,
      description: this.description,
      time: this.time,
      duration: this.duration,
      date: this.date,
      location: this.location,
      emailInvitations: this.emailInvitations
    });
  }

  addEmailInvitation() {
    this.invite_touched = true;

    this.emailInvitations.push(this.fb.group({
      emailInvitation: ['', [Validators.required, Validators.email]]
    }));
  }

  deleteEmailInvitation(i) {
    this.emailInvitations.removeAt(i);
  }

  save() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.editPresentationFormGroup.invalid) {
      return;
    }

    this.presentation = new PresentationDTO({
      title: this.title.value,
      description: this.description.value,
      time: this.time.value,
      duration: this.duration.value,
      location: this.location.value,
      emailInvitations: this.emailInvitations.value
    });

    this.presentationJSON = JSON.stringify(this.presentation);

    this.data.createPresentation(this.presentation);

  }

  discard() {
  }

}
