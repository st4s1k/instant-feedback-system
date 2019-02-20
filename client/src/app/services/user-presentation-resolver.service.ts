import { Injectable } from '@angular/core';
import { PresentationService } from './presentation.service';
import { Resolve, RouterStateSnapshot, ActivatedRouteSnapshot } from '@angular/router';
import { Observable, of } from 'rxjs';
import { take, mergeMap } from 'rxjs/operators';
import { Presentation } from '../models/presentation.model';
import { PresentationDTO } from '../models/dtos/presentation.dto';

@Injectable({
  providedIn: 'root'
})
export class UserPresentationResolverService implements Resolve<Presentation[]> {

  constructor(private ps: PresentationService) { }
  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Presentation[]> {

    return this.ps.getPresentationsByEmail(localStorage.getItem('email')).pipe(
      take(1),
      mergeMap(presentationDtoList => {
        if (presentationDtoList) {
          // alert('Sucess: Loaded presentations.');
          return of(
            presentationDtoList.map(
              presentationDto => PresentationDTO.toModel(presentationDto)
            )
          );
        } else { // id not found
          return of([]);
        }
      })
    );
  }
}
