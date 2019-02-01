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

  commentFormGroup: FormGroup;

  commentBox: FormControl = this.fb.control('', [Validators.required]);

  presentation: PresentationDTO;

  currentRate = 1;

  submittedRate = false;

  commentAdded = false;

  constructor(
    private presentationService: PresentationService,
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder
  ) { }

  ngOnInit() {
    this.route.data.subscribe((data: { presentation: PresentationDTO }) => {
      this.presentation = data.presentation;
    });

    this.commentFormGroup = this.fb.group({
      commentBox: ''
    });

    // IF (user already voted) submittedRate = true;
    // ELSE submittedRate = false;
  }

  submitRate(rate: number) {

    this.submittedRate = true;

    if (!this.presentation.mark || Number.isNaN(+this.presentation.mark)) {
      this.presentation.mark = '' + rate;
    } else {
      this.presentation.mark = '' + Number((+this.presentation.mark + rate) / 2).toFixed(2);
    }


    this.presentationService.updatePresentation(this.presentation).pipe(first()).subscribe(
      data => {
        // alert('Succes!');
      },
      error => {
        alert(error);
      }
    );

  }

  addComment() {

    this.commentFormGroup.reset();

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
