import {Component} from '@angular/core';
import {MatError, MatFormField, MatLabel} from '@angular/material/form-field';
import {MatInput} from '@angular/material/input';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {NgIf} from '@angular/common';
import {MatButton} from '@angular/material/button';
import {UserRequestDTO, UserService} from '../../core/service/user.service';
import {Router} from '@angular/router';
import {RSP_SUCCESS} from '../../core/constant/ResponseCode';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  imports: [
    MatFormField,
    MatInput,
    MatLabel,
    ReactiveFormsModule,
    NgIf,
    MatButton,
    MatError
  ],
  styleUrl: './signup.component.scss'
})
export class SignupComponent {
  signupForm: FormGroup;


  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private router: Router,
  ) {
    this.signupForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(4)]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      name: ['', Validators.required],
      address: ['', Validators.required],
      nic: ['', [Validators.required, Validators.pattern(/^\d{9}[Vv]$|^\d{12}$/)]], // NIC validation
      phone: ['', [Validators.required, Validators.pattern(/^\d{10}$/)]], // Phone validation
    });
  }

  submitForm() {
    if (this.signupForm.valid) {
      console.log('Form Submitted', this.signupForm.value);
      const user: UserRequestDTO = this.signupForm.value;
      this.userService.register(user).subscribe({
        next: (res) => {
          if (res.status === RSP_SUCCESS) {
            console.log(res)
            this.router.navigate(["pre/login"])
          } else {
            //error
          }
        },
        error(er) {
          console.log(er)
        }
      });
    } else {
      this.signupForm.markAllAsTouched();
    }
  }
}
