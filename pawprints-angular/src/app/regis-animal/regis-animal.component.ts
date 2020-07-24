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
import { AnimalType } from '../models/animalType';
import { Animal } from '../models/animal';
@Component({
  selector: 'app-regis-animal',
  templateUrl: './regis-animal.component.html',
  styleUrls: ['./regis-animal.component.scss'],
})
export class RegisAnimalComponent implements OnInit {
  isValid = false;
  animalForm: FormGroup = new FormGroup({
    name: new FormControl(),
    animal: new FormControl(),
    age: new FormControl(),
  });
  animalTypes: AnimalType[] = [
    AnimalType.Cat,
    AnimalType.Dog,
    AnimalType.Horse,
  ];
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

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Basic ${this.authenticationService.userValue.authdata}`,
    }),
  };

  ngOnInit(): void {
    this.animalForm = this.formBuilder.group({
      name: ['', Validators.required],
      animal: ['', [Validators.required]],
      age: ['', [Validators.required]],
    });

    this.animalForm.valueChanges.subscribe((input) => {
      this.isValid = !this.animalForm.invalid;
    });
  }

  // convenience getter for easy access to form fields
  get f() {
    return this.animalForm.controls;
  }

  onSubmit() {
    const animalRequest = {
      name: this.f.name.value,
      animal: this.f.animal.value,
      age: this.f.age.value,
    };
    this.http
      .post<Animal>(
        `${environment.apiUrl}/animal`,
        animalRequest,
        this.httpOptions
      )
      .subscribe(
        (data) => {
          this.toastr.success(
            '<span class="tim-icons icon-bell-55" [data-notify]="icon"></span> Animal created!',
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
