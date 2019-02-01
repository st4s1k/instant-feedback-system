import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PresentationDTO } from '../dto/presentation.dto';
import { environment } from 'src/environments/environment.prod';

// const API_URL = `${environment.jsonServerUrl}${environment.presentations}`;
const API_URL = `${environment.apiUrl}${environment.presentations}`;

@Injectable({
  providedIn: 'root'
})
export class PresentationService {

  constructor(private http: HttpClient) { }

  createPresentation(presentation: PresentationDTO) {
    return this.http.post<PresentationDTO>(`${API_URL}`, presentation);
  }

  getPresentations() {
    return this.http.get<PresentationDTO[]>(`${API_URL}`);
  }

  getPresentationsByUser(userId: number) {
    return this.http.get<PresentationDTO[]>(`${API_URL}`, {
      params: {
        userId: '' + userId
      }
    });
  }

  getPresentationsByTitle(title: string) {
    return this.http.get<PresentationDTO[]>(`${API_URL}`, {
      params: {
        title: title
      }
    });
  }

  getPresentationById(id: number) {
    return this.http.get<PresentationDTO>(`${API_URL}/${id}`);
  }

  updatePresentation(presentation: PresentationDTO) {
    return this.http.put<PresentationDTO>(`${API_URL}/${presentation.id}`, presentation);
  }

  deletePresentation(id: number) {
    return this.http.delete<PresentationDTO>(`${API_URL}/${id}`);
  }
}
