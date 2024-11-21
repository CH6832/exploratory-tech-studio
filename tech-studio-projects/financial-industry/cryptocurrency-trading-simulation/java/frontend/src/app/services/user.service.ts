// src/app/services/user.service.ts
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:8080/api/users'; // Your backend base URL

  constructor(private http: HttpClient) {}

  // User authentication method
  authenticateUser(username: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/authenticate`, { username, password });
  }

  // Get user profile by ID
  getUserById(userId: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/${userId}`);
  }

  // Create a new user
  createUser(user: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}`, user);
  }
}
