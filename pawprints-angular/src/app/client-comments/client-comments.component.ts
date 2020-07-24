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

@Component({
  selector: 'app-client-comments',
  templateUrl: './client-comments.component.html',
  styleUrls: ['./client-comments.component.scss'],
})
export class ClientCommentsComponent implements OnInit {
  isValid = false;
  user = this.authenticationService.userValue;
  comments: any[] = [];
  isConnected = false;
  commentForm: FormGroup = new FormGroup({
    comments: new FormControl(),
  });
  error = '';
  constructor(
    private toastr: ToastrService,
    private formBuilder: FormBuilder,
    private router: Router,
    private authenticationService: AuthenticationService,
    private http: HttpClient
  ) {}

  /**httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Basic ${this.authenticationService.userValue.authdata}`,
    }),
  };*/

  ngOnInit(): void {
    this.commentForm = this.formBuilder.group({
      comment: ['', [Validators.required, Validators.maxLength(255)]],
    });

    this.commentForm.valueChanges.subscribe((input) => {
      this.isValid = !this.commentForm.invalid;
    });

    this.http.get<any[]>(`${environment.apiUrl}/comments`).subscribe(
      (data) => {
        this.comments = data;
        console.log(data);
      },
      (error) => console.log(error)
    );
  }

  // convenience getter for easy access to form fields
  get coment() {
    return this.commentForm.controls;
  }

  onSubmitComment() {
    console.log(this.coment.comment.value);
    const coment = {
      comment: this.coment.comment.value,
      userId: this.authenticationService.userValue.id,
    };
    console.log(coment);
    this.comments.push(coment);
    console.log(this.comments);
    this.http.post<any>(`${environment.apiUrl}/comment`, coment).subscribe(
      (data) => {
        this.toastr.success(
          '<span class="tim-icons icon-bell-55" [data-notify]="icon"></span> Comment done!',
          '',
          {
            timeOut: 2000,
            enableHtml: true,
            toastClass: 'alert alert-success alert-with-icon',
            positionClass: 'toast-top-center',
          }
        );
        console.log(data);
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

    this.commentForm.reset();
  }

  public navigate(url) {
    this.router.navigate([url]);
  }
}
