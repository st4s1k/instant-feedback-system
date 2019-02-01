import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GlobalServUserService {
  constructor() { }
  private navEmailSource = new Subject<string>();
  navEmail = this.navEmailSource.asObservable();
  private NavUserIdSource = new Subject<number>();
  navUser = this.NavUserIdSource.asObservable();
  private NavAuthenticatedSource = new Subject<number>();
  NavAuthenticated = this.NavAuthenticatedSource.asObservable();
  setNavEmail(email: string) {
    this.navEmailSource.next(email);
  }
  setNavUserId(id: number) {
    this.NavUserIdSource.next(id);
  }
  setNavAuthenticated(auth: number) {
    this.NavAuthenticatedSource.next(auth);
  }

}
