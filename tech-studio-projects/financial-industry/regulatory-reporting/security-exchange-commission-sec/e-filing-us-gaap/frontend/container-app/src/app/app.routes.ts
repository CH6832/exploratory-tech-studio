import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// Import the components you want to route to
import { DashboardComponent } from './dashboard/dashboard.component';
import { FinancialReportsComponent } from './reports/financial-reports.component';
import { HomeComponent } from './home/home.component';
// @ts-ignore
import { NotFoundComponent } from './components/not-found/not-found.component';
import {AboutComponent} from './about/about.component';
import {ContactComponent} from './contact/contact.component';

export const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'financial-reports', component: FinancialReportsComponent },
  { path: 'about', component: AboutComponent },
  { path: 'contact', component: ContactComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)], // Register routes
  exports: [RouterModule]
})
export class AppRoutingModule { }
