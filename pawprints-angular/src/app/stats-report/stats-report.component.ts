import { Component, OnInit } from '@angular/core';
import { Chart } from 'chart.js';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthenticationService } from '../_services';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-stats-report',
  templateUrl: './stats-report.component.html',
  styleUrls: ['./stats-report.component.scss'],
})
export class StatsReportComponent implements OnInit {
  public canvas: any;
  public ctx;
  public datasets: any;
  public data: any;
  public myChartData;
  public opportunityChart;
  public clicked: boolean = true;
  public clicked1: boolean = false;
  public clicked2: boolean = false;
  public clicked3: boolean = false;

  status: any[] = [];
  count: number[] = [];

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Basic ${this.authenticationService.userValue.authdata}`,
    }),
  };

  constructor(
    private authenticationService: AuthenticationService,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.init();
  }
  public init(): void {
    this.http
      .get<any>(`${environment.apiUrl}/orders/status`, this.httpOptions)
      .subscribe((results) => {
        results.forEach((result) => {
          this.status.push(result[0]);
          this.count.push(result[1]);
        });
        this.drawTable(this.status, this.count);
      });
  }

  public drawTable(status, count) {
    var gradientBarChartConfiguration: any = {
      maintainAspectRatio: false,
      legend: {
        display: false,
      },

      tooltips: {
        backgroundColor: '#f5f5f5',
        titleFontColor: '#333',
        bodyFontColor: '#666',
        bodySpacing: 4,
        xPadding: 12,
        mode: 'nearest',
        intersect: 0,
        position: 'nearest',
      },
      responsive: true,
      scales: {
        yAxes: [
          {
            gridLines: {
              drawBorder: false,
              color: 'rgba(29,140,248,0.1)',
              zeroLineColor: 'transparent',
            },
            ticks: {
              suggestedMin: 0,
              suggestedMax: 0,
              padding: 20,
              fontColor: '#9e9e9e',
            },
          },
        ],

        xAxes: [
          {
            gridLines: {
              drawBorder: false,
              color: 'rgba(29,140,248,0.1)',
              zeroLineColor: 'transparent',
            },
            ticks: {
              padding: 20,
              fontColor: '#9e9e9e',
            },
          },
        ],
      },
    };

    this.canvas = document.getElementById('MyChartData');
    this.ctx = this.canvas.getContext('2d');
    var gradientStroke = this.ctx.createLinearGradient(0, 230, 0, 50);

    gradientStroke.addColorStop(1, 'rgba(29,140,248,0.2)');
    gradientStroke.addColorStop(0.4, 'rgba(29,140,248,0.0)');
    gradientStroke.addColorStop(0, 'rgba(29,140,248,0)'); //blue colors

    var myChartData = new Chart(this.ctx, {
      type: 'bar',
      data: {
        labels: status,
        datasets: [
          {
            label: 'Count',
            fill: true,
            backgroundColor: gradientStroke,
            hoverBackgroundColor: gradientStroke,
            borderColor: '#1f8ef1',
            borderWidth: 2,
            borderDash: [],
            borderDashOffset: 0.0,
            data: count,
          },
        ],
      },
      options: gradientBarChartConfiguration,
    });
  }

  public updateTable(chart, status, count) {
    chart.data.labels = status;
    chart.data.datasets[0].data = count;
    chart.update();
  }
}
