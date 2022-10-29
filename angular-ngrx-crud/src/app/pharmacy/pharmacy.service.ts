import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http'
import { environment } from 'src/environments/environment';

import { Pharmacy } from './models/pharmacy';

@Injectable({
  providedIn: 'root'
})
export class PharmacyService {

  baseUrl = `${environment.apiUrl}pharmacies`;

  constructor(private http: HttpClient) { }

  getAll(): Observable<Pharmacy[]> {
    return this.http.get<Pharmacy[]>(this.baseUrl);
  }
  add(pharmacy: Pharmacy): Observable<Pharmacy> {
    return this.http.post<Pharmacy>(this.baseUrl, pharmacy);
  }

  update(id: number, pharmacy: Pharmacy): Observable<Pharmacy> {
    return this.http.put<Pharmacy>(`${this.baseUrl}/${id}`, pharmacy);
  }

  delete(id: number): Observable<Pharmacy> {
    return this.http.delete<Pharmacy>(`${this.baseUrl}/${id}`);
  }

}
