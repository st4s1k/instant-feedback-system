import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from 'src/environments/environment.prod';
import { User } from '../models/user.model';

  // const API_URL = environment.apiUrl;

const API_URL = environment.jsonServerUrl;

@Injectable({
  providedIn: 'root'
})
export class UserService {

  //  fd: FormData = new FormData;

  constructor(private http: HttpClient) { }

  createUser(user: User) {
    return this.http.put<User>(`${API_URL}/authentication`, user);
  }

  getAllUsers() {
    return this.http.get<User[]>(`${API_URL}/users`);
  }

  getUserById(id: number) {
    return this.http.get<User>(`${API_URL}/users/${id}`);
  }

  updateUser(user: User) {
    return this.http.patch<User>(`${API_URL}/users/${user.id}`, user);
  }

  deleteUser(id: number) {
    return this.http.delete<User>(`${API_URL}/users/${id}`);
  }
}
