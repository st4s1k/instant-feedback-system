import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from 'src/environments/environment.prod';
import { User } from '../models/user.model';
import { UserDTO } from '../models/dtos/user.dto';

  // const API_URL = environment.apiUrl;

const API_URL = environment.jsonServerUrl;

@Injectable({
  providedIn: 'root'
})
export class UserService {

  //  fd: FormData = new FormData;

  constructor(private http: HttpClient) { }

  createUser(user: User) {
    return this.http.put<UserDTO>(`${API_URL}/authentication`, user);
    // return this.http.post<UserDTO>(`${API_URL}/users`, user);
  }

  getAllUsers() {
    return this.http.get<UserDTO[]>(`${API_URL}/users`);
  }

  getUserById(id: number) {
    return this.http.get<UserDTO>(`${API_URL}/users/${id}`);
  }

  updateUser(user: User) {
    return this.http.patch<UserDTO>(`${API_URL}/users/${user.id}`, user);
  }

  deleteUser(id: number) {
    return this.http.delete<UserDTO>(`${API_URL}/users/${id}`);
  }
}
