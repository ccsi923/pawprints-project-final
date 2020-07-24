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
import { Animal } from '../models/animal';
import { Cart } from '../models/cart';
import { Product } from '../models/product';
import { PaymentType } from '../models/paymentType';
import { ProductType } from '../models/productType';
@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss'],
})
export class CartComponent implements OnInit {
  isValid = false;
  isProductValid = false;
  products: any[] = [];
  animals: Animal[] = [];
  loading = false;
  returnUrl = '/shop';
  quantityTotal = 0;
  authData = null;
  user = this.authenticationService.userValue;
  productTypes: ProductType[] = [
    ProductType.Lineage,
    ProductType.Health,
    ProductType.Pawprints,
  ];
  paymentTypes: PaymentType[] = [
    PaymentType.American,
    PaymentType.CreditCard,
    PaymentType.Paypal,
    PaymentType.Transfer,
  ];
  cartForm: FormGroup = new FormGroup({
    animalId: new FormControl(),
    paymentType: new FormControl(),
    comments: new FormControl(),
  });
  productForm: FormGroup = new FormGroup({
    productType: new FormControl(),
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
      Authorization: `Basic ${this.authenticationService.userValue?.authdata}`,
    }),
  };

  ngOnInit(): void {
    this.productForm = this.formBuilder.group({
      productType: ['', Validators.required],
      requestedQuantity: ['', [Validators.required, Validators.min(1)]],
    });

    this.cartForm = this.formBuilder.group({
      animalId: ['', Validators.required],
      paymentType: ['', [Validators.required]],
      comments: ['', Validators.required],
    });

    this.productForm.valueChanges.subscribe((input) => {
      this.isProductValid = !this.productForm.invalid;
    });

    this.cartForm.valueChanges.subscribe((input) => {
      this.isValid = !this.cartForm.invalid;
    });
    if (this.user != null) {
      this.http
        .get<Animal[]>(`${environment.apiUrl}/animals/owner`, this.httpOptions)
        .subscribe(
          (data) => {
            this.animals = data;
            console.log(data);
          },
          (error) => console.log(error)
        );
    }
  }

  // convenience getter for easy access to form fields
  get f() {
    return this.cartForm.controls;
  }
  get prod() {
    return this.productForm.controls;
  }

  deleteProduct(elementId: number): void {
    this.products = this.products.filter(
      (product) => elementId !== product.elementId
    );
  }

  onSubmitProduct() {
    console.log(this.prod.productType.value);
    console.log(this.prod.requestedQuantity.value);
    const product = {
      elementId: this.quantityTotal,
      productType: this.prod.productType.value,
      requestedQuantity: this.prod.requestedQuantity.value,
    };
    console.log(product);
    this.products.push(product);
    console.log(this.products);
    this.quantityTotal++;
    this.productForm.reset();
  }

  onSubmit() {
    const cart = {
      animalId: this.f.animalId.value,
      paymentType: this.f.paymentType.value,
      comments: this.f.comments.value,
      products: this.products,
    };
    this.http
      .post<Cart>(`${environment.apiUrl}/cart`, cart, this.httpOptions)
      .subscribe(
        (data) => {
          this.toastr.success(
            '<span class="tim-icons icon-bell-55" [data-notify]="icon"></span> Shopping done!',
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
  navigate(url) {
    this.router.navigate([url]);
  }
}
