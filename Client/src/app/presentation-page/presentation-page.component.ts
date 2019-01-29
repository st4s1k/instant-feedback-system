import { Component, OnInit } from '@angular/core';

import { PresentationService } from '../services/presentation.service';
import { PresentationDTO } from '../dto/presentation.dto';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-presentation-page',
  templateUrl: './presentation-page.component.html',
  styleUrls: ['./presentation-page.component.scss']
})
export class PresentationPageComponent implements OnInit {

  presentation: PresentationDTO;

  constructor(
    private presentationService: PresentationService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.getPresentation();
  }

  getPresentation(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.presentationService.getPresentationById(id)
      .subscribe(presentation => this.presentation = presentation);
  }

}
