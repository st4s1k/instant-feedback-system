import { Component, OnInit } from '@angular/core';

import { PresentationService } from '../services/presentation.service';
import { PresentationDTO } from '../dto/presentation.dto';
import { Router, ActivatedRoute } from '@angular/router';
import { FormControl, Validators, FormBuilder, FormGroup } from '@angular/forms';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-presentation-page',
  templateUrl: './presentation-page.component.html',
  styleUrls: ['./presentation-page.component.scss']
})
export class PresentationPageComponent implements OnInit {

  messageFormGroup: FormGroup;

  messageBox: FormControl = this.fb.control('', [Validators.required]);

  presentation: PresentationDTO;

  currentRate = 1;

  submittedRate = false;

  messageAdded = false;

  avgMark = 0;

  constructor(
    private presentationService: PresentationService,
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder
  ) { }

  ngOnInit() {

    this.route.data.subscribe((data: { presentation: PresentationDTO }) => {
      this.presentation = data.presentation;

      let sum = 0;

      data.presentation.marks.forEach(element => {
        sum += element.mark;
      });

      this.avgMark = sum / this.presentation.marks.length;
    });

    this.messageFormGroup = this.fb.group({
      messageBox: ''
    });

  }

  submitRate(rate: number) {

    this.submittedRate = true;

    if (!this.presentation.marks) {
      this.avgMark = rate;
    } else {
      this.presentation.marks.push({
        email: localStorage.getItem('email'),
        mark: +rate.toFixed(2)
      });
    }

    this.presentationService.updatePresentation(this.presentation).pipe(first()).subscribe(
      data => {
        alert('Succes!');
      },
      error => {
        alert(error);
      }
    );

  }

  addComment() {

    alert(this.messageBox.value);

    this.presentation.messages.push({
      email: localStorage.getItem('email'),
      message: this.messageBox.value,
      type: '',
      anon: false
    });

    this.presentationService.updatePresentation(this.presentation).pipe(first())
      .subscribe(
        data => {
          alert('Succes!:' + JSON.stringify(data));
          this.router.navigate(['']);
        },
        error => {
          alert(error);
        }
      );

    this.messageFormGroup.reset();
  }

}
