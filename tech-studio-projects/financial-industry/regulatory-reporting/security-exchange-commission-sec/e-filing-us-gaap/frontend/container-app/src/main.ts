import { bootstrapApplication } from '@angular/platform-browser';
import { provideRouter } from '@angular/router';  // Provide routing support
import { routes } from './app/app.routes';  // Import the routes from app.routes.ts
import { AppComponent } from './app/app.component';  // Import AppComponent

bootstrapApplication(AppComponent, {
  providers: [provideRouter(routes)]  // Provide routes here
})
  .catch(err => console.error(err));
