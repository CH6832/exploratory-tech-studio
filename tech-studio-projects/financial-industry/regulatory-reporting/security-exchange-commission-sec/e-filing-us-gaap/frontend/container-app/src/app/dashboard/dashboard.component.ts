import {AfterViewInit, Component, OnInit} from '@angular/core';
import Chart from 'chart.js/auto';
import { CommonModule } from '@angular/common';  // CommonModule for directives like *ngIf and *ngFor
import { RouterModule } from '@angular/router';  // Import RouterModule for routing

@Component({
  selector: 'app-dashboard',
  standalone: true,  // Standalone component
  imports: [CommonModule, RouterModule],  // Import necessary modules like CommonModule
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})

/*
export class DashboardComponent implements OnInit {

  constructor() {}

  ngOnInit(): void {
    console.log("Dashboard component loaded");
  }
}
*/

export class DashboardComponent implements AfterViewInit {
  ngAfterViewInit(): void {
    this.createPieChart();
    this.createBarChart();
  }

  createPieChart() {
    const pieCtx = document.getElementById('pieChart') as HTMLCanvasElement;
    new Chart(pieCtx, {
      type: 'pie',
      data: {
        labels: ['Approved', 'Pending', 'Rejected'],
        datasets: [
          {
            data: [30, 15, 5], // Example data
            backgroundColor: ['#4caf50', '#ffc107', '#f44336'],
          },
        ],
      },
      options: {
        plugins: {
          legend: {
            display: true,
            position: 'bottom',
          },
        },
      },
    });
  }

  createBarChart() {
    const barCtx = document.getElementById('barChart') as HTMLCanvasElement;
    new Chart(barCtx, {
      type: 'bar',
      data: {
        labels: ['January', 'February', 'March', 'April', 'May'], // Example months
        datasets: [
          {
            label: 'Reports Created',
            data: [5, 10, 15, 20, 25], // Example data
            backgroundColor: '#4caf50',
          },
        ],
      },
      options: {
        scales: {
          x: {
            title: {
              display: true,
              text: 'Months',
            },
          },
          y: {
            title: {
              display: true,
              text: 'Number of Reports',
            },
            beginAtZero: true,
          },
        },
      },
    });
  }
}

