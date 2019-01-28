import { Component, OnInit } from '@angular/core';
import { DataService } from '../services/data.service';
import { PresentationDTO } from '../dto/presentation.dto';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  presentations$: PresentationDTO[];

  constructor(private data: DataService) { }

  ngOnInit() {
    this.data.getPresentations()
    .subscribe(data => this.presentations$ = data);
  }

  openPresentationPage(i: number) {
    console.log('Trying to open presentation ' + this.presentations$[i].title);
    window.open('presentation-page');
  }

}
