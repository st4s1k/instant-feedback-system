import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Presentation } from '../models/presentation.model';
import { environment } from 'src/environments/environment.prod';
import { PresentationDTO } from '../models/dtos/presentation.dto';

const SERVER_URL = environment.serverUrl;
const PRESENTATIONS_API = environment.presentationsApiRoute;
const PAGE_SIZE = environment.defaultPageSize;

@Injectable({
  providedIn: 'root'
})
export class PresentationService {

  constructor(private http: HttpClient) { }

  createPresentation(presentation: Presentation) {
    return this.http.post(SERVER_URL + PRESENTATIONS_API, Presentation.toDTO(presentation));
  }

  getPresentations() {
    return this.http.get<any>(SERVER_URL + PRESENTATIONS_API);
  }

  getPresentationsByPage(page: number) {
    return this.http.get<any>(SERVER_URL + PRESENTATIONS_API, {
      params: {
        page: `${page}`,
        size: `${PAGE_SIZE}`
      }
    });
  }

  getPresentationByPageAndUser(page: number, email: string) {
    return this.http.get<any>(SERVER_URL + PRESENTATIONS_API, {
      params: {
        email: `${email}`,
        page: `${page}`,
        size: `${PAGE_SIZE}`
      }
    });
  }

  getPresentationsByTitleOrEmailKeyword(keyword: string, page: number) {
    return this.http.get<any>(SERVER_URL + PRESENTATIONS_API, {
      params: {
        title_or_email_like: keyword,
        page: `${page}`,
        size: `${PAGE_SIZE}`
      }
    });
  }

  getPresentationsByTitleKeyword(title: string, page: number) {
    return this.http.get<any>(SERVER_URL + PRESENTATIONS_API, {
      params: {
        title_like: title,
        page: `${page}`,
        size: `${PAGE_SIZE}`
      }
    });
  }

  getPresentationsByEmailKeyword(keyword: string, page: number) {
    return this.http.get<any>(SERVER_URL + PRESENTATIONS_API, {
      params: {
        email_like: keyword,
        page: `${page}`,
        size: `${PAGE_SIZE}`
      }
    });
  }

  getPresentationsByEmail(email: string) {
    return this.http.get<PresentationDTO[]>(SERVER_URL + PRESENTATIONS_API, {
      params: {
        email: email
      }
    });
  }

  getPresentationById(id: string) {
    return this.http.get<PresentationDTO>(SERVER_URL + PRESENTATIONS_API + `/${id}`);
  }

  updatePresentation(presentation: Presentation) {
    return this.http.put(SERVER_URL + PRESENTATIONS_API, Presentation.toDTO(presentation));
  }

  deletePresentation(id: string) {
    return this.http.delete(SERVER_URL + PRESENTATIONS_API + `/${id}`, {
      responseType: 'text'
    });
  }
}
