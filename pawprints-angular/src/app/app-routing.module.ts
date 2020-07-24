import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StatsReportComponent } from './stats-report/stats-report.component';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './_helpers';
import { HealthComponent } from './health/health.component';
import { LinageComponent } from './linage/linage.component';
import { PawprintsComponent } from './pawprints/pawprints.component';
import { CartComponent } from './cart/cart.component';
import { ShopComponent } from './shop/shop.component';
import { ClientOrdersComponent } from './client-orders/client-orders.component';
import { RegisAnimalComponent } from './regis-animal/regis-animal.component';
import { RegisUserComponent } from './regis-user/regis-user.component';
import { ProviderOrderComponent } from './provider-order/provider-order.component';
import { ClientOrderComponent } from './client-order/client-order.component';
import { IncomesComponent } from './incomes/incomes.component';
import { ExpensesComponent } from './expenses/expenses.component';
import { ReportClientProductOrderedComponent } from './report-client-product-ordered/report-client-product-ordered.component';
import { ClientCommentsComponent } from './client-comments/client-comments.component';
const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'shop',
  },
  { path: 'login', component: LoginComponent },
  {
    path: 'admin/order/status',
    component: StatsReportComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'helath',
    component: HealthComponent,
    pathMatch: 'full',
  },
  {
    path: 'linage',
    component: LinageComponent,
    pathMatch: 'full',
  },
  {
    path: 'pawprints',
    component: PawprintsComponent,
    pathMatch: 'full',
  },
  {
    path: 'cart',
    component: CartComponent,
    pathMatch: 'full',
  },
  {
    path: 'shop',
    component: ShopComponent,
    pathMatch: 'full',
  },
  {
    path: 'client/orders',
    component: ClientOrdersComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'register/animal',
    component: RegisAnimalComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'register',
    component: RegisUserComponent,
    pathMatch: 'full',
  },
  {
    path: 'admin/provider/orders',
    component: ProviderOrderComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'admin/client/orders',
    component: ClientOrderComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'admin/incomes',
    component: IncomesComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'admin/expenses',
    component: ExpensesComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'admin/client/product',
    component: ReportClientProductOrderedComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'client/comments',
    component: ClientCommentsComponent,
    pathMatch: 'full',
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
