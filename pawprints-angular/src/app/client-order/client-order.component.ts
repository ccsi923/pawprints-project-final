import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { AuthenticationService } from '../_services';
import { OrderStatus } from '../models/orderStatus';
import { OrderClient } from '../models/orderClient';
import { ProductOrderType } from '../models/productOrderType';
import { environment } from 'src/environments/environment';
import { ToastrService } from 'ngx-toastr';
import { OrderLineRequest } from '../models/orderLineRequest';
@Component({
  selector: 'app-client-order',
  templateUrl: './client-order.component.html',
  styleUrls: ['./client-order.component.scss'],
})
export class ClientOrderComponent implements OnInit {
  // status = new FormControl(['']);

  statusList: OrderStatus[] = [
    OrderStatus.Open,
    OrderStatus.SentInit,
    OrderStatus.ReceivedInit,
    OrderStatus.SentResult,
    OrderStatus.ReOpen,
    OrderStatus.Closed,
  ];

  ordersKits: ProductOrderType[] = [
    ProductOrderType.Init,
    ProductOrderType.Result,
  ];

  clientOrders: OrderClient[] = [];

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
      .get<OrderClient[]>(`${environment.apiUrl}/orders`, this.httpOptions)
      .subscribe((data) => {
        console.log(data);
        this.clientOrders = data;
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
  sumTotal(stockOrderLineList: OrderLineRequest[]): number {
    let total = 0;
    stockOrderLineList.forEach(function (value) {
      console.log(value.requestedQuantity);
      total += value.requestedQuantity;
    });
    return total;
  }
  sendKit(
    id: number,
    kitSend: ProductOrderType,
    stockOrderLineList: OrderLineRequest[]
  ) {
    let quantity = this.sumTotal(stockOrderLineList);
    console.log(id, kitSend, quantity);
    this.http
      .put<void>(
        `${environment.apiUrl}/makekit/producttype/${kitSend}/${quantity}`,
        null,
        this.httpOptions
      )
      .subscribe(
        (data) => {
          console.log('kits created');
          this.toastr.success(
            '<span class="tim-icons icon-bell-55" [data-notify]="icon"></span> Order of kits created!',
            '',
            {
              timeOut: 2000,
              enableHtml: true,
              toastClass: 'alert alert-success alert-with-icon',
              positionClass: 'toast-top-center',
            }
          );
          let updatedKit = this.clientOrders.find((order) => order.id === id);
          updatedKit.kitStatus = undefined;
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
  }

  openUpdate(id: number, orderS: OrderStatus): void {
    console.log(id, orderS);
    const orderStatus = {
      orderStatus: orderS,
    };
    this.http
      .put<void>(
        `${environment.apiUrl}/order/update/status/${id}`,
        orderStatus,
        this.httpOptions
      )
      .subscribe(
        (data) => {
          console.log('updated');
          this.toastr.success(
            '<span class="tim-icons icon-bell-55" [data-notify]="icon"></span> Order updated!',
            '',
            {
              timeOut: 2000,
              enableHtml: true,
              toastClass: 'alert alert-success alert-with-icon',
              positionClass: 'toast-top-center',
            }
          );
          let updatedKit = this.clientOrders.find((order) => order.id === id);
          updatedKit.kitStatus = undefined;

          let updatedOrder = this.clientOrders.find((order) => order.id === id);
          updatedOrder.status = orderS;
          updatedOrder.newStatus = undefined;
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
