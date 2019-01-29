import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormArray, Validators, FormControl } from '@angular/forms';
import { PresentationService } from '../services/presentation.service';
import { PresentationDTO } from '../dto/presentation.dto';
import { first } from 'rxjs/operators';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-edit-presentation',
  templateUrl: './edit-presentation.component.html',
  styleUrls: ['./edit-presentation.component.scss']
})
export class EditPresentationComponent implements OnInit {

  editPresentationFormGroup: FormGroup;

  title: FormControl = new FormControl('Template Title', [Validators.required]);
  description: FormControl = new FormControl('Template Description', [Validators.required]);
  time: FormControl = new FormControl('12:12', [Validators.required]);
  duration: FormControl = new FormControl('12:12', [Validators.required]);
  date: FormControl = new FormControl('1996-12-12', [Validators.required]);
  location: FormControl = new FormControl('Template location', [Validators.required]);
  emailInvitations: FormArray = this.fb.array([]);

  presentation: PresentationDTO;

  submitted = false;
  invite_touched = false;

  constructor(
    private presentationService: PresentationService,
    private router: Router,
    private fb: FormBuilder,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {

    if (this.router.url === '/new-presentation') {

      this.title = new FormControl('Template Title', [Validators.required]);
      this.description = new FormControl('Template Description', [Validators.required]);
      this.time = new FormControl('12:12', [Validators.required]);
      this.duration = new FormControl('12:12', [Validators.required]);
      this.date = new FormControl('1996-12-12', [Validators.required]);
      this.location = new FormControl('Template location', [Validators.required]);
      this.emailInvitations = this.fb.array([]);

      this.emailInvitations.push(this.fb.control(
        'test@email.com', [Validators.required, Validators.email]
      )); // ONLY FOR TESTING

    } else {

      this.getPresentation();

      this.title = new FormControl(this.presentation.title, [Validators.required]);
      this.description = new FormControl(this.presentation.description, [Validators.required]);
      this.time = new FormControl(this.presentation.time, [Validators.required]);
      this.duration = new FormControl(this.presentation.duration, [Validators.required]);
      this.date = new FormControl(this.presentation.date, [Validators.required]);
      this.location = new FormControl(this.presentation.location, [Validators.required]);
      this.emailInvitations = this.fb.array([]);
    }

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

    this.emailInvitations.push(this.fb.control(
      '', [Validators.required, Validators.email]
    ));
  }

  deleteEmailInvitation(i) {
    this.emailInvitations.removeAt(i);
  }

  getPresentation(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.presentationService.getPresentationById(id)
      .subscribe(presentation => this.presentation = presentation);
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
      date: this.date.value,
      location: this.location.value,
      emailInvitations: this.emailInvitations.value
    });

    this.presentationService.createPresentation(this.presentation).pipe(first())
      .subscribe(
        data => {
          console.log('Succes Registration');
          // alert('Success');
          this.router.navigate(['/home']);
        },
        error => {
          alert(error);
          console.log(error);
        }
      );
  }

  discard() {
    if (this.editPresentationFormGroup) {
      this.editPresentationFormGroup.reset();
    }
  }

}
