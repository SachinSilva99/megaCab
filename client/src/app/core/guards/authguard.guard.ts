import {CanActivateFn, Router} from '@angular/router';
import {inject} from '@angular/core';
import {LoggedInUser, StorageService, USER} from '../service/storage.service';

export const authguardGuard: CanActivateFn = (route, state) => {
  const storageService = inject(StorageService);
  const router = inject(Router);

  const loggedInUser = storageService.getItem<LoggedInUser>(USER);
  let token;
  if (loggedInUser) {
    token = loggedInUser.token
  }
  if (token) {
    return true;
  } else {
    router.navigate(['/pre/login']);
    return false;
  }
};
