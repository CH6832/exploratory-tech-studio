import {Component} from '@angular/core';
import {HeaderComponent} from './header/header.component';
import {SidebarComponent} from './sidebar/sidebar.component';
import {RouterModule, RouterOutlet} from '@angular/router';
import {FooterComponent} from './footer/footer.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  imports: [
    RouterModule,
    SidebarComponent,
    RouterOutlet,
    FooterComponent
  ],
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'US-GAAP E-Filing App';
}
