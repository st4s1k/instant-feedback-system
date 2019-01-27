import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';
import { Presentation } from '../models/presentation.model';
import { environment } from 'src/environments/environment.prod';

const API_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private http: HttpClient) { }

  createUser(user: User) {
    return this.http.post<User>(`${API_URL}/users`, user);
  }

  getUsers() {
    return this.http.get<User[]>(`${API_URL}/users`);
  }

  getUserById(id: number) {
    return this.http.get<User>(`${API_URL}/users/${id}`);
  }

  updateUser(user: User) {
    return this.http.put<User>(`${API_URL}/users/${user.id}`, user);
  }

  deleteUser(id: number) {
    return this.http.delete<User>(`${API_URL}/users/${id}`);
  }

  createPresentation(presentation: Presentation) {
    return this.http.put<User>(`${API_URL}/presentations`, presentation);
  }

  getPresentations() {
    return this.http.get<Presentation[]>(`${API_URL}/presentations`);
  }

  getPresentationById(id: number) {
    return this.http.get<Presentation>(`${API_URL}/presentations/${id}`);
  }

  updatePresentation(presentation: Presentation) {
    return this.http.post<Presentation>(`${API_URL}/presentations/${presentation.id}`, presentation);
  }

  deletePresentation(id: number) {
    return this.http.delete<Presentation>(`${API_URL}/presentations/${id}`);
  }
}
