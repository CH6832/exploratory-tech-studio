import { Component, OnInit } from '@angular/core';

/**
 * About Component for displaying platform information, mission, vision, and key features.
 * This is a static component that provides basic details about the service.
 */
@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.scss']
})
export class AboutComponent implements OnInit {

  // Optionally, we can define properties for dynamic data binding
  mission: string = "Our mission is to provide a seamless and secure platform for businesses to manage their financial filings.";
  vision: string = "We envision becoming the most trusted platform for financial reporting worldwide.";
  features: string[] = [
    "Automated Filings: Effortlessly submit your financial reports with automation.",
    "Real-Time Status Tracking: Track your reports’ status in real-time.",
    "Secure Platform: Protect sensitive data with encryption.",
    "Comprehensive Dashboards: Get insights and trends through detailed dashboards.",
    "User-Friendly Interface: Intuitive design for easier reporting."
  ];

  constructor() { }

  ngOnInit(): void {
    // This is where we can fetch dynamic content if needed from a service or API.
    // For now, the data is static, but you could easily replace it with dynamic data later.
  }

}
