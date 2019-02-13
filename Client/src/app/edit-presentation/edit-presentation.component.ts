import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormArray, Validators, FormControl } from '@angular/forms';
import { PresentationService } from '../services/presentation.service';
import { Presentation } from '../models/presentation.model';
import { first } from 'rxjs/operators';
import { Router, ActivatedRoute } from '@angular/router';
import { GlobalServUserService } from '../services/global-serv-user.service';
import { User } from '../models/user.model';

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

  // presentation: Presentation;

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
    } else {
      this.pageTitle = 'Edit presentation';

      this.route.data.subscribe((data: { presentation: Presentation }) => {
        this.modelToFormGroup(data.presentation);
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

  modelToFormGroup = function (presentation: Presentation) {
    this.title.setValue(presentation.title);
    this.description.setValue(presentation.description);
    this.startTime.setValue(presentation.startDate.split('T')[1]);
    this.endTime.setValue(presentation.endDate.split('T')[1]);
    this.date.setValue(presentation.startDate.split('T')[0]);
    this.location.setValue(presentation.place);
    this.emailInvitations = this.fb.array([]);

    for (const participant of presentation.participants) {
      this.emailInvitations.push(this.fb.control(
        participant.email, [Validators.required, Validators.email]
      ));
    }
  };

  formGroupToModel = function () {
    const presentation = new Presentation();

    presentation.email = localStorage.getItem('email');
    presentation.title = this.title.value;
    presentation.description = this.description.value;
    presentation.startDate = this.date.value + 'T' + this.startTime.value;
    presentation.place = this.location.value;
    presentation.endDate = this.date.value + 'T' + this.endTime.value;
    presentation.participants = [];

    for (const emailInvitation of this.emailInvitations) {
      presentation.participants.push(new User({
        email: emailInvitation
      }));
    }

    return presentation;
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

    const presentation = this.formGroupToModel();

    this.sendNewPresentation(presentation).pipe(first())
      .subscribe(data => {
        console.log(JSON.stringify(data));
        this.router.navigate([`/presentation-page/${data.id}`]);
      }, error => {
        alert(error);
      });
  }

  sendNewPresentation(presentation: Presentation) {
    if (this.router.url === '/new-presentation') {
      return this.presentationService.createPresentation(presentation);
    } else {
      return this.presentationService.updatePresentation(presentation);
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
