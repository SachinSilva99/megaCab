import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {AppResponse} from '../interfaces/app';

export interface UserRequestDTO {
  username: string;
  password: string;
  role: string;
  name: string;
  address: string;
  nic: string;
  phone: string;
}

export interface UserLoginRequestDTO {
  username: string;
  password: string;
}


@Injectable({
  providedIn: 'root'
})
export class UserService {
  apiUrl = environment.url + "/users"

  constructor(private http: HttpClient) {
  }

  register(user: UserRequestDTO) {
    return this.http.post<AppResponse<null>>(this.apiUrl + "/register", user);
  }

  login(user: UserLoginRequestDTO) {
    return this.http.post<AppResponse<null>>(this.apiUrl + "/login", user);
  }
}
