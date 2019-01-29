import { Component, OnInit } from '@angular/core';
import { PresentationService } from '../services/presentation.service';
import { PresentationDTO } from '../dto/presentation.dto';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  presentations$: PresentationDTO[];

  constructor(
    private presentationService: PresentationService,
    private router: Router
  ) { }

  ngOnInit() {
    this.presentationService.getPresentations()
      .subscribe(presentationService => this.presentations$ = presentationService);
  }

  openPresentationPage(i: number) {
    console.log('Trying to open presentation ' + this.presentations$[i].id);
    this.router.navigate([`/presentation-page/${this.presentations$[i].id}`]);
  }

}
