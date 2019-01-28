import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

import { environment } from 'src/environments/environment.prod';
import {User} from '../models/user.model';

const API_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class UserService {

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
}
