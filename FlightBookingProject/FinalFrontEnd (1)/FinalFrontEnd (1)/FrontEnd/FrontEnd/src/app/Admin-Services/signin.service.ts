import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class SigninService {

  constructor(private http:HttpClient) { }

  baseUrl = "http://localhost:9004/registration"

  signinUser(data : any):Observable<any>{
    console.log("******"+data);

    return this.http.post(`${this.baseUrl}/signin` , data);

  }
}
