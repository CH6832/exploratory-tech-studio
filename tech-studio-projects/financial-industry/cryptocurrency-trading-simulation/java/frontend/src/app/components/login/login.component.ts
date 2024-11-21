// src/app/components/login/login.component.ts
import { Component } from '@angular/core';
import { UserService } from '../../services/user.service';
import { Router } from '@angular/router';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  imports: [
    FormsModule
  ],
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private userService: UserService, private router: Router) {}

  onSubmit(): void {
    this.userService.authenticateUser(this.username, this.password).subscribe(
      (response) => {
        // Store the token or user data in localStorage/sessionStorage
        localStorage.setItem('user', JSON.stringify(response));
        this.router.navigate(['/profile']);
      },
      (error) => {
        this.errorMessage = 'Invalid credentials';
      }
    );
  }
}
