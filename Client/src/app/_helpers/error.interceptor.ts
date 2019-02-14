import { Injectable } from '@angular/core';
import { HttpEvent,
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
  HttpResponse,
  HttpErrorResponse} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AuthenticationService } from '../services/authentication.service';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  constructor(private authenticationService: AuthenticationService) { }
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>>{
    return next.handle(request)
      .pipe(
        catchError( (error: HttpErrorResponse) => {
          if (error.status === 401) {
            // auto logout if 401 response returned from api
            alert('ERR 401! ' + error.error.message);
            this.authenticationService.logout();
            location.reload(true);
          }
          let errMsg = '';
          // Client Side Error
          if (error.error instanceof ErrorEvent) {
            errMsg = `Error: ${error.error.message}`;
          }
          else {  // Server Side Error
            errMsg = `Error Code: ${error.status},  Message: ${error.message}`;
          }
          return throwError(errMsg);
        })
      )
  }
}
