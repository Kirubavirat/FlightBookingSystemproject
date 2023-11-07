import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SigninComponent } from './signin/signin.component';
import { SignupComponent } from './signup/signup.component';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { FlightFormComponent } from './flight-form/flight-form.component';
import { BookingFormComponent } from './booking-form/booking-form.component';
import { DatePipe } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NavbarAdminComponent } from './navbar-admin/navbar-admin.component';
import { FlightListComponent } from './flight-list/flight-list.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { MatDialogModule } from '@angular/material/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatInputModule} from '@angular/material/input';
import { MatSortModule } from '@angular/material/sort';
import {MatButtonModule} from '@angular/material/button';
import { UserListComponent } from './user-list/user-list.component';
import { BookingListComponent } from './booking-list/booking-list.component';
import { SearchFlightsComponent } from './search-flights/search-flights.component';
import { HomePageComponent } from './home-page/home-page.component';
import { PaymentlistComponent } from './paymentlist/paymentlist.component';
import { MyProfileComponent } from './my-profile/my-profile.component';
import { PaymentappComponent } from './paymentapp/paymentapp.component';
import { NewhomeComponent } from './newhome/newhome.component';

@NgModule({
  declarations: [
    AppComponent,
    SigninComponent,
    SignupComponent,
    FlightFormComponent,
    BookingFormComponent,
    NavbarAdminComponent,
    FlightListComponent,
    UserListComponent,
    BookingListComponent,
    SearchFlightsComponent,
    HomePageComponent,
    PaymentlistComponent,
    MyProfileComponent,
    PaymentappComponent,
    NewhomeComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    DatePipe,
    HttpClientModule,
    MatToolbarModule,
    MatFormFieldModule,
    MatIconModule,
    MatPaginatorModule,
    MatTableModule,
    MatDialogModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatButtonModule,
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
