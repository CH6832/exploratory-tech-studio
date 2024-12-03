import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';  // Import routing module
import { AppComponent } from './app.component';            // Import the main app component
import { SidebarComponent } from './sidebar/sidebar.component'; // Import sidebar component
import { HeaderComponent } from './header/header.component';   // Import header component
import { DashboardComponent } from './dashboard/dashboard.component';
import {FooterComponent} from './footer/footer.component';
import {CommonModule} from '@angular/common';  // Import dashboard component
import { HomeComponent } from './home/home.component'; // Import the HomeComponent
import { AboutComponent } from './about/about.component';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,  // Declare it here
    HomeComponent,
    AboutComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    SidebarComponent,
    FooterComponent,
    AppRoutingModule,
    HeaderComponent,
    AboutComponent
    // Add CommonModule here
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
