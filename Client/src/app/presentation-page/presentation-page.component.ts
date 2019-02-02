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

  feedbackBox: FormControl = this.fb.control('');

  feedbackFormGroup: FormGroup = this.fb.group({ feedbackBox: this.feedbackBox });

  presentation: Presentation;

  currentRate = 1;

  submittedRate = false;

  feedbackAdded = false;


  constructor(
    private ps: PresentationService,
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder
  ) { }

  ngOnInit() {

    this.route.data.subscribe((data: { presentation: Presentation }) => {
      console.log('data.presentation: ' + JSON.stringify(data.presentation));

      this.presentation = new Presentation(data.presentation);

      console.log('this.presentation: ' + JSON.stringify(this.presentation));
    });

  }

  submitRate(rate: number) {

    this.submittedRate = true;

    if (!this.presentation.marks) {
      this.presentation.marks = [];
    }

    this.presentation.marks.push({
      userId: +localStorage.getItem('userId'),
      email: localStorage.getItem('email'),
      mark: +rate.toFixed(2)
    });

    this.ps.updatePresentation(this.presentation).pipe(first()).subscribe(
      data => {
        // alert('Succes!');
        // console.log('Succes!: ' + JSON.stringify(data));
      },
      error => {
        alert('Error!');
        console.log('Error!: ' + JSON.stringify(error));
      }
    );

  }

  leaveFeedback(type: string) {

    if (!this.presentation.feedback) {
      this.presentation.feedback = [];
    }

    this.presentation.feedback.push({
      email: localStorage.getItem('email'),
      message: this.feedbackBox.value,
      type: '',
      anonimity: false
    });

    this.ps.updatePresentation(this.presentation).pipe(first())
      .subscribe(
        data => {
          // alert('Succes!:' + JSON.stringify(data));
        },
        error => {
          alert('Error!: ' + error);
        }
      );

    this.feedbackFormGroup.reset();
  }

}
