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
  searchFilterType = 0;

  searchBox: FormControl = this.fb.control('', Validators.required);

  constructor(
    private ps: PresentationService,
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private notifier: NotifierService
  ) { }

  ngOnInit() {
    this.route.data.subscribe((data: { page: any }) => this.processPage(data.page));
  }

  processPage(page: any) {
    console.log('processing page:' + JSON.stringify(page));
    if (page && page.content) {
      this.presentations = page.content
        .map(presentationDto => PresentationDTO.toModel(presentationDto));
      this.numberOfPages = page.totalPages;
      this.totalElements = page.totalElements;
    } else  {
      this.presentations = [];
      this.numberOfPages = 0;
      this.totalElements = 0;
    }
  }

  requestAllPresentations() {
    this.ps.getPresentationsByPage(0)
      .subscribe(page => this.processPage(page));
  }

  searchByEmail() {

    this.searchFilterType = 1;

    this.ps.getPresentationsByEmailKeyword(this.searchBox.value, 0)
      .subscribe(page => this.processPage(page));
  }

  searchByTitle() {

    this.searchFilterType = 2;

    this.ps.getPresentationsByTitleKeyword(this.searchBox.value, 0)
      .subscribe(page => this.processPage(page));
  }

  searchAll() {

    if (this.searchBox.invalid) {
      this.requestAllPresentations();
      this.searchFilterType = 0;
      return;
    }

    this.searchFilterType = 3;

    this.ps.getPresentationsByTitleOrEmailKeyword(this.searchBox.value, 0)
      .subscribe(page => this.processPage(page));
  }

  openPresentationPage(i: number) {
    this.router.navigate([`/presentation-page/${this.presentations[i].id}`])
      .then().catch(() =>
      this.notifier.notify('error', 'Could not load presentation!'));
  }

  searchFilter(page: number) {
    switch (this.searchFilterType) {
      case 0: return this.ps.getPresentationsByPage(page);
      case 1: return this.ps.getPresentationsByEmailKeyword(this.searchBox.value, page);
      case 2: return this.ps.getPresentationsByTitleKeyword(this.searchBox.value, page);
      case 3: return this.ps.getPresentationsByTitleOrEmailKeyword(this.searchBox.value, page);
    }
  }

  openPage() {
    this.searchFilter(this.currentPage - 1)
      .pipe(first()).subscribe(page => this.processPage(page));
  }
}
