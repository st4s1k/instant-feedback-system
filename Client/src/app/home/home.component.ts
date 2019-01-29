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
    private data: PresentationService,
    private router: Router
  ) { }

  ngOnInit() {
    this.data.getPresentations()
      .subscribe(data => this.presentations$ = data);
  }

  openPresentationPage(i: number) {
    console.log('Trying to open presentation ' + this.presentations$[i].id);
    this.router.navigate([`/presentation-page/${this.presentations$[i].id}`]);
  }

}
