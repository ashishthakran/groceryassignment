import { Component, OnInit } from '@angular/core';
import {AppConstants} from "../../../shared/AppConstants";
import {IGetRowsParams, Module} from '@ag-grid-community/core';
import {InfiniteRowModelModule} from "@ag-grid-community/all-modules";
import {GroceryReportService} from "../../service/grocery-report.service";
import {Router} from "@angular/router";
import {Grocery} from "../../model/Grocery";

@Component({
  selector: 'app-max-price-report',
  templateUrl: './max-price-report.component.html',
  styleUrls: ['./max-price-report.component.css']
})
export class MaxPriceReportComponent implements OnInit {
  gridApi: any;
  gridColumnApi: any;

  modules: Module[] = [InfiniteRowModelModule];
  columnDefs = [
    { field: 'name', headerName: 'Name', sortable: false, filter: false, cellRenderer: this.createItemNameLink.bind(this) },
    { field: 'price', headerName: 'Max Price', sortable: false, filter: false },
    { field: 'priceDate', headerName: 'Date', sortable: false, filter: false }
  ];
  defaultColDef = {
    flex: 1,
    minWidth: 90,
    resizable: true
  };
  components = {
    loadingRenderer: function(params: any) {
      if(params.value !== undefined) {
        return params.value;
      }
      else {
        return "<img src=\"https://www.ag-grid.com/example-assets/loading.gif\">";
      }
    }
  };
  rowBuffer: number;
  rowSelection: string;
  rowModelType: string;
  paginationPageSize: number;
  cacheBlockSize: number;
  cacheOverflowSize: number;
  maxConcurrentDatasourceRequests: number;
  infiniteInitialRowCount: number;
  maxBlocksInCache: number;
  rowData!: Grocery[];

  constructor(private groceryReportService: GroceryReportService, private router: Router) {
    this.rowBuffer = 0;
    this.rowSelection = 'multiple';
    this.rowModelType = 'infinite';
    this.paginationPageSize = AppConstants.GRID_DEFAULT_PAGE_SIZE;
    this.cacheBlockSize = AppConstants.GRID_DEFAULT_PAGE_SIZE;
    this.cacheOverflowSize = 2;
    this.maxConcurrentDatasourceRequests = 1;
    this.infiniteInitialRowCount = 1000;
    this.maxBlocksInCache = 20;
  }

  ngOnInit() {
  }

  createItemNameLink(params: any): any {
    const element = document.createElement('a');
    element.innerHTML = params.value;
    element.setAttribute("href", "javascript:void(0);")
    element.addEventListener('click', () => {
      this.router.navigate(['reports/price-trend', params.value])
    });
    return element;
  }

  onGridReady(params: any) {
    this.gridApi = params.api;
    this.gridColumnApi = params.columnApi;
    console.log("onGridReady");
    let datasource = {
      getRows: (params: IGetRowsParams) => {
        console.info("Getting datasource rows, start: " + params.startRow + ", end: " + params.endRow);
        let pageNo = (params.endRow / this.paginationPageSize) - 1;

        this.groceryReportService.getMaxPriceReport(pageNo, this.paginationPageSize)
          .subscribe(response => {
            let lastRow = -1;
            if (response.data.totalCount <= params.endRow) {
              lastRow = response.data.totalCount;
            }
            params.successCallback(response.data.groceries, lastRow);
          });
      }
    };
    params.api.setDatasource(datasource);
  }
}
