import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Brand } from '../model';

@Injectable({ providedIn: 'root' })
export class BrandService {
  constructor(private http: HttpClient) { }

  public findAll() {
    return this.http.get<Brand[]>('api/brand');
  }

}
