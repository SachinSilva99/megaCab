import { Injectable } from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {AppResponse} from '../interfaces/app';

export interface DriverResponseDTO {
    id : number;
    name : string;
    phone : string;
}


@Injectable({
  providedIn: 'root'
})
export class DriverService {

  apiUrl = environment.url + "/drivers"

  constructor(private http: HttpClient) {
  }

  getAllDrivers() {
    return this.http.get<AppResponse<DriverResponseDTO[]>>(this.apiUrl + "/all");
  }
}
