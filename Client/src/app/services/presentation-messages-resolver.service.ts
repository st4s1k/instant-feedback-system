import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { take, mergeMap } from 'rxjs/operators';
import { MessageDTO } from '../models/dtos/message.dto';
import { Message } from '../models/message.model';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class PresentationMessagesResolverService {

  constructor(private msgSrv: MessageService, private router: Router) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Message[]> | Observable<never> {
    const id = route.paramMap.get('id');

    return this.msgSrv.getPresentationMessages(id).pipe(
      take(1),
      mergeMap(messageDtoList => {
        if (messageDtoList) {
          // alert('Sucess: Loaded presentations.');
          return of(
            messageDtoList.map(
              messageDto => MessageDTO.toModel(messageDto)
            )
          );
        } else { // id not found
          // this.router.navigate(['/home']);
          alert('Error: Cannot load presentations.');
          return EMPTY;
        }
      })
    );
  }
}
