import {Injectable} from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {AppResponse} from '../interfaces/app';
import {UserRequestDTO} from './user.service';

export interface BookingRequestDTO {
  distanceId: number | null;
  carId: number;
  netAmount: number;
  totalAmount: number;
  taxAmount: number;
}

export interface BookingResponseDTO {
  bookingReference: string;
}

@Injectable({
  providedIn: 'root'
})
export class BookingService {

  apiUrl = environment.url + "/bookings"

  constructor(private http: HttpClient) {
  }

  book(bookingRequestDTO: BookingRequestDTO) {
    return this.http.post<AppResponse<null>>(this.apiUrl, bookingRequestDTO);
  }

}
