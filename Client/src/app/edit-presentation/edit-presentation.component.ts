import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormArray, Validators, FormControl } from '@angular/forms';
import { PresentationService } from '../services/presentation.service';
import { PresentationDTO } from '../dto/presentation.dto';
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
  time: FormControl = this.fb.control('', [Validators.required]);
  duration: FormControl = this.fb.control('', [Validators.required]);
  date: FormControl = this.fb.control('', [Validators.required]);
  location: FormControl = this.fb.control('', [Validators.required]);
  emailInvitations: FormArray = this.fb.array([]);

  presentation: PresentationDTO;

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
      this.presentation = new PresentationDTO();
    } else {
      this.pageTitle = 'Edit presentation';

      this.route.data.subscribe((data: { presentation: PresentationDTO }) => {

        this.presentation = data.presentation;

        this.title.setValue(data.presentation.title);
        this.description.setValue(data.presentation.description);
        this.time.setValue(data.presentation.time);
        this.duration.setValue(data.presentation.duration);
        this.date.setValue(data.presentation.date);
        this.location.setValue(data.presentation.location);

        for (const invitation of data.presentation.emailInvitations) {
          this.emailInvitations.push(this.fb.control(
            invitation, [Validators.required, Validators.email]
          ));
        }
      });
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

  save() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.editPresentationFormGroup.invalid) {
      return;
    }

    Object.assign(this.presentation, this.editPresentationFormGroup.value);

    if (this.router.url === '/new-presentation') {
      this.presentationService.createPresentation(this.presentation).pipe(first())
        .subscribe(
          data => {
            alert('Succes!:' + data);
            this.router.navigate(['/home']);
          },
          error => {
            alert(error);
          }
        );
    } else {
      this.presentationService.updatePresentation(this.presentation).pipe(first())
        .subscribe(
          data => {
            alert('Succes!:' + data);
            this.router.navigate(['/home']);
          },
          error => {
            alert(error);
          }
        );
    }
  }

  discard() {
    if (this.editPresentationFormGroup) {
      this.editPresentationFormGroup.reset();
    }
  }

}
