import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BasicAuthInterceptor, ErrorInterceptor } from './_helpers';
import { ToastrModule } from 'ngx-toastr';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StatsReportComponent } from './stats-report/stats-report.component';
import { LoginComponent } from './login/login.component';
import { LayoutComponent } from './layout/layout.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { NavbarComponent } from './navbar/navbar.component';
import { ShopComponent } from './shop/shop.component';
import { HealthComponent } from './health/health.component';
import { LinageComponent } from './linage/linage.component';
import { PawprintsComponent } from './pawprints/pawprints.component';
import { CartComponent } from './cart/cart.component';
import { RegisAnimalComponent } from './regis-animal/regis-animal.component';
import { ClientOrdersComponent } from './client-orders/client-orders.component';
import { RegisUserComponent } from './regis-user/regis-user.component';
import { ProviderOrderComponent } from './provider-order/provider-order.component';
import { ClientOrderComponent } from './client-order/client-order.component';
import { IncomesComponent } from './incomes/incomes.component';
import { ExpensesComponent } from './expenses/expenses.component';
import { ReportClientProductOrderedComponent } from './report-client-product-ordered/report-client-product-ordered.component';
import { ClientCommentsComponent } from './client-comments/client-comments.component';

@NgModule({
  declarations: [
    AppComponent,
    StatsReportComponent,
    LoginComponent,
    LayoutComponent,
    SidebarComponent,
    NavbarComponent,
    ShopComponent,
    HealthComponent,
    LinageComponent,
    PawprintsComponent,
    CartComponent,
    RegisAnimalComponent,
    ClientOrdersComponent,
    RegisUserComponent,
    ProviderOrderComponent,
    ClientOrderComponent,
    IncomesComponent,
    ExpensesComponent,
    ReportClientProductOrderedComponent,
    ClientCommentsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    ToastrModule.forRoot(),
    BrowserAnimationsModule,
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: BasicAuthInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
