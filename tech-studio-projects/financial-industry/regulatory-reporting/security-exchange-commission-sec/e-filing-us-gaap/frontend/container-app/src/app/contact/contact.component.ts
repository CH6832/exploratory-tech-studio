import { Component, OnInit } from '@angular/core';
import {FormsModule} from '@angular/forms';

/**
 * Contact Component for displaying the contact form.
 * The form allows users to send inquiries or feedback to the platform.
 */
@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  imports: [
    FormsModule
  ],
  styleUrls: ['./contact.component.scss']
})
export class ContactComponent implements OnInit {

  // Properties for form fields
  name: string = '';
  email: string = '';
  message: string = '';

  // Property for feedback messages
  successMessage: string = '';
  errorMessage: string = '';

  constructor() { }

  ngOnInit(): void {
    // Initialization logic (if any) can go here in the future
  }

  // Method to handle form submission
  onSubmit(): void {
    if (this.name && this.email && this.message) {
      // Simulate sending the form data to the backend (e.g., via HTTP request)
      // In a real-world application, you would use a service to send the data to a backend API.

      // Simulating a successful submission response.
      this.successMessage = 'Thank you for contacting us! We will get back to you soon.';
      this.errorMessage = '';  // Clear any previous error messages

      // Optionally clear the form after successful submission
      this.clearForm();
    } else {
      // Show error message if the form is incomplete
      this.errorMessage = 'Please fill in all fields before submitting.';
      this.successMessage = '';  // Clear any previous success messages
    }
  }

  // Helper method to clear the form fields
  clearForm(): void {
    this.name = '';
    this.email = '';
    this.message = '';
  }
}
