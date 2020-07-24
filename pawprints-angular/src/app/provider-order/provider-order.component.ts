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
import { first } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { ToastrService } from 'ngx-toastr';
import { ProviderProduct } from '../models/providerProduct';
import { OrderLineRequest } from '../models/orderLineRequest';
import { ProviderOrder } from '../models/providerOrderRequest';

@Component({
  selector: 'app-provider-order',
  templateUrl: './provider-order.component.html',
  styleUrls: ['./provider-order.component.scss'],
})
export class ProviderOrderComponent implements OnInit {
  isValid = true;
  isProviderValid = false;
  orderLines: OrderLineRequest[] = [];
  providerProducts: ProviderProduct[] = [];
  products: any[] = [];
  loading = false;
  returnUrl = '/shop';
  quantityTotal = 0;

  providerForm: FormGroup = new FormGroup({
    comments: new FormControl(),
  });
  orderLineForm: FormGroup = new FormGroup({
    productId: new FormControl(),
    requestedQuantity: new FormControl(),
  });
  error = '';

  constructor(
    private toastr: ToastrService,
    private formBuilder: FormBuilder,
    private router: Router,
    private authenticationService: AuthenticationService,
    private http: HttpClient
  ) {}

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Basic ${this.authenticationService.userValue.authdata}`,
    }),
  };

  ngOnInit(): void {
    this.orderLineForm = this.formBuilder.group({
      productId: ['', Validators.required],
      requestedQuantity: ['', [Validators.required, Validators.min(1)]],
    });

    this.providerForm = this.formBuilder.group({
      comments: [''],
    });

    this.orderLineForm.valueChanges.subscribe((input) => {
      this.isProviderValid = !this.orderLineForm.invalid;
    });

    this.http
      .get<ProviderProduct[]>(
        `${environment.apiUrl}/products`,
        this.httpOptions
      )
      .subscribe(
        (data) => {
          this.providerProducts = data;
          console.log(data);
        },
        (error) => console.log(error)
      );
  }

  // convenience getter for easy access to form fields
  get f() {
    return this.providerForm.controls;
  }
  get prod() {
    return this.orderLineForm.controls;
  }

  deleteProduct(elementId: number): void {
    this.products = this.products.filter(
      (product) => elementId !== product.elementId
    );
  }

  onSubmitProduct() {
    console.log(this.prod.productId.value);
    console.log(this.prod.requestedQuantity.value);
    const product = {
      elementId: this.quantityTotal,
      productId: this.prod.productId.value,
      requestedQuantity: this.prod.requestedQuantity.value,
    };
    console.log(product);
    this.products.push(product);
    console.log(this.products);
    this.quantityTotal++;
    this.orderLineForm.reset();
  }

  onSubmit() {
    const providerProductOrder = {
      comments: this.f.comments.value,
      providerOrderLines: this.products,
    };
    this.http
      .post<ProviderOrder>(
        `${environment.apiUrl}/providerorder`,
        providerProductOrder,
        this.httpOptions
      )
      .subscribe(
        (data) => {
          this.toastr.success(
            '<span class="tim-icons icon-bell-55" [data-notify]="icon"></span> Provider order done!',
            '',
            {
              timeOut: 2000,
              enableHtml: true,
              toastClass: 'alert alert-success alert-with-icon',
              positionClass: 'toast-top-center',
            }
          );
          console.log(data), this.router.navigate([this.returnUrl]);
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
    this.quantityTotal = 0;
  }
}
