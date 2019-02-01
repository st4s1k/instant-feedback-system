import { Component, OnInit } from '@angular/core';

import { PresentationService } from '../services/presentation.service';
import { Presentation } from '../models/presentation.model';
import { Router, ActivatedRoute } from '@angular/router';
import { FormControl, Validators, FormBuilder, FormGroup } from '@angular/forms';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-presentation-page',
  templateUrl: './presentation-page.component.html',
  styleUrls: ['./presentation-page.component.scss']
})
export class PresentationPageComponent implements OnInit {

  feedbackFormGroup: FormGroup;

  feedbackBox: FormControl = this.fb.control('', [Validators.required]);

  presentation: Presentation;

  currentRate = 1;

  submittedRate = false;

  feedbackAdded = false;

  avgMark = 0;

  constructor(
    private ps: PresentationService,
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder
  ) { }

  ngOnInit() {

    this.route.data.subscribe((data: { presentation: Presentation }) => {
      Object.assign(this.presentation, data.presentation);

      console.log('presentation page: ' + JSON.stringify(this.presentation));

      this.avgMark = this.ps.avgMark(data.presentation.marks);

      console.log('Average mark: ' + this.avgMark);
    });

    this.feedbackFormGroup = this.fb.group({
      feedbackBox: ''
    });

  }

  submitRate(rate: number) {

    this.submittedRate = true;

    if (!this.presentation.marks) {
      this.avgMark = rate;
    } else {
      this.presentation.marks.push({
        userId: +localStorage.getItem('userId'),
        email: localStorage.getItem('email'),
        mark: +rate.toFixed(2)
      });
    }

    this.ps.updatePresentation(this.presentation).pipe(first()).subscribe(
      data => {
        alert('Succes!');
      },
      error => {
        alert(error);
      }
    );

  }

  leaveFeedback(type: string) {

    alert(this.feedbackBox.value);

    this.presentation.feedback.push({
      email: localStorage.getItem('email'),
      message: this.feedbackBox.value,
      type: '',
      anonimity: false
    });

    this.ps.updatePresentation(this.presentation).pipe(first())
      .subscribe(
        data => {
          alert('Succes!:' + JSON.stringify(data));
          this.router.navigate(['']);
        },
        error => {
          alert(error);
        }
      );

    this.feedbackFormGroup.reset();
  }

}
