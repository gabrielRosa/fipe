import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Car, CarHistory } from '../model';

@Injectable({ providedIn: 'root' })
export class CarService {
  constructor(private http: HttpClient) { }

  public findAllByBrandId(brandId: string) {
    return this.http.get<Car[]>(`api/car/all/${brandId}`);
  }

  public findHistoryByIdAndModels(carId: string, brandId: string) {
    const params = new HttpParams().set('brandId', brandId);

    return this.http.get<CarHistory[]>(`api/car/${carId}`, { params });
  }

}
