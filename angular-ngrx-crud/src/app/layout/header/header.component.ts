import { ChangeDetectionStrategy, Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class HeaderComponent implements OnInit {

  @Output() menuToggled = new EventEmitter<boolean>();

  user: string = 'Enea';

  // constructor(private authService: AuthService) { }

  logout(): void {
    console.log('Logged out');
  }

  constructor() { }

  ngOnInit() {
  }

}
