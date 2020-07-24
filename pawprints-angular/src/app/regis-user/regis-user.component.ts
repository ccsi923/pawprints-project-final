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
import { AnimalType } from '../models/animalType';
import { User } from '../_models';
@Component({
  selector: 'app-regis-user',
  templateUrl: './regis-user.component.html',
  styleUrls: ['./regis-user.component.scss'],
})
export class RegisUserComponent implements OnInit {
  isValid = false;
  passwordMismatch = false;
  isShow = false;
  userForm: FormGroup = new FormGroup({
    userEmail: new FormControl(),
    username: new FormControl(),
    password: new FormControl(),
    confirm: new FormControl(),
    addressStreet: new FormControl(),
    addressZipCode: new FormControl(),
    addressCity: new FormControl(),
    addressCountry: new FormControl(),
    addressPhone: new FormControl(),
    mailingStreet: new FormControl(),
    mailingZipCode: new FormControl(),
    mailingCity: new FormControl(),
    mailingCountry: new FormControl(),
    mailingPhone: new FormControl(),
  });

  loading = false;
  returnUrl = '/shop';
  error = '';
  constructor(
    private toastr: ToastrService,
    private formBuilder: FormBuilder,
    private router: Router,
    private authenticationService: AuthenticationService,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.userForm = this.formBuilder.group({
      userEmail: [
        '',
        [
          Validators.required,
          Validators.pattern(
            /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
          ),
        ],
      ],
      username: [
        '',
        [
          Validators.required,
          Validators.maxLength(20),
          Validators.minLength(4),
        ],
      ],
      password: ['', [Validators.required, Validators.minLength(4)]],
      confirm: ['', [Validators.required, Validators.minLength(4)]],
      addressStreet: ['', [Validators.required]],
      addressZipCode: ['', [Validators.required]],
      addressCity: ['', [Validators.required]],
      addressCountry: ['', [Validators.required]],
      addressPhone: [
        '',
        [Validators.required, Validators.pattern('^(6|7|9){1}([0-9]){8}$')],
      ],
      mailingStreet: [''],
      mailingZipCode: [''],
      mailingCity: [''],
      mailingCountry: [''],
      mailingPhone: ['', Validators.pattern('^(6|7|9){1}([0-9]){8}$')],
    });

    this.userForm.valueChanges.subscribe(() => {
      if (this.password.value !== this.confirm.value) {
        this.passwordMismatch = true;
      } else {
        this.passwordMismatch = false;
      }
      this.isValid = !this.userForm.invalid;
    });
  }
  get password() {
    return this.userForm.get('password');
  }

  get confirm() {
    return this.userForm.get('confirm');
  }

  // convenience getter for easy access to form fields
  get f() {
    return this.userForm.controls;
  }
  toggleDisplay() {
    this.isShow = !this.isShow;
    console.log(this.isShow);
  }

  onSubmit() {
    const userRequest = {
      userEmail: this.f.userEmail.value,
      username: this.f.username.value,
      password: this.f.password.value,
      primaryAddress: {
        addressStreet: this.f.addressStreet.value,
        addressZipCode: this.f.addressZipCode.value,
        addressCity: this.f.addressCity.value,
        addressCountry: this.f.addressCountry.value,
        addressPhone: this.f.addressPhone.value,
      },
      mailingAddress: {
        addressStreet: this.f.mailingStreet.value,
        addressZipCode: this.f.mailingZipCode.value,
        addressCity: this.f.mailingCity.value,
        addressCountry: this.f.mailingCountry.value,
        addressPhone: this.f.mailingPhone.value,
      },
    };
    this.http.post<User>(`${environment.apiUrl}/client`, userRequest).subscribe(
      (data) => {
        this.toastr.success(
          '<span class="tim-icons icon-bell-55" [data-notify]="icon"></span> User created!',
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
  }
}
