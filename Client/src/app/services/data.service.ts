import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiResponse } from '../models/api.response';
import { User } from '../models/user.model';
import { Presentation } from '../models/presentation.model';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private http: HttpClient) { }

  protocol = 'http';
  ipAdress = 'localhost';
  port = '8080';

  baseUrl = `${this.protocol}://${this.ipAdress}:${this.port}`;

  createUser(user: User) {
    return this.http.post<ApiResponse>(`${this.baseUrl}/users`, user);
  }

  getUsers(): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(`${this.baseUrl}/users`);
  }

  getUserById(id: number): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(`${this.baseUrl}/users/${id}`);
  }

  updateUser(user: User): Observable<ApiResponse> {
    return this.http.put<ApiResponse>(`${this.baseUrl}/users/${user.id}`, user);
  }

  deleteUser(id: number) {
    return this.http.delete<ApiResponse>(`${this.baseUrl}/users/${id}`);
  }

  createPresentation(presentation: Presentation) {
    return this.http.put<ApiResponse>(`${this.baseUrl}/presentations`, presentation);
  }

  getPresentations() {
    return this.http.get<ApiResponse>(`${this.baseUrl}/presentations`);
  }

  getPresentationById(id: number) {
    return this.http.get<ApiResponse>(`${this.baseUrl}/presentations/${id}`);
  }

  updatePresentation(presentation: Presentation) {
    return this.http.post<ApiResponse>(`${this.baseUrl}/presentations/${presentation.id}`, presentation);
  }

  deletePresentation(id: number) {
    return this.http.delete<ApiResponse>(`${this.baseUrl}/presentations/${id}`);
  }
}
