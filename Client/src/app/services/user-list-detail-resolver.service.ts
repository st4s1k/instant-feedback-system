import { Injectable } from '@angular/core';

import { Router, Resolve, RouterStateSnapshot, ActivatedRouteSnapshot } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap, take } from 'rxjs/operators';
import { User } from '../models/user.model';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class UserListDetailResolverService implements Resolve<User[]> {

  constructor(private userService: UserService) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<User[]> | Observable<never> {
    return this.userService.getAllUsers().pipe(
      take(1),
      mergeMap(users => {
        if (users) {
          return of(users);
        } else { // id not found
          // this.router.navigate(['/home']);
          alert('Error: Cannot load Users.');
          return EMPTY;
        }
      })
    );
  }
}
