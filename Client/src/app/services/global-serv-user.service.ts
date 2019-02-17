import { Injectable } from '@angular/core';
import { Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GlobalServUserService {

  constructor() { }

  private navEmailSource = new Subject<string>();
  navEmail = this.navEmailSource.asObservable();
  private NavUserIdSource = new Subject<string>();
  navUser = this.NavUserIdSource.asObservable();
  private NavAuthenticatedSource = new Subject<boolean>();
  NavAuthenticated = this.NavAuthenticatedSource.asObservable();
  private SessionIDSource = new Subject<string>();
  SessionID = this.SessionIDSource.asObservable();
  private UserRoleSource = new Subject<string>();
  UserRole = this.UserRoleSource.asObservable();
  setNavEmail(email: string) {
    this.navEmailSource.next(email);
  }
  setNavUserId(id: string) {
    this.NavUserIdSource.next(id);
  }
  setSessionID(SessID: string) {
    this.SessionIDSource.next(SessID);
  }
  setNavAuthenticated(auth: boolean) {
    this.NavAuthenticatedSource.next(auth);
  }
  setUserRole(role: string) {
    this.UserRoleSource.next(role);
  }

}
