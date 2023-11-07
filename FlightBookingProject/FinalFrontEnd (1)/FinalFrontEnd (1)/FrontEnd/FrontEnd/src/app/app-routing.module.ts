import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SigninComponent } from './signin/signin.component';
import { SignupComponent } from './signup/signup.component';
import { FlightFormComponent } from './flight-form/flight-form.component';
import { BookingFormComponent } from './booking-form/booking-form.component';
import { NavbarAdminComponent } from './navbar-admin/navbar-admin.component';
import { FlightListComponent } from './flight-list/flight-list.component';
import { UserListComponent } from './user-list/user-list.component';
import { BookingListComponent } from './booking-list/booking-list.component';
import { SearchFlightsComponent } from './search-flights/search-flights.component';
import { HomePageComponent } from './home-page/home-page.component';
import { PaymentlistComponent } from './paymentlist/paymentlist.component';
import { MyProfileComponent } from './my-profile/my-profile.component';
import { PaymentappComponent } from './paymentapp/paymentapp.component';
import { NewhomeComponent } from './newhome/newhome.component';

const routes: Routes = [
 
 
  {path:'newhome', component:NewhomeComponent},
  { path: '', redirectTo: '/newhome', pathMatch: 'full' },
  // { path: '**', redirectTo: '/login', pathMatch: 'full' },
  {path:'home',component:HomePageComponent},
  {path:'login', component: SigninComponent},
  {path:'register', component: SignupComponent},
  {path:'addflight', component: FlightFormComponent},
  {path:'bookingTicket', component: BookingFormComponent},
  {path:'navbar', component: NavbarAdminComponent}, 
  {path:'flightList', component: FlightListComponent},
  {path:'userList', component:UserListComponent},
  {path:'bookingList', component:BookingListComponent},
  {path:'searching', component:SearchFlightsComponent},
  {path:'paymentlist',component:PaymentlistComponent},
  {path:'profile',component:MyProfileComponent},
  {path:'pay',component:PaymentappComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
