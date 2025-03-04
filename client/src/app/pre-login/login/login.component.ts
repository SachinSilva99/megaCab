import { Component } from '@angular/core';
import {MatError, MatFormField, MatLabel} from '@angular/material/form-field';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatInput} from '@angular/material/input';
import {NgIf} from '@angular/common';
import {MatButton} from '@angular/material/button';
import {UserLoginRequestDTO, UserService} from '../../core/service/user.service';
import {RSP_SUCCESS} from '../../core/constant/ResponseCode';

@Component({
  selector: 'app-login',
  imports: [
    MatFormField,
    ReactiveFormsModule,
    MatInput,
    NgIf,
    MatButton,
    MatError,
    MatLabel
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  loginForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
  ) {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      console.log("Login Successful", this.loginForm.value);
      const user : UserLoginRequestDTO = this.loginForm.value;
      this.userService.login(user).subscribe({
        next(res) {
          if (res.status === RSP_SUCCESS) {
            console.log(res)

          } else {
            //error
          }
        },
        error(er) {
          console.log(er)
        }
      });
    }
  }
}
