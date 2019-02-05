import { Component, OnInit } from '@angular/core';
import { PresentationService } from '../services/presentation.service';
import { Presentation } from '../models/presentation.model';
import { Router, ActivatedRoute } from '@angular/router';
import { PresentationDTO } from '../models/dtos/presentation.dto';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  presentations: Presentation[];

  constructor(
    private ps: PresentationService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {

    // this.ps.getPresentations().subscribe(Presentations => this.presentations);
    this.route.data.subscribe((data: { presentationList: Presentation[] }) => {
      this.presentations = data.presentationList;
      // console.log(JSON.stringify(this.presentations));
    });
  }

  openPresentationPage(i: number) {
    // console.log('Trying to open presentation ' + this.presentations[i].id);
    this.router.navigate([`/presentation-page/${this.presentations[i].id}`]);
  }

}
