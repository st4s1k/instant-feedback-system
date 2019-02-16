import { Injectable } from '@angular/core';
import { User } from '../models/user.model';
import { UserService } from './user.service';
import { Router, Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { take, mergeMap } from 'rxjs/operators';
import { UserDTO } from '../models/dtos/user.dto';

@Injectable({
  providedIn: 'root'
})
export class UserDetailResolverService implements Resolve<User> {

  constructor(private userServ: UserService, private router: Router) { }
  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<User> | Observable<never> {

    const id = localStorage.getItem('userId');
    return this.userServ.getUserById(id).pipe(
      take(1),
      mergeMap(user => {
        if (user) {
          return of(UserDTO.toModel(user));
        } else { // id not found
          this.router.navigate(['/sign-in']);
          return EMPTY;
        }
      })
    );
  }

}
