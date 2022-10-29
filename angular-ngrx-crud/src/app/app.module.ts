import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgxsModule } from '@ngxs/store';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './layout/header/header.component';
import { LayoutComponent } from './layout/layout.component';
import { MenuItemComponent } from './layout/menu-item/menu-item.component';
import { PageHeaderComponent } from './layout/page-header/page-header.component';
import { MaterialModule } from './material-module';
import { PharmacyComponent } from './pharmacy/pharmacy.component';
import { PharmacyState } from './pharmacy/store/pharmacy.state.ts';
import { UsersRoutingModule } from './user-routing.module';
import { HomeComponent } from './home/home.component';
import { SalesComponent } from './sales/sales.component';

@NgModule({
  declarations: [	
    AppComponent,
    PharmacyComponent,
    LayoutComponent,
    PageHeaderComponent,
    HeaderComponent,
    MenuItemComponent,
    HomeComponent,
      SalesComponent
   ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MaterialModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    NgxsModule.forRoot([PharmacyState]),
    UsersRoutingModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
