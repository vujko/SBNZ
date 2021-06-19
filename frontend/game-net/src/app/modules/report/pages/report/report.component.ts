import { Component, OnInit } from '@angular/core';
import { ReportService } from '../../report.service';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.scss']
})
export class ReportComponent implements OnInit {

  games: any[];
  profit: number;
  images: String[];

  constructor(
    private reportService: ReportService
  ) { }

  ngOnInit(): void {
    this.getReport();
    this.images = [
      "./../../../../../assets/images/unnamed.png",
      "./../../../../../assets/images/lampa-game-over.jpg",
      "./../../../../../assets/images/Pac_Man_TF_PVC_Sneak_Peek_3840x3840px.jpg",
      "./../../../../../assets/images/unnamed.jpg"
    ];
  }

  randImage(game) {
    return this.images[game.id % this.images.length];
  }

  getReport() {
    this.reportService.getReport()
    .subscribe(response => {
      this.games = response.games;
      this.profit = response.profit;
    })
  }

}
