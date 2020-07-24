import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-linage',
  templateUrl: './linage.component.html',
  styleUrls: ['./linage.component.scss'],
})
export class LinageComponent implements OnInit {
  constructor(private router: Router) {}

  ngOnInit(): void {}
  public navigate(url) {
    this.router.navigate([url]);
  }
}
