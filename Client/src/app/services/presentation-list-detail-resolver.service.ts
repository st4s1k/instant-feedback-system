import { Injectable } from '@angular/core';

import {
  Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';

import { Observable, of } from 'rxjs';
import { mergeMap, take } from 'rxjs/operators';
import { PresentationService } from './presentation.service';
import { Presentation } from '../models/presentation.model';
import { PresentationDTO } from '../models/dtos/presentation.dto';

@Injectable({
  providedIn: 'root'
})
export class PresentationListDetailResolverService implements Resolve<Presentation[]> {

  constructor(private ps: PresentationService) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Presentation[]> | Observable<never> {

    return this.ps.getPresentations().pipe(
      take(1),
      mergeMap(presentationDtoList => {
        if (presentationDtoList) {
          return of(presentationDtoList
            .map(presentationDto =>
              PresentationDTO.toModel(presentationDto)));
        } else {
          return of([]);
        }
      })
    );
  }
}
