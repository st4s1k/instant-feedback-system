import { Injectable } from '@angular/core';
import { PresentationService } from './presentation.service';
import { Resolve, Router, RouterStateSnapshot, ActivatedRouteSnapshot } from '@angular/router';
import { Observable, EMPTY, of } from 'rxjs';
import { take, mergeMap } from 'rxjs/operators';
import { PresentationDTO } from '../dto/presentation.dto';

@Injectable({
  providedIn: 'root'
})
export class UserPresentationResolverService implements Resolve<PresentationDTO[]> {

  constructor(private ps: PresentationService, private router: Router) { }
  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<PresentationDTO[]> | Observable<never> {

    // const id = +route.paramMap.get('id');
    const id = +JSON.parse(localStorage.getItem('userId'));

    return this.ps.getPresentationsByUser(id).pipe(
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
