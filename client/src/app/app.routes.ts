import {Routes} from '@angular/router';
import {PostLoginComponent} from './post-login/post-login.component';
import {PreLoginComponent} from './pre-login/pre-login.component';
import {preLoginRoutes} from './pre-login/pre-login.routes';
import {postLoginRoutes} from './post-login/post-login.routes';
import {authguardGuard} from './core/guards/authguard.guard';
import {preLoginGuard} from './core/guards/pre-login.guard';

export const routes: Routes = [
  {path: '', component: PostLoginComponent, children: postLoginRoutes, canActivate: [authguardGuard]},
  {path: 'pre', component: PreLoginComponent, children: preLoginRoutes, canActivate:[preLoginGuard]},
];
