import { Injectable } from '@angular/core';
import { HttpEvent,
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
  HttpErrorResponse} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AuthenticationService } from '../services/authentication.service';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  constructor(private authenticationService: AuthenticationService) { }
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request)
      .pipe(
        catchError( (error: HttpErrorResponse) => {
          let errMsg = '';
          // Client Side Error
          if (error.error instanceof ErrorEvent) {
            errMsg = `Error: ${error.error.message}`;
          } else {  // Server Side Error
            if (error.status === 401) {
              // auto logout if 401 response returned from api
              this.authenticationService.logout();
              errMsg = `Error Code: ${error.status},  Message: Email or password are incorrect`;
            } else {
              errMsg = `Error Code: ${error.status},  Message: ${error.error}`;
            }
          }
          return throwError(errMsg);
        })
      );
  }
}
