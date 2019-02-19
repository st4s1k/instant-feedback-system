import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormArray, Validators, FormControl } from '@angular/forms';
import { PresentationService } from '../services/presentation.service';
import { Presentation } from '../models/presentation.model';
import { first } from 'rxjs/operators';
import { Router, ActivatedRoute } from '@angular/router';
import { NotifierService } from 'angular-notifier';
import { ParticipantService } from '../services/participant.service';
import { Participant } from '../models/participant.model';
import { ParticipantDTO } from '../models/dtos/participant.dto';

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

  participants: Participant[] = [];

  pageTitle: string;

  submitted = false;
  invite_touched = false;

  presentation = new Presentation();
  arrayControl;

  constructor(
    private presentationService: PresentationService,
    private participantService: ParticipantService,
    private router: Router,
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private notifier: NotifierService
  ) {
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

  ngOnInit() {

    if (this.router.url === '/new-presentation') {
      this.pageTitle = 'New presentation';
    } else {
      this.pageTitle = 'Edit presentation';

      this.route.data.subscribe((data: { presentation: Presentation }) => {

        this.presentation = data.presentation;

        this.participantService.getPresentationParticipants(data.presentation.id)
          .subscribe(participantDtoList => {

            this.participants = participantDtoList.map(participantDto =>
              ParticipantDTO.toModel(participantDto));

            this.modelToFormGroup();
          });
      });
    }
  }

  modelToFormGroup() {
    this.title.setValue(this.presentation.title);
    this.description.setValue(this.presentation.description);
    this.startTime.setValue(this.presentation.startTime);
    this.endTime.setValue(this.presentation.endTime);
    this.date.setValue(this.presentation.date);
    this.location.setValue(this.presentation.place);

    if (this.participants) {
      this.participants
        .filter(participant => participant && participant.email)
        .forEach(participant =>
          this.emailInvitations.push(
            this.fb.control(participant.email, [Validators.required, Validators.email])));
    }
  }

  formGroupToModel() {

    return new Presentation(<Presentation>{
      email: localStorage.getItem('email'),
      title: this.title.value,
      description: this.description.value,
      startTime: this.startTime.value,
      endTime: this.endTime.value,
      date: this.date.value,
      place: this.location.value,
    });
  }

  addEmailInvitation_Btn() {
    this.invite_touched = true;

    this.emailInvitations.push(
      this.fb.control('', [Validators.required, Validators.email]));
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

    this.sendNewPresentation(Object.assign(this.presentation, this.formGroupToModel()))
      .pipe(first())
      .subscribe(id => {

        this.notifier.notify('success', 'Presentation saved');

        // console.log(JSON.stringify(id));

        this.presentation.id = '' + id;

        if (this.emailInvitations.value) {

          this.arrayControl = this.emailInvitations.value;

          // console.log(this.emailInvitations.value);

          this.participantService.addParticipants(
            this.emailInvitations.value.map(email => new Participant(<Participant>{
              presentationId: this.presentation.id,
              email: email
            }))

          ).subscribe(() => {
              this.notifier.notify('success', 'Invites are sent');
            },
            error1 => {
              this.notifier.notify('error', 'Invite not sent' + error1);
            });
        }
        this.router.navigate([`/presentation-page/${id}`]);

      }, error => {
        // alert('Error:' + error);
        this.notifier.notify('error', error);
      });
  }

  sendNewPresentation(presentation: Presentation) {
    if (this.router.url === '/new-presentation') {
      return this.presentationService.createPresentation(presentation);
    } else {
      this.notifier.notify('success', 'Success');
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
