import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  FormControl,
} from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from '../_services';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { ToastrService } from 'ngx-toastr';
import { ProviderOrder } from '../models/providerOrderRequest';
import { OrderReceptionStatus } from '../models/orderReceptionStatus';

@Component({
  selector: 'app-accept-provider-order',
  templateUrl: './accept-provider-order.component.html',
  styleUrls: ['./accept-provider-order.component.scss'],
})
export class AcceptProviderOrderComponent implements OnInit {
  // status = new FormControl(['']);

  providerOrders: ProviderOrder[] = [];
  full: OrderReceptionStatus = OrderReceptionStatus.Full;
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Basic ${this.authenticationService.userValue.authdata}`,
    }),
  };

  constructor(
    private authenticationService: AuthenticationService,
    private http: HttpClient,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.http
      .get<ProviderOrder[]>(
        `${environment.apiUrl}/providerorders`,
        this.httpOptions
      )
      .subscribe((data) => {
        console.log(data);
        this.providerOrders = data;
      });

    /*this.status.valueChanges.subscribe((newStatus) => {
      console.log(newStatus);

    });*/
  }
  /*
  catchSelect() {
    // Pasamos el valor seleccionado a la variable verSeleccion
    this.selected = this.varSelected;
    console.log(this.selected, this.varSelected);
  }
    */
  updateOrder(id: number): void {
    console.log(id);

    this.http
      .put<void>(
        `${environment.apiUrl}/update/purchaseunits/order/${id}`,
        null,
        this.httpOptions
      )
      .subscribe(
        (data) => {
          console.log('updated');
          let updatedOrderStatu = this.providerOrders.find(
            (prov) => prov.id === id
          );
          updatedOrderStatu.status = this.full;
          this.toastr.success(
            '<span class="tim-icons icon-bell-55" [data-notify]="icon"></span> Order provider updated!',
            '',
            {
              timeOut: 2000,
              enableHtml: true,
              toastClass: 'alert alert-success alert-with-icon',
              positionClass: 'toast-top-center',
            }
          );
        },
        (error) => {
          this.toastr.error(
            `<span class="tim-icons icon-bell-55" [data-notify]="icon"></span> ${error}`,
            '',
            {
              timeOut: 2000,
              enableHtml: true,
              toastClass: 'alert alert-danger alert-with-icon',
              positionClass: 'toast-top-center',
            }
          );
        }
      );
    /*
    this.clicked = true;
    this.opportunities.map(
      // tslint:disable-next-line: only-arrow-functions
      function (opp): void {
        console.log(opp);
        if (id === opp.id) {
          opp.opportunityStatus = status;
          console.log(opp);
        }
      }
    );
    */
  }
}
