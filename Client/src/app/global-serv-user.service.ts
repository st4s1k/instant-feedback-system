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
  private NavAuthenticatedSource = new Subject<boolean>();
  NavAuthenticated = this.NavAuthenticatedSource.asObservable();
  private SessionIDSource = new Subject<number>();
  SessionID = this.SessionIDSource.asObservable();
  setNavEmail(email: string) {
    this.navEmailSource.next(email);
  }
  setNavUserId(id: number) {
    this.NavUserIdSource.next(id);
  }
  setSessionID(SessID: number) {
    this.SessionIDSource.next(SessID);
  }
  setNavAuthenticated(auth: boolean) {
    this.NavAuthenticatedSource.next(auth);
  }

}
