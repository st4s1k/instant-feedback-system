import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Presentation } from '../models/presentation.model';
import { environment } from 'src/environments/environment.prod';

const API_URL = `${environment.jsonServerUrl}${environment.presentations}`;
// const API_URL = `${environment.apiUrl}${environment.presentations}`;

@Injectable({
  providedIn: 'root'
})
export class PresentationService {

  constructor(private http: HttpClient) { }

  createPresentation(presentation: Presentation) {
    return this.http.post<Presentation>(`${API_URL}`, presentation);
  }

  getPresentations() {
    return this.http.get<Presentation[]>(`${API_URL}`);
  }

  getPresentationsByUser(userId: number) {
    return this.http.get<Presentation[]>(`${API_URL}`, {
      params: {
        userId: '' + userId
      }
    });
  }

  getPresentationsByTitle(title: string) {
    return this.http.get<Presentation[]>(`${API_URL}`, {
      params: {
        title: title
      }
    });
  }

  getPresentationById(id: number) {
    return this.http.get<Presentation[]>(`${API_URL}`, {
      params: {
        id: '' + id
      }
    });
  }

  updatePresentation(presentation: Presentation) {
    return this.http.put<Presentation>(`${API_URL}/${presentation.id}`, presentation);
    // return this.http.put<Presentation>(`${API_URL}`, presentation, {
    //   params: {
    //     id: '' + presentation.id
    //   }
    // });
  }

  deletePresentation(id: number) {
    // return this.http.delete<Presentation>(`${API_URL}`, {
    //   params: {
    //     id: '' + id
    //   }
    // });
    return this.http.delete<Presentation>(`${API_URL}/${id}`);
  }

  getAvgMark(marks: { email: string, mark: number }[]) {

    let value = Number(0).toFixed(2);

    if (marks) {
      let sum = 0;

      marks.forEach(element => {
        sum += element.mark;
      });
      value = Number(sum / marks.length).toFixed(2);
    }

    return value;
  }
}
