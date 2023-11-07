import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FlightService {

  constructor(private http: HttpClient) { }
  

  private createRequestOptions(): { headers: HttpHeaders } {
    const token = 'Bearer ' + sessionStorage.getItem('token');
    // Define the headers
    const headers = new HttpHeaders({
      'Authorization': token
    });

    // Define the request options
    const requestOptions = {
      headers: headers
    };

    return requestOptions;
  }

  baseUrl = "http://localhost:9191/registration/autherization";
  flightNo = sessionStorage.getItem('flightNo');

  addFlight(data: any): Observable<any> {
    const requestOptions = this.createRequestOptions();
    return this.http.post(`${this.baseUrl}/addFlight`, data, requestOptions);
  }

  getAllFlights(): Observable<any[]> {
    const requestOptions = this.createRequestOptions();
    return this.http.get<any[]>(`${this.baseUrl}/viewallflights`);
  }


  getFlightByNo(flightNo: string): Observable<any> {
    const requestOptions = this.createRequestOptions();
    return this.http.get<any>(`${this.baseUrl}/viewflightbyflightNo/${flightNo}`, requestOptions);
  }

  getFlightsByName(flightName: string): Observable<any[]> {
    const requestOptions = this.createRequestOptions();
    return this.http.get<any[]>(`${this.baseUrl}/viewflightbyname/${flightName}`, requestOptions);
  }

  deleteFlight(flightNo: string): Observable<string> {
    const requestOptions = this.createRequestOptions();
    return this.http.delete<string>(`${this.baseUrl}/deleteflight/${flightNo}`, requestOptions);
  }

  updateFlight(flightNo: string, data: any): Observable<any> {
    const requestOptions = this.createRequestOptions();
    return this.http.put(`${this.baseUrl}/updateflightbyid/${flightNo}`, data, requestOptions);
  }
  
  findByLocation(flightFrom: string, flightTo: string, date: string): Observable<any[]> {
    const requestOptions = this.createRequestOptions();
    return this.http.get<any[]>(`${this.baseUrl}/findbetween/${flightFrom}/${flightTo}/${date}`);
  }
}
