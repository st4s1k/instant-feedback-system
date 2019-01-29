import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

import { environment } from 'src/environments/environment.prod';
import { UserDTO } from '../dto/user.dto';

const API_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }
  createUser(user: UserDTO) {
    // return this.http.post<UserDTO>(`${API_URL}/users`, user);
    return this.http.put<UserDTO>(`${API_URL}/userRegister`, user);
  }

  getAllUsers() {
    return this.http.get<UserDTO[]>(`${API_URL}/users`);
  }

  getUserById(id: number) {
    return this.http.get<UserDTO>(`${API_URL}/users/${id}`);
  }

  updateUser(user: UserDTO) {
    return this.http.put<UserDTO>(`${API_URL}/users/${user.id}`, user);
  }

  deleteUser(id: number) {
    return this.http.delete<UserDTO>(`${API_URL}/users/${id}`);
  }
}
