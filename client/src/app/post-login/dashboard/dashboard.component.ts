import {Component} from '@angular/core';
import {NgFor, NgForOf} from '@angular/common';
import {CarResponseDTO, CarService} from '../../core/service/car.service';
import {RSP_SUCCESS} from '../../core/constant/ResponseCode';
import {DistanceResponseDTO, DistanceService} from '../../core/service/distance.service';
import {ReactiveFormsModule} from '@angular/forms';
import {DriverService} from '../../core/service/driver.service';
import {LoggedInUser, StorageService, USER} from '../../core/service/storage.service';
import {Router} from '@angular/router';
import {BookingService} from '../../core/service/booking.service';
import Swal from 'sweetalert2';


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
    private distanceService: DistanceService,
    private storageService: StorageService,
    private router: Router,
    private bookingService: BookingService,
    private driverService: DriverService) {
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
    this.driverService.getAllDrivers().subscribe({
      next: (res) => {
        if (res.status === RSP_SUCCESS) {
          this.drivers = res.content;
        } else {
        }
      },
      error(er) {
        console.log(er)
      }
    });
  }

  selectCar(car: CarResponseDTO) {
    console.log(car)
    this.selectedCar = car;
  }

  selectDistance(event: Event) {
    const selectedDistanceId = (event.target as HTMLSelectElement).value;
    console.log('Selected Distance ID:', selectedDistanceId);
    this.selectedDistance = this.distances.find(value => value.id === parseInt(selectedDistanceId));
  }

  selectDriver(event: Event) {
    const selectedDriverId = (event.target as HTMLSelectElement).value;
    this.selectedDriver = this.drivers.find(value => value.id === parseInt(selectedDriverId));
  }


  bookVehicle() {

    let user = this.storageService.getItem<LoggedInUser>(USER);
    if (!user) {
      return;
    }
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
        taxAmount: taxAmount
      };
      console.log(bookingRequest)
      this.bookingService.book(bookingRequest).subscribe({
        next: (res) => {
          if (res.status === RSP_SUCCESS) {
            Swal.fire({
              icon: 'success',
              title: 'Success',
            })
          }else {
            Swal.fire({
              icon: 'error',
              title: 'Something went wrong!',
            })
          }
        }
      })
    } else {
      return;
    }


  }

  logoutClick() {
    this.storageService.clear();
    this.router.navigate(["/pre/login"])
  }
}
