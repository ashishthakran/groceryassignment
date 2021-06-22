import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {MaxPriceReportComponent} from "./grocery/component/max-price-report/max-price-report.component";
import {PriceTrendReportComponent} from "./grocery/component/price-trend-report/price-trend-report.component";
import {FileUploadComponent} from "./grocery/component/file-upload/file-upload.component";

const routes: Routes = [
  { path: '', redirectTo: 'upload-items', pathMatch: 'full' },
  { path: 'upload-items', component: FileUploadComponent },
  { path: 'reports/max-price', component: MaxPriceReportComponent },
  { path: 'reports/price-trend/:itemName', component: PriceTrendReportComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
