import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Presentation } from '../models/presentation.model';
import { environment } from 'src/environments/environment.prod';
import { PresentationDTO } from '../models/dtos/presentation.dto';

const SERVER_URL = environment.serverUrl;
const PRESENTATIONS_API = environment.presentationsApiRoute;

@Injectable({
  providedIn: 'root'
})
export class PresentationService {

  constructor(private http: HttpClient) { }

  createPresentation(presentation: Presentation) {
    return this.http.post<string>(SERVER_URL + PRESENTATIONS_API, Presentation.toDTO(presentation));
  }

  getPresentations() {
    return this.http.get<PresentationDTO[]>(SERVER_URL + PRESENTATIONS_API);
  }

  getPresentationsByTitle(title: string) {
    return this.http.get<PresentationDTO[]>(SERVER_URL + PRESENTATIONS_API, {
      params: {
        title_like: title
      }
    });
  }

  getPresentationsByEmailKeyword(keyword: string) {
    return this.http.get<PresentationDTO[]>(SERVER_URL + PRESENTATIONS_API, {
      params: {
        email_like: '' + keyword
      }
    });
  }

  getPresentationsByEmail(email: string) {
    return this.http.get<PresentationDTO[]>(SERVER_URL + PRESENTATIONS_API, {
      params: {
        email: '' + email
      }
    });
  }

  getPresentationById(id: string) {
    return this.http.get<PresentationDTO>(SERVER_URL + PRESENTATIONS_API + `/${id}`);
  }

  updatePresentation(presentation: Presentation) {
    return this.http.put<string>(SERVER_URL + PRESENTATIONS_API, Presentation.toDTO(presentation));
  }

  deletePresentation(id: string) {
    return this.http.delete<PresentationDTO>(SERVER_URL + PRESENTATIONS_API + `/${id}`);
  }
}
