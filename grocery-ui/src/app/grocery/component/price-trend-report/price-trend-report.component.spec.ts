import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PriceTrendReportComponent } from './price-trend-report.component';

describe('PriceTrendReportComponent', () => {
  let component: PriceTrendReportComponent;
  let fixture: ComponentFixture<PriceTrendReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PriceTrendReportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PriceTrendReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
