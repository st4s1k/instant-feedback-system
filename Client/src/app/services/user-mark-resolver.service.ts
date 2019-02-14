import { Injectable } from '@angular/core';
import { MarkService } from './mark.service';
import { ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { take, mergeMap } from 'rxjs/operators';
import { MarkDTO } from '../models/dtos/mark.dto';
import { Mark } from '../models/mark.model';

@Injectable({
  providedIn: 'root'
})
export class UserMarkResolverService {

  constructor(private ms: MarkService, private router: Router) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Mark> | Observable<never> {
    const id = localStorage.getItem('userId');

    return this.ms.getUserMark(id).pipe(
      take(1),
      mergeMap(markDto => {
        if (markDto) {
          return of(MarkDTO.toModel(markDto));
        } else { // id not found
          alert('Error: Could not load a presentation.');
          this.router.navigate(['']);
          return EMPTY;
        }
      })
    );
  }
}
