import { Injectable } from '@angular/core';

import {
  Router, Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';

import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap, take } from 'rxjs/operators';
import { Presentation } from '../models/presentation.model';
import { PresentationService } from './presentation.service';

@Injectable({
  providedIn: 'root'
})
export class PresentationDetailResolverService implements Resolve<Presentation> {

  constructor(private ps: PresentationService, private router: Router) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Presentation> | Observable<never> {
    const id = +route.paramMap.get('id');

    return this.ps.getPresentationById(id).pipe(
      take(1),
      mergeMap(presentations => {
        if (presentations && presentations[0]) {
          return of(presentations[0]);
        } else { // id not found
          this.router.navigate(['']);
          return EMPTY;
        }
      })
    );
  }
}
