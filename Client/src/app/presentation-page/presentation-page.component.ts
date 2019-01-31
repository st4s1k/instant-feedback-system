import { Component, OnInit } from '@angular/core';

import { PresentationService } from '../services/presentation.service';
import { PresentationDTO } from '../dto/presentation.dto';
import { Router, ActivatedRoute } from '@angular/router';
import { FormControl, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-presentation-page',
  templateUrl: './presentation-page.component.html',
  styleUrls: ['./presentation-page.component.scss']
})
export class PresentationPageComponent implements OnInit {

  presentation: PresentationDTO;

  currentRate = 1;

  submittedRate = false;

  constructor(
    private ps: PresentationService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.route.data.subscribe((data: { presentation: PresentationDTO }) => {
      this.presentation = data.presentation;
    });

    // IF (user already voted) submittedRate = true;
    // ELSE submittedRate = false;
  }

  submitRate(rate: number) {

    this.submittedRate = true;

    this.presentation.mark = '' + Number((+this.presentation.mark + rate) / 2).toFixed(2);

    this.ps.updatePresentation(this.presentation).pipe(first()).subscribe(
      data => {
        alert('Succes!');
      },
      error => {
        alert(JSON.stringify(error));
      }
    );

  }
}
