import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from 'src/environments/environment.prod';
import { User } from '../models/user.model';
import { UserDTO } from '../models/dtos/user.dto';

const SERVER_URL = environment.serverUrl;
const USERS_API = environment.usersApiRoute;
const PAGE_SIZE = environment.defaultUserPageSize;

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  createUser(user: User) {
    return this.http.post<UserDTO>(SERVER_URL + USERS_API + ``, user);
  }

  getAllUsers() {
    return this.http.get<UserDTO[]>(SERVER_URL + USERS_API + ``);
  }

  getUserById(id: string) {
    return this.http.get<UserDTO>(SERVER_URL + USERS_API + `/${id}`);
  }

  updateUser(user: User) {
    return this.http.put<UserDTO>(SERVER_URL + USERS_API, user);
  }

  deleteUser(id: string) {
    return this.http.delete<UserDTO>(SERVER_URL + USERS_API + `/${id}`);
  }

  getUsersByPage(page:number){
    return this.http.get<any>(SERVER_URL + USERS_API, {
      params: {
        page: `${page}`,
        size: `${PAGE_SIZE}`
      }
    });
  }
}
