import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {
  title = 'Welcome to the Home Page';
  message = 'This is the home page of the application!';

  constructor() { }

  // Any methods you want to use in the component can go here.
  // For example, a method to change the message:
  changeMessage() {
    this.message = 'You just clicked the button!';
  }
}
