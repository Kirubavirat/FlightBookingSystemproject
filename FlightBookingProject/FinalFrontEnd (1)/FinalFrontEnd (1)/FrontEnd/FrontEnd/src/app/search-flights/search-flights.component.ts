import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FlightService } from '../Admin-Services/flight.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-search-flights',
  templateUrl: './search-flights.component.html',
  styleUrls: ['./search-flights.component.css']
})
export class SearchFlightsComponent implements OnInit {

  search!: FormGroup;
  noFlightsFoundMessage: string = ''; // Initialize it with an empty string

  

 
  datasource: any[]=[];
  constructor(private flightService:FlightService,
          private router:Router,
          private formbuilder:FormBuilder
    ) { }

   
 
    ngOnInit():void{
      this.search=this.formbuilder.group({
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
        date: [
          '',
          [
            Validators.required,
            
          ],
        ]
      });}

      searchFlights()
      {
        this.flightService.findByLocation(this.search.value.flightFrom,this.search.value.flightTo,this.search.value.date).subscribe
          ({
            next:(val:any)=>{
              this.datasource=val;
              if(this.datasource.length===0)
              {
                  this.noFlightsFoundMessage="No flights were found";
              }
            },
            
            error:console.error,
          });
      }

}
