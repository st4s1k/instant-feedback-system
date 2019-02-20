import { Injectable } from '@angular/core';

import { Resolve, RouterStateSnapshot, ActivatedRouteSnapshot } from '@angular/router';
import { Observable, of } from 'rxjs';
import { mergeMap, take } from 'rxjs/operators';
import { User } from '../models/user.model';
import { UserService } from './user.service';
import { UserDTO } from '../models/dtos/user.dto';

@Injectable({
  providedIn: 'root'
})
export class UserListDetailResolverService implements Resolve<User[]> {

  constructor(private userService: UserService) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<User[]> {
    return this.userService.getAllUsers().pipe(
      take(1),
      mergeMap(userDtoList => {
        if (userDtoList) {
          return of(
            userDtoList.map(
              userDto => UserDTO.toModel(userDto)
            )
          );
        } else { // id not found
          return of([]);
        }
      })
    );
  }
}
