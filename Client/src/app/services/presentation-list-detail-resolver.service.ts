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
export class PresentationListDetailResolverService implements Resolve<any> {

  constructor(private ps: PresentationService) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<any> {

    return this.ps.getPresentationsByPage(0).pipe(
      take(1),
      mergeMap(page => {
          return of(page);
      })
    );
  }
}
