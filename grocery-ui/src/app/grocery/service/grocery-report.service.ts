import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {AppConstants} from "../../shared/AppConstants";

@Injectable({
  providedIn: 'root'
})
export class GroceryReportService {

  private readonly GROCERIES_BASE_URL = AppConstants.APP_BASE_URL + '/api/groceries';
  public readonly FILE_UPLOAD_URL = AppConstants.APP_BASE_URL + '/api/groceries/upload';

  constructor(private http: HttpClient) { }

  getMaxPriceReport(pageNo: number, pageSize: number): Observable<any> {
    return this.http.get(`${this.GROCERIES_BASE_URL}/reports/max-price?pageNo=${pageNo}&pageSize=${pageSize}`);
  }

  getPriceTrendReport(itemName: string): Observable<any> {
    return this.http.get(`${this.GROCERIES_BASE_URL}/reports/price-trend/${itemName}`);
  }
}
