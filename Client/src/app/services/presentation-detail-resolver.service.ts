import { Injectable } from '@angular/core';

import {
  Router, Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';

import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap, take } from 'rxjs/operators';
import { PresentationService } from './presentation.service';
import { PresentationDTO } from '../models/dtos/presentation.dto';
import { Presentation } from '../models/presentation.model';

@Injectable({
  providedIn: 'root'
})
export class PresentationDetailResolverService implements Resolve<Presentation> {

  constructor(private ps: PresentationService, private router: Router) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Presentation> | Observable<never> {
    const id = route.paramMap.get('id');

    return this.ps.getPresentationById(id).pipe(
      take(1),
      mergeMap(presentationDto => {
        if (presentationDto) {
          return of(PresentationDTO.toModel(presentationDto));
        } else { // id not found
          this.router.navigate(['']);
          return EMPTY;
        }
      })
    );
  }
}
