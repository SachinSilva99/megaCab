import {HttpInterceptorFn} from '@angular/common/http';
import {inject} from '@angular/core';
import {LoggedInUser, StorageService, USER} from '../service/storage.service';

export const interceptorInterceptor: HttpInterceptorFn = (req, next) => {
  const storageService = inject(StorageService);
  const loggedInUser = storageService.getItem<LoggedInUser>(USER);
  let token;
  if (loggedInUser) {
    token = loggedInUser.token
  }

  const authReq = token ? req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`,
      },
    })
    : req;

  return next(authReq);
};
