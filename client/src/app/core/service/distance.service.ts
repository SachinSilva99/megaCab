import { Injectable } from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {AppResponse} from '../interfaces/app';
import {CarResponseDTO} from './car.service';
export interface DistanceResponseDTO {
  id: number;
  pickupLocation: string;
  dropLocation: string;
  distanceKm: number;
}

@Injectable({
  providedIn: 'root'
})
export class DistanceService {

  apiUrl = environment.url + "/distances"

  constructor(private http: HttpClient) {
  }

  getAllDistances() {
    return this.http.get<AppResponse<DistanceResponseDTO[]>>(this.apiUrl + "/all");
  }

}
