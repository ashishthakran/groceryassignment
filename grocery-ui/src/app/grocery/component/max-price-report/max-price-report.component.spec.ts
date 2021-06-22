import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MaxPriceReportComponent } from './max-price-report.component';

describe('MaxPriceReportComponent', () => {
  let component: MaxPriceReportComponent;
  let fixture: ComponentFixture<MaxPriceReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MaxPriceReportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MaxPriceReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
