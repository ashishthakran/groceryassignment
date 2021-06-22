import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {GroceryReportService} from "../../service/grocery-report.service";
import {DatePipe} from "@angular/common";
import {Chart} from 'chart.js'

@Component({
  selector: 'app-price-trend-report',
  templateUrl: './price-trend-report.component.html',
  styleUrls: ['./price-trend-report.component.css']
})
export class PriceTrendReportComponent implements OnInit {
  canvas: any;
  ctx: any;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private datePipe: DatePipe,
              private groceryReportService: GroceryReportService) {}

  ngOnInit() {}

  ngAfterViewInit() {
     let itemName = this.route.snapshot.params['itemName'];

    this.groceryReportService.getPriceTrendReport(itemName).subscribe(response => {
      let priceTrendList = response.data.priceTrendList
      var priceList = [];
      var priceDateList = [];

      for(let priceTrend of priceTrendList) {
        priceList.push(priceTrend.price);
        let formattedDate = this.datePipe.transform(priceTrend.priceDate, 'yyyy-MM-dd');
        priceDateList.push(formattedDate!);
      }
      this.renderTrendChart(priceList, priceDateList);
    });
  }

  renderTrendChart(priceList: number[], priceDateList: string[]) {
    this.canvas = document.getElementById('price-trend-grid');
    this.ctx = this.canvas.getContext('2d');
    let myChart = new Chart(this.ctx, {
      type: 'line',
      data: {
        labels: priceDateList,
        datasets: [{
          label: 'Price Trend',
          data: priceList,
          backgroundColor: [
            'rgba(255, 99, 132, 1)',
            'rgba(54, 162, 235, 1)',
            'rgba(255, 206, 86, 1)'
          ],
          borderWidth: 1
        }]
      },
      options: {
        responsive: false
      }
    });
  }
}
