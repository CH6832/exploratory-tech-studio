import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';  // Use `of` to return mock data

@Injectable({
  providedIn: 'root'
})
export class FinancialReportService {
  getReports(): Observable<any[]> {
    // Mock data for testing
    return of([
      { name: 'Report 1' },
      { name: 'Report 2' },
      { name: 'Report 3' }
    ]);
  }
}
