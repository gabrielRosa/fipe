import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { first } from 'rxjs/operators';

import { BrandService, CarService } from 'src/app/service';
import { Brand, Car, CarHistory } from 'src/app/model';

@Component({
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  brandForm: FormGroup;
  carForm: FormGroup;
  brandArr: Brand[];
  carArr: Car[];
  currentBrand: string = null;
  currentCar: string = null;
  carHistory: CarHistory[] = null;

  constructor(
    private formBuilder: FormBuilder,
    private brandService: BrandService,
    private carService: CarService
    ) { }

  ngOnInit() {
    this.brandFormGroup();
    this.carFormGroup();
    this.loadAllBrands();
  }

  brandFormGroup() {
    this.brandForm = this.formBuilder.group({
      brandSelect: new FormControl('')
    });
  }

  carFormGroup() {
    this.carForm = this.formBuilder.group({
      carSelect: new FormControl('')
    });
  }

  onCurrentBrandChange(event) {
    this.currentBrand = event.target.value;

    this.carService
      .findAllByBrandId(this.currentBrand)
      .pipe(first())
      .subscribe(data => this.carArr = data);
  }

  onCurrentCarChange(event) {
    this.currentCar = event.target.value;

    this.carService
      .findHistoryByIdAndModels(this.currentCar, this.currentBrand)
      .pipe(first())
      .subscribe(data => this.carHistory = data);

  }

  private loadAllBrands() {
    this.brandService
      .findAll()
      .pipe(first())
      .subscribe(data => this.brandArr = data);
  }

}
