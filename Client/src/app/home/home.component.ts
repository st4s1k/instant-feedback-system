import { Component, OnInit } from '@angular/core';
import { PresentationService } from '../services/presentation.service';
import { Presentation } from '../models/presentation.model';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';
import { PresentationDTO } from '../models/dtos/presentation.dto';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  presentations: Presentation[];

  searchBox: FormControl = this.fb.control('', Validators.required);

  constructor(
    private ps: PresentationService,
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder
  ) { }

  ngOnInit() {
    this.route.data.subscribe((data: { presentations: Presentation[] }) => {
      this.presentations = data.presentations;
      // console.log(JSON.stringify(this.presentations));
    });
  }

  searchAll() {

    if (this.searchBox.invalid) {
      alert('Bad boy! searchByEmail()');
      return;
    }

    this.presentations = [];

    this.ps.getPresentationsByEmail(this.searchBox.value).subscribe(
      presentationDtoList => this.presentations = this.presentations.concat(
        presentationDtoList.map(
          presentationDto => PresentationDTO.toModel(presentationDto)
        )
      )
    );

    this.ps.getPresentationsByTitle(this.searchBox.value).subscribe(
      presentationDtoList => this.presentations = this.presentations.concat(
        presentationDtoList.map(
          presentationDto => PresentationDTO.toModel(presentationDto)
        )
      )
    );

  }

  searchByEmail() {

    if (this.searchBox.invalid) {
      alert('Bad boy! searchByEmail()');
      return;
    }

    this.ps.getPresentationsByEmail(this.searchBox.value).subscribe(
      presentationDtoList => this.presentations = presentationDtoList.map(
        presentationDto => PresentationDTO.toModel(presentationDto)
      )
    );
  }

  searchByTitle() {

    if (this.searchBox.invalid) {
      alert('Bad boy! searchByTitle()');
      return;
    }

    this.ps.getPresentationsByTitle(this.searchBox.value).subscribe(
      presentationDtoList => this.presentations = presentationDtoList.map(
        presentationDto => PresentationDTO.toModel(presentationDto)
      )
    );
  }

  openPresentationPage(i: number) {
    // console.log('Trying to open presentation ' + this.presentations[i].id);
    this.router.navigate([`/presentation-page/${this.presentations[i].id}`]);
  }

}
