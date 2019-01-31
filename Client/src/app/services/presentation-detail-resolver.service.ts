import { Injectable } from '@angular/core';

import {
  Router, Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap, take } from 'rxjs/operators';
import { PresentationDTO } from '../dto/presentation.dto';
import { PresentationService } from './presentation.service';

@Injectable({
  providedIn: 'root'
})
export class PresentationDetailResolverService implements Resolve<PresentationDTO> {

  constructor(private ps: PresentationService, private router: Router) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<PresentationDTO> | Observable<never> {
    const id = +route.paramMap.get('id');

    return this.ps.getPresentationById(id).pipe(
      take(1),
      mergeMap(presentation => {
        if (presentation) {
          return of(presentation);
        } else { // id not found
          this.router.navigate(['/home']);
          return EMPTY;
        }
      })
    );
  }
}
