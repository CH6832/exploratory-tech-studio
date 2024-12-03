import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';  // Ensure this path is correct
import { FinancialReportsComponent } from './reports/financial-reports.component';  // Correct path
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component'

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },  // Default redirect
  { path: 'dashboard', component: DashboardComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' },  // Default redirect
  { path: 'home', component: HomeComponent }, // Define the home route
  { path: '', redirectTo: '/financial-reports', pathMatch: 'full' },  // Default redirect
  { path: 'financial-reports', component: FinancialReportsComponent },
  { path: '', redirectTo: '/about', pathMatch: 'full' },  // Default redirect
  { path: 'about', component: AboutComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
