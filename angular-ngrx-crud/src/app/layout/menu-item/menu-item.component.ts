import { Component, Input, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { Menu } from '../menu.model';

@Component({
  selector: 'app-menu-item',
  templateUrl: './menu-item.component.html',
  styleUrls: ['./menu-item.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class MenuItemComponent implements OnInit {

  @Input() menu: Menu= [];
  constructor() { }

  ngOnInit() {
  }

}
