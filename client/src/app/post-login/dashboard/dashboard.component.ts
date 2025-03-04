import {Component} from '@angular/core';
import {NgFor, NgForOf} from '@angular/common';
import {CarResponseDTO, CarService} from '../../core/service/car.service';
import {RSP_SUCCESS} from '../../core/constant/ResponseCode';
import {DistanceResponseDTO, DistanceService} from '../../core/service/distance.service';
import {ReactiveFormsModule} from '@angular/forms';


interface DriverResponseDTO {
  id: number;
  name: string;
  phone: string;
}

interface BookingRequestDTO {
  distanceId: number;
  carId: number;
  netAmount: number;
  totalAmount: number;
  taxAmount: number;
  customerId: number;
}

interface BookingResponseDTO {
  bookingReference: string;
}

@Component({
  selector: 'app-dashboard',
  imports: [
    NgForOf,
    ReactiveFormsModule
  ],
  templateUrl: './dashboard.component.html',
  standalone: true,
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent {

  taxPercentage = 10;
  perKmPrice = 100;


  cars: CarResponseDTO[] = [];
  distances: DistanceResponseDTO[] = [];
  drivers: DriverResponseDTO[] = [];

  selectedCar: CarResponseDTO | undefined;
  selectedDistance: DistanceResponseDTO | undefined;
  selectedDriver: DriverResponseDTO | undefined;

  constructor(
    private carService: CarService,
    private distanceService: DistanceService) {
    this.fetchCars();
    this.fetchDistances();
    this.fetchDrivers();
  }


  fetchCars() {
    this.carService.getAllCars().subscribe({
      next: (res) => {
        if (res.status === RSP_SUCCESS) {
          this.cars = res.content;
        } else {
          //error
        }
      },
      error(er) {
        console.log(er)
      }
    });
  }

  fetchDistances() {
    this.distanceService.getAllDistances().subscribe({
      next: (res) => {
        if (res.status === RSP_SUCCESS) {
          this.distances = res.content;
        } else {
        }
      },
      error(er) {
        console.log(er)
      }
    });
  }

  fetchDrivers() {

  }

  selectCar(car: CarResponseDTO) {
    this.selectedCar = car;
  }

  selectDistance(event: Event) {
    const selectedDistanceId = (event.target as HTMLSelectElement).value;
    console.log('Selected Distance ID:', selectedDistanceId);
  }

  selectDriver(event: Event) {
    const selectedDriver = (event.target as HTMLSelectElement).value;
    this.selectedDriver = JSON.parse(selectedDriver);
  }


  bookVehicle() {
    if (this.selectedCar && this.selectedDistance && this.selectedDriver) {
      const distanceKm = this.selectedDistance.distanceKm;
      const netAmount = distanceKm * this.perKmPrice;
      const taxAmount = (netAmount * this.taxPercentage) / 100;
      const totalAmount = netAmount * taxAmount;
      const bookingRequest: BookingRequestDTO = {
        distanceId: this.selectedDistance.id,
        carId: this.selectedCar.id,
        netAmount: netAmount,
        totalAmount: totalAmount,
        taxAmount: taxAmount,
        customerId: 1
      };
    } else {
      return;
    }


  }
}
