import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FlightService } from '../Admin-Services/flight.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-flight-list',
  templateUrl: './flight-list.component.html',
  styleUrls: ['./flight-list.component.css'],
})
export class FlightListComponent implements OnInit {
  formValue!: FormGroup;
  isLoggedIn: boolean = false;
  isRole: string | null = null;

  displayedColumns: string[] = [
    'flightNo',
    'flightName',
    'flightFrom',
    'flightTo',
    'fare',
    'seats',
    'time',
    'date',
    'action',
  ];

  dataSource: any[] = [];

  constructor(
    private flightService: FlightService,
    private router: Router,
    private formbuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.getFlightList();
    const loggedInValue = sessionStorage.getItem('loggedIn');
    this.isLoggedIn = loggedInValue === 'true';
    this.isRole = sessionStorage.getItem('role');
    this.formValue = this.formbuilder.group({
      flightNo: ['', [Validators.required, Validators.pattern('\\d{5}')]],
      flightName: [
        '',
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(50),
        ],
      ],
      flightFrom: [
        '',
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(30),
        ],
      ],
      flightTo: [
        '',
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(30),
        ],
      ],
      fare: [0, [Validators.required, Validators.min(0), Validators.max(1000)]],
      seats: [0, [Validators.required, Validators.min(0), Validators.max(50)]],
      date:['',[Validators.required]],
      time: ['', [Validators.required]],
      
    });
  }
  
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value
      .trim()
      .toLowerCase();
    this.dataSource = this.dataSource.filter((row) => {
      return (
        row.flightNo.toLowerCase().includes(filterValue) ||
        row.flightName.toLowerCase().includes(filterValue) ||
        row.flightFrom.toLowerCase().includes(filterValue) ||
        row.flightTo.toLowerCase().includes(filterValue) ||
        row.date.toLowerCase().includes(filterValue)
      );
    });

    if (!filterValue || filterValue === '') {
      this.getFlightList();
    }
  }

  getFlightList() {
    this.flightService.getAllFlights().subscribe({
      next: (res) => {
        this.dataSource = res;
      },
      error: console.error,
    });
  }

  deleteFlight(flightNo: any) {
    console.log('Deleting flight with flightNo: ', flightNo);

    this.flightService.deleteFlight(flightNo).subscribe({
      next: (res) => {
        console.log(res);
      },
    });
   // alert('Flight deleted successfully ' + flightNo);
    Swal.fire({
      title: 'Flight deleted successfully ' + flightNo,
      icon: 'success',
    });
    this.getFlightList();
  }

  EditForm(data: any) {
    this.formValue.controls['flightNo'].setValue(data.flightNo);
    this.formValue.controls['flightName'].setValue(data.flightName);
    this.formValue.controls['flightFrom'].setValue(data.flightFrom);
    this.formValue.controls['flightTo'].setValue(data.flightTo);
    this.formValue.controls['fare'].setValue(data.fare);
    this.formValue.controls['seats'].setValue(data.seats);
    this.formValue.controls['date'].setValue(data.date);
    this.formValue.controls['time'].setValue(data.time);
    
    console.log(this.formValue.value.flightNo);
    sessionStorage.setItem('flightNo', this.formValue.value.flightNo);
    sessionStorage.setItem('Fare', this.formValue.value.fare);
  }

  redirectToBookingOrLogin(flightNo: any,fare:any) {
    if (this.isLoggedIn) {
      // If logged in, check their role
      sessionStorage.setItem('flightNo', flightNo);
      sessionStorage.setItem('Fare', fare);

      
      if (this.isRole === 'User') {
        // Redirect regular user to booking page
        this.router.navigate(['/bookingTicket']);
       
      }
    } else {
      // If not logged in, redirect to login page
      this.router.navigate(['/login']);
    }
  }
  updateFlight() {
    console.log(this.formValue.value)
    this.flightService
      .updateFlight(this.formValue.value.flightNo, this.formValue.value)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.getFlightList();
          this.router.navigate(['flightList']);
         
        },
      });
      this.getFlightList();
  }
  onSubmit() {
    this.router.navigate(['/flightList']);
    this.getFlightList();
  }
}
