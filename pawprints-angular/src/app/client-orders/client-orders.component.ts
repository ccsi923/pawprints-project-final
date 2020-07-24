import { Component, OnInit } from '@angular/core';
import { ClientOrder } from '../models/clientOrder';
import { AuthenticationService } from '../_services';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-client-orders',
  templateUrl: './client-orders.component.html',
  styleUrls: ['./client-orders.component.scss'],
})
export class ClientOrdersComponent implements OnInit {
  // status = new FormControl(['']);
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Basic ${this.authenticationService.userValue.authdata}`,
    }),
  };

  orders: ClientOrder[] = [];

  constructor(
    private authenticationService: AuthenticationService,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.http
      .get<ClientOrder[]>(
        `${environment.apiUrl}/order/${this.authenticationService.userValue.id}`,
        this.httpOptions
      )
      .subscribe((data) => {
        console.log(data);
        this.orders = data;
      });

    /*this.status.valueChanges.subscribe((newStatus) => {
      console.log(newStatus);

    });*/
  }
}
