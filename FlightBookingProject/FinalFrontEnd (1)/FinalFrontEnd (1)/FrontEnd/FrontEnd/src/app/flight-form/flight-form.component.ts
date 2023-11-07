import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FlightService } from '../Admin-Services/flight.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-flight-form',
  templateUrl: './flight-form.component.html',
  styleUrls: ['./flight-form.component.css']
})
export class FlightFormComponent {
  flightForm: FormGroup;
  // addTrainErrorMessage: any;

  constructor(private fb: FormBuilder, private flightservice:FlightService,private router: Router) {
    this.flightForm = this.fb.group({
      flightNo: ['', [Validators.required, Validators.pattern('\\d{5}')]],
      flightName: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]],
      flightFrom: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(30)]],
      flightTo: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(30)]],
      fare: [0, [Validators.required, Validators.min(0), Validators.max(1000)]],
      seats: [0, [Validators.required, Validators.min(0), Validators.max(50)]],
      time: ['', [Validators.required]],
      date: ['', [Validators.required]],
    });
  }

  
  submitForm() {
    if (this.flightForm.valid) {
      // Submit the form data
      console.log(this.flightForm.value);
      this.flightservice.addFlight(this.flightForm.value).subscribe({
        next:(val:any)=>{
          console.log(val)
         
         }
        // error:(val:any)=>{
        //   this.addTrainErrorMessage=val.error.message;
        //   window.scrollTo(0,0);
        //}
      })
      
      console.log(this.flightForm.value)
      //alert("Data Added Successfully")
      Swal.fire({
        title: 'Data Added Successfully.',
        icon: 'success',
      });
      // window.location.reload();
      this.router.navigate(['/flightList']);


    } else {
      // Handle validation errors
      //alert('Please Enter Valid Data')
      Swal.fire({
        title: 'Please Enter Valid Data',
        icon: 'error',
      });
      console.log('Form contains validation errors.');
    }
  }

  goBack() {
     // Reload the page
  }
  reload(){
    window.location.reload();
  }
}

