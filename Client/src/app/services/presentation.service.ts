import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserDTO } from '../dto/user.dto';
import { PresentationDTO } from '../dto/presentation.dto';
import { environment } from 'src/environments/environment.prod';

const API_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class PresentationService {

  constructor(private http: HttpClient) { }

  createPresentation(presentation: PresentationDTO) {
    return this.http.put<UserDTO>(`${API_URL}/presentations`, presentation);
  }

  getPresentations() {
    return this.http.get<PresentationDTO[]>(`${API_URL}/presentations`);
  }

  getPresentationById(id: number) {
    return this.http.get<PresentationDTO>(`${API_URL}/presentations/${id}`);
  }

  updatePresentation(presentation: PresentationDTO) {
    return this.http.post<PresentationDTO>(`${API_URL}/presentations/${presentation.id}`, presentation);
  }

  deletePresentation(id: number) {
    return this.http.delete<PresentationDTO>(`${API_URL}/presentations/${id}`);
  }
}
