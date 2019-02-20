import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.prod';
import { Mark } from '../models/mark.model';
import { HttpClient } from '@angular/common/http';
import { MarkDTO } from '../models/dtos/mark.dto';

const SERVER_URL = environment.serverUrl;
const MARKS_API = environment.marksApiRoute;

@Injectable({
  providedIn: 'root'
})
export class MarkService {

  constructor(private http: HttpClient) { }

  addMark(mark: Mark) {
    return this.http.post<number>(SERVER_URL + MARKS_API, Mark.toDTO(mark));
  }

  getUserMark(userId: string, presentationId: string) {
    return this.http.get<MarkDTO>(SERVER_URL + MARKS_API, {
      params: {
        userId: userId,
        presentationId: presentationId
      }
    });
  }
}
