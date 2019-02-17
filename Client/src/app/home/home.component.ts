import { Component, OnInit } from '@angular/core';
import { PresentationService } from '../services/presentation.service';
import { Presentation } from '../models/presentation.model';
import { Router, ActivatedRoute } from '@angular/router';
import {FormBuilder, FormControl, Validators} from '@angular/forms';
import { PresentationDTO } from '../models/dtos/presentation.dto';
import { NotifierService } from 'angular-notifier';
import { first } from 'rxjs/internal/operators/first';
import { environment } from 'src/environments/environment.prod';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  presentations: Presentation[];

  message: String;

  pageSize = environment.defaultPageSize;
  currentPage = 1;
  numberOfPages = 0;
  totalElements = 0;

  searchBox: FormControl = this.fb.control('', Validators.required);

  constructor(
    private ps: PresentationService,
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private notifier: NotifierService
  ) { }

  ngOnInit() {
    this.route.data.subscribe((data: { pages: any }) => {
        console.log('paginated presentations: ' + JSON.stringify(data.pages));
        if (data.pages && data.pages.content) {
          this.presentations = data.pages.content
            .map(presentationDto => PresentationDTO.toModel(presentationDto));
          this.numberOfPages = data.pages.totalPages;
          this.totalElements = data.pages.totalElements;
        }
      }
    );
  }

  requestAllPresentations() {
    this.ps.getPresentationsByPage(0).subscribe(presentationDtoList =>
      this.presentations = presentationDtoList
        .map(presentationDto => PresentationDTO.toModel(presentationDto)));
  }

  searchAll() {
    if (this.searchBox.invalid) {
      this.requestAllPresentations();
      return;
    }

    this.ps.getPresentationsByTitleOrEmailKeyword(this.searchBox.value)
      .subscribe(presentationDtoList => {
          if (presentationDtoList) {
            this.presentations = presentationDtoList
              .map(presentationDto => PresentationDTO.toModel(presentationDto));
          } else {
            this.presentations = [];
          }
        }
      );
  }

  searchByEmail() {
    this.ps.getPresentationsByEmailKeyword(this.searchBox.value)
      .subscribe(presentationDtoList => {
          if (presentationDtoList) {
            this.presentations = presentationDtoList
              .map(presentationDto => PresentationDTO.toModel(presentationDto));
          } else {
            this.presentations = [];
          }
        }
      );
  }

  searchByTitle() {
    this.ps.getPresentationsByTitle(this.searchBox.value)
      .subscribe(presentationDtoList => {
          if (presentationDtoList) {
            this.presentations = presentationDtoList
              .map(presentationDto => PresentationDTO.toModel(presentationDto));
          } else {
            this.presentations = [];
          }
        }
      );
  }

  openPresentationPage(i: number) {
    this.router.navigate([`/presentation-page/${this.presentations[i].id}`])
      .then().catch(() =>
      this.notifier.notify('error', 'Could not load presentation!'));
  }

  openPage() {
    this.ps.getPresentationsByPage(this.currentPage - 1)
      .pipe(first()).subscribe(pages => {
        console.log('openPage: ' + JSON.stringify(pages));
        if (pages && pages.content) {
          this.presentations = pages.content
            .map(presentationDto => PresentationDTO.toModel(presentationDto));
          this.numberOfPages = pages.totalPages;
          this.totalElements = pages.totalElements;
        }
      }
    );
  }
}
