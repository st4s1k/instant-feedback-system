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
export class PresentationListDetailResolverService implements Resolve<PresentationDTO[]> {

  constructor(private ps: PresentationService, private router: Router) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<PresentationDTO[]> | Observable<never> {

    return this.ps.getPresentations().pipe(
      take(1),
      mergeMap(presentations => {
        if (presentations) {
          // alert('Sucess: Loaded presentations.');
          return of(presentations);
        } else { // id not found
          // this.router.navigate(['/home']);
          alert('Error: Cannot load presentations.');
          return EMPTY;
        }
      })
    );
  }
}
