import {Component} from '@angular/core';
import {MatError, MatFormField, MatLabel} from '@angular/material/form-field';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatInput} from '@angular/material/input';
import {NgIf} from '@angular/common';
import {MatButton} from '@angular/material/button';
import {UserLoginRequestDTO, UserService} from '../../core/service/user.service';
import {RSP_SUCCESS} from '../../core/constant/ResponseCode';
import {LoggedInUser, StorageService, USER} from '../../core/service/storage.service';
import Swal from 'sweetalert2'

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
    private storageService: StorageService,
  ) {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(4)]]
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      const user: UserLoginRequestDTO = this.loginForm.value;
      this.userService.login(user).subscribe({
        next: (res) => {
          if (res.status === RSP_SUCCESS) {
            const token = res.content;
            const loggedInUser: LoggedInUser = {token: token.accessToken, username: this.loginForm.value.username};
            this.storageService.setItem(USER, loggedInUser);
            console.log(this.storageService.getItem(USER));
          } else {
            Swal.fire({
              text: res.message,
              icon: "error"
            });
          }
        },
        error(er) {
          Swal.fire({
            text: er.message,
            icon: "error"
          });
        }
      });
    }
  }
}
