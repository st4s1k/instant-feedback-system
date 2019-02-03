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

  type: FormControl = this.fb.control('feedback');
  anonymity: FormControl = this.fb.control(false);
  feedbackBox: FormControl = this.fb.control('');

  feedbackFormGroup: FormGroup = this.fb.group({
    feedbackBox: this.feedbackBox,
    type: this.type,
    anonymity: this.anonymity
  });

  presentation: Presentation;
  currentRate = 0;
  submittedRate = false;
  feedbackAdded = false;
  canVote = false;
  myMark = 0;
  myEmail: string;
  avgMark = 0;
  ratingsCount = 0;
  editingMessage = -1;

  constructor(
    private ps: PresentationService,
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder
  ) { }

  ngOnInit() {

    this.myEmail = localStorage.getItem('email');

    this.route.data.subscribe((data: { presentation: Presentation }) => {
      this.presentation = new Presentation(data.presentation);

      this.avgMark = +this.ps.getAvgMark(this.presentation.marks);

      this.ratingsCount = this.presentation.marks.length;

      const markObject = this.presentation.marks
        .find(mark => mark.email === this.myEmail);

      if (!markObject) {
        this.canVote = true;
      } else {
        this.myMark = markObject.mark;
        this.currentRate = markObject.mark;
      }
    });
  }

  submitRate(rate: number) {

    this.submittedRate = true;
    this.canVote = false;
    this.myMark = rate;
    this.currentRate = rate;

    if (!this.presentation.marks) {
      this.presentation.marks = [];
    }

    this.presentation.marks.push({
      userId: +localStorage.getItem('userId'),
      email: localStorage.getItem('email'),
      mark: +rate.toFixed(2)
    });

    this.avgMark = +this.ps.getAvgMark(this.presentation.marks);

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

  editMessage(i: number) {
    // alert('editing message ' + i);

    this.editingMessage = i;

    this.feedbackBox.setValue(this.presentation.feedback[i].message);
    this.anonymity.setValue(this.presentation.feedback[i].anonymity);
    this.type.setValue(this.presentation.feedback[i].type);
  }

  leaveFeedback(type: string) {

    const currentFeedback = {
      email: (this.anonymity.value ? 'anonymous' : localStorage.getItem('email')),
      message: this.feedbackBox.value,
      type: this.type.value,
      anonymity: this.anonymity.value
    };

    if (this.editingMessage >= 0) {
      this.presentation.feedback[this.editingMessage] = currentFeedback;
      this.editingMessage = -1;
    } else {
      if (!this.presentation.feedback) {
        this.presentation.feedback = [];
      }
      this.presentation.feedback.push(currentFeedback);
    }

    this.ps.updatePresentation(this.presentation).pipe(first())
      .subscribe(
        data => {
          // alert('Succes!:' + JSON.stringify(data));
        },
        error => {
          alert('Error!: ' + error);
        }
      );

    this.feedbackFormGroup.reset({
      feedbackBox: '',
      msgType: 'feedback',
      anonymity: false
    });
  }

}
