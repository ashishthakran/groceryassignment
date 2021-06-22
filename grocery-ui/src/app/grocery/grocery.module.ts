import { NgModule } from '@angular/core';

import { MaxPriceReportComponent } from './component/max-price-report/max-price-report.component';
import { PriceTrendReportComponent } from './component/price-trend-report/price-trend-report.component';
import {HttpClientModule} from "@angular/common/http";
import {AppRoutingModule} from "../app.routing.module";
import {AgGridModule} from "@ag-grid-community/angular";
import {CommonModule} from "@angular/common";
import { FileUploadComponent } from './component/file-upload/file-upload.component';
import {NgxSpinnerModule} from "ngx-spinner";
import {FileUploadModule} from "ng2-file-upload";

@NgModule({
  declarations: [
    MaxPriceReportComponent,
    PriceTrendReportComponent,
    FileUploadComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    AppRoutingModule,
    NgxSpinnerModule,
    FileUploadModule,
    AgGridModule.withComponents([])
  ],
  providers: []
})
export class GroceryModule { }
