import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {
  FormBuilder,
  FormGroup,
  Validators,
  FormControl,
} from '@angular/forms';
import { AuthenticationService } from '../_services';
import { first } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup = new FormGroup({
    username: new FormControl(),
    password: new FormControl(),
  });
  invalidLogin = false;
  error = '';
  loading = false;
  returnUrl = '/shop';
  disabled = false;

  constructor(
    private toastr: ToastrService,
    private formBuilder: FormBuilder,
    private router: Router,
    private authenticationService: AuthenticationService
  ) {}

  ngOnInit() {
    if (this.authenticationService.userValue) {
      this.router.navigate(['/shop']);
    }
    // tslint:disable-next-line: member-ordering
    const signUpButton = document.getElementById('signUp');
    // tslint:disable-next-line: member-ordering
    const signInButton = document.getElementById('signIn');
    // tslint:disable-next-line: member-ordering
    const container = document.getElementById('container');
    // tslint:disable-next-line: semicolon
    signUpButton.addEventListener('click', () => {
      container.classList.add('right-panel-active');
      this.loginForm = this.formBuilder.group({
        username: ['', Validators.required],
        password: ['', Validators.required],
      });
    });

    signInButton.addEventListener('click', () => {
      container.classList.remove('right-panel-active');
    });
  }

  // convenience getter for easy access to form fields
  get f() {
    return this.loginForm.controls;
  }

  onSubmit() {
    console.log('entering');
    this.disabled = true;
    if (this.loginForm.invalid) {
      this.disabled = false;
      return;
    }

    this.loading = true;
    this.authenticationService
      .login(this.f.username.value, this.f.password.value)
      .pipe(first())
      .subscribe(
        (data) => {
          this.router.navigate([this.returnUrl]);
          this.toastr.success(
            '<span class="tim-icons icon-bell-55" [data-notify]="icon"></span> Welcome to <b> PAWPRRINTS </b> - a beautiful CRM.',
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
          this.router.navigate(['/login']);
          this.error = error;
          this.toastr.error(
            `<span class="tim-icons icon-bell-55" [data-notify]="icon"></span> Invalid Username or Password combination`,
            '',
            {
              timeOut: 2000,
              enableHtml: true,
              toastClass: 'alert alert-danger alert-with-icon',
              positionClass: 'toast-top-center',
            }
          );
          this.disabled = false;
          this.loading = false;
        }
      );
  }

  navigateTo(url) {
    this.router.navigate([url]);
  }

  /*
  checkLogin() {
    if (this.loginservice.authenticate(this.username, this.password)
    ) {
      this.router.navigate(['']);
      this.invalidLogin = false;
    } else {
      this.invalidLogin = true;
    }
  }
  */
}
