import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormArray, Validators, FormControl } from '@angular/forms';
import { PresentationService } from '../services/presentation.service';
import { Presentation } from '../models/presentation.model';
import { first } from 'rxjs/operators';
import { Router, ActivatedRoute } from '@angular/router';
import { GlobalServUserService } from '../global-serv-user.service';

@Component({
  selector: 'app-edit-presentation',
  templateUrl: './edit-presentation.component.html',
  styleUrls: ['./edit-presentation.component.scss']
})
export class EditPresentationComponent implements OnInit {

  editPresentationFormGroup: FormGroup;

  title: FormControl = this.fb.control('', [Validators.required]);
  description: FormControl = this.fb.control('', [Validators.required]);
  startTime: FormControl = this.fb.control('', [Validators.required]);
  endTime: FormControl = this.fb.control('', [Validators.required]);
  date: FormControl = this.fb.control('', [Validators.required]);
  location: FormControl = this.fb.control('', [Validators.required]);
  emailInvitations: FormArray = this.fb.array([]);

  presentation: Presentation;

  pageTitle: string;

  submitted = false;
  invite_touched = false;

  constructor(
    private presentationService: PresentationService,
    private router: Router,
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private gs: GlobalServUserService
  ) { }

  ngOnInit() {

    if (this.router.url === '/new-presentation') {
      this.pageTitle = 'New presentation';
      this.presentation = new Presentation();
    } else {
      this.pageTitle = 'Edit presentation';

      this.route.data.subscribe((data: { presentation: Presentation }) => {
        this.presentation = new Presentation(data.presentation);
        this.modelToFormGroup();
      });
    }

    this.editPresentationFormGroup = this.fb.group({
      title: this.title,
      description: this.description,
      startTime: this.startTime,
      endTime: this.endTime,
      date: this.date,
      location: this.location,
      emailInvitations: this.emailInvitations
    });
  }

  modelToFormGroup = function () {
    this.title.setValue(this.presentation.title);
    this.description.setValue(this.presentation.description);
    this.startTime.setValue(this.presentation.startDate.split('T')[1]);
    this.endTime.setValue(this.presentation.endDate.split('T')[1]);
    this.date.setValue(this.presentation.startDate.split('T')[0]);
    this.location.setValue(this.presentation.place);
    this.emailInvitations = this.fb.array([]);

    for (const participant of this.presentation.participants) {
      this.emailInvitations.push(this.fb.control(
        participant.email, [Validators.required, Validators.email]
      ));
    }
  };

  formGroupToModel = function () {
    this.presentation.email = localStorage.getItem('email');
    this.presentation.title = this.title.value;
    this.presentation.description = this.description.value;
    this.presentation.startDate = this.date.value + 'T' + this.startTime.value;
    this.presentation.place = this.location.value;
    this.presentation.endDate = this.date.value + 'T' + this.endTime.value;
    this.presentation.participants = [];

    for (const emailInvitation of this.emailInvitations) {
      this.presentation.participants.push({
        email: emailInvitation
      });
    }
  };

  addEmailInvitation() {
    this.invite_touched = true;

    this.emailInvitations.push(this.fb.control(
      '', [Validators.required, Validators.email]
    ));
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

    this.formGroupToModel();

    this.sendNewPresentation().pipe(first())
      .subscribe(data => {
        this.router.navigate([`/presentation-page/${this.presentation.id}`]);
      }, error => {
        alert(error);
      });
  }

  sendNewPresentation() {
    if (this.router.url === '/new-presentation') {
      return this.presentationService.createPresentation(this.presentation);
    } else {
      return this.presentationService.updatePresentation(this.presentation);
    }
  }

  discard() {
    if (this.router.url === '/new-presentation') {
      this.editPresentationFormGroup.reset();
    } else {
      this.ngOnInit();
    }
  }

}
