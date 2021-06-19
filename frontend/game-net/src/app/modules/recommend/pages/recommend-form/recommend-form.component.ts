import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { Game } from '../../model';
import { RecommendService } from '../../recommend.service';

@Component({
  selector: 'app-recommend-form',
  templateUrl: './recommend-form.component.html',
  styleUrls: ['./recommend-form.component.scss']
})
export class RecommendFormComponent implements OnInit {

  addForm:FormGroup;
  submitted: boolean;

  games: Game[];
  images: String[];


  constructor(
    private formBuilder: FormBuilder,
    private messageService: MessageService,
    private recommedService: RecommendService
  ) { }

  ngOnInit(): void {
    this.addForm = this.formBuilder.group({
      genre: ['', Validators.required],
      theme: ['', Validators.required],
      playerSupport: ['', Validators.required],
      platform: ['', Validators.required],
      specialSection: ['', Validators.required],
      lowerPrice: ['', Validators.required],
      higherPrice: ['', Validators.required]
    });
    this.images = [
      "./../../../../../assets/images/unnamed.png",
      "./../../../../../assets/images/lampa-game-over.jpg",
      "./../../../../../assets/images/Pac_Man_TF_PVC_Sneak_Peek_3840x3840px.jpg",
      "./../../../../../assets/images/unnamed.jpg"
    ]
  }

  onSubmit() {
    this.submitted = true;

    if(this.invalidFormInputs()) {
      this.submitted = false;
      return;
    }

    var request = {
      genre: (this.f.genre.value === null) ? '':this.f.genre.value,
      lowerPrice:  (this.f.lowerPrice.value === null) ? 0:this.f.lowerPrice.value,
      higherPrice: (this.f.higherPrice.value === null) ? 0:this.f.higherPrice.value,
      platform: (this.f.platform.value === null) ? '':this.f.platform.value,
      theme: (this.f.theme.value === null) ? '':this.f.theme.value,
      playerSupport: (this.f.playerSupport.value === null) ? '':this.f.playerSupport.value,
      specialSection: (this.f.specialSection.value === null) ? '':this.f.specialSection.value,
    }
    this.recommedService.recommend(request)
    .subscribe(response => {
      this.games = response;
      if(this.games === [])
        this.messageService.add({ severity: 'No results',
                summary: 'We can not recommend you any game!',
                detail: `Your serach does not fit any of our games!`});
      this.resetForm();
    })

  }

  randImage(game) {
    return this.images[game.id % this.images.length];
  }

  purchase(game) {
    this.recommedService.purchase({"gameId": game.id, "userEmail": "bodroza.joca1@gmail.com"}).subscribe(response => {
      this.messageService.add({ severity: 'success',
              summary: 'Successful purchase!',
              detail: `Game ${game.name} successfuly bought!`});
    })

  }

  get f() { return this.addForm.controls; }

  invalidFormInputs(): boolean {
    // if(this.f.genre.value === null || this.f.genre.value === '') {
    //   return true;
    // }
    // if(this.f.theme.value === null || this.f.theme.value === '') {
    //   return true;
    // }
    // if(this.f.playerSupport.value === null || this.f.playerSupport.value === '') {
    //   return true;
    // }
    // if(this.f.platform.value === null || this.f.platform.value === '') {
    //   return true;
    // }
    // if(this.f.specialSection.value === null || this.f.specialSection.value === '') {
    //   return true;
    // }
    // if(this.f.price.value === null || this.f.price.value === '') {
    //   return true;
    // }
    return false;
  }

  resetForm() {
    this.addForm.reset();
  }

}
