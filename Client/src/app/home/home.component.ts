import { Component, OnInit } from '@angular/core';
import { DataService } from '../services/data.service';
import { Presentation } from '../models/presentation.model';
import { markDirty } from '@angular/core/src/render3';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  presentations$: Presentation[];

  constructor(private data: DataService) { }

  ngOnInit() {
    this.data.getPresentations()
    .subscribe(data => this.presentations$ = data);
  }

}
