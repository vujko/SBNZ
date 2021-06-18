import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { AuthenticationService } from '../../authentication.service';
import { LoginDTO } from '../../model';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.scss']
})
export class LogInComponent implements OnInit {

  addForm:FormGroup;
  submitted: boolean;

  constructor(
    private formBuilder: FormBuilder,
    private authenticationService: AuthenticationService,
    private messageService: MessageService
  ) { }

  ngOnInit(): void {
    this.addForm = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  onSubmit() {
    this.submitted = true;

    this.authenticationService.login(this.addForm.getRawValue())
    .subscribe((response) => {
      this.login(response);
    });   
  }

  login(response: any) {
    this.submitted = false;
    this.authenticationService.changeCurrentUser(response);
    this.messageService.add({ severity: 'success',
              summary: 'Successful log in!',
              detail: `User ${this.f.email.value} successfuly logged in!`});
    this.resetForm();
  }

  get f() { return this.addForm.controls; }

  invalidFormInputs(): boolean {
    if(this.f.email.value === null || this.f.email.value === '') {
      return true;
    }
    if(this.f.password.value === null || this.f.password.value === '') {
      return true;
    }
    return false;
  }

  resetForm() {
    this.addForm.reset();
  }

}
