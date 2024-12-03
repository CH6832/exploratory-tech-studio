import { Component } from '@angular/core';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  imports: [
    NgIf
  ],
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent {
  // To track the state of the sidebar (collapsed or expanded)
  isCollapsed = false;

  // Method to toggle the sidebar's collapsed state
  toggleSidebar() {
    this.isCollapsed = !this.isCollapsed;
  }
}
