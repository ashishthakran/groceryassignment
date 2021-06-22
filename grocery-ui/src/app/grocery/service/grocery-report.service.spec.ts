import { TestBed } from '@angular/core/testing';

import { GroceryReportService } from './grocery-report.service';

describe('GroceryReportService', () => {
  let service: GroceryReportService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GroceryReportService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
