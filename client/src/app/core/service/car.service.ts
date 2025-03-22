import { Injectable } from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {AppResponse} from '../interfaces/app';

export interface CarResponseDTO {
  id: number;
  model: string;
  driverId: number;
  driverName: string;
  driverContact: string;
  noOfSeats: number;
  image1: string;
  image2: string;
  image3: string;
}


@Injectable({
  providedIn: 'root'
})
export class CarService {

  apiUrl = environment.url + "/cars"

  constructor(private http: HttpClient) {
  }

  getAllCars() {
    return this.http.get<AppResponse<CarResponseDTO[]>>(this.apiUrl + "/available");
  }


}
