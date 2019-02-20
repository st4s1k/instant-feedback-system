import { Injectable } from '@angular/core';

import {
  Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';

import { Observable, of } from 'rxjs';
import { mergeMap, take } from 'rxjs/operators';
import { PresentationService } from './presentation.service';

@Injectable({
  providedIn: 'root'
})
export class PresentationListPageDetailResolverService implements Resolve<any> {

  constructor(private ps: PresentationService) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<any> {

    const email = route.paramMap.get('email');

    if (email) {
      return this.ps.getPresentationsByEmail(email).pipe(
        take(1),
        mergeMap(page => {
          return of(page);
        })
      );
    }

    return this.ps.getPresentationsByPage(0).pipe(
      take(1),
      mergeMap(page => {
        return of(page);
      })
    );
  }
}
