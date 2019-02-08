import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from 'src/environments/environment.prod';
import { User } from '../models/user.model';
import { UserDTO } from '../models/dtos/user.dto';

const SERVER_URL = environment.serverUrl;
const USERS_API = environment.usersApiRoute;

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  createUser(user: User) {
    // return this.http.put<UserDTO>(`${API_URL}/authentication`, user);
    return this.http.post<UserDTO>(SERVER_URL + USERS_API + ``, user);
  }

  getAllUsers() {
    return this.http.get<UserDTO[]>(SERVER_URL + USERS_API + ``);
  }

  getUserById(id: string) {
    return this.http.get<UserDTO>(SERVER_URL + USERS_API + `/${id}`);
  }

  updateUser(user: User) {
    // return this.http.patch<UserDTO>(`${API_URL}/users/${user.id}`, user);
    return this.http.put<UserDTO>(SERVER_URL + USERS_API + `/${user.id}`, user);
  }

  deleteUser(id: string) {
    return this.http.delete<UserDTO>(SERVER_URL + USERS_API + `/${id}`);
  }
}
