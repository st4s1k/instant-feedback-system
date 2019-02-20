// Modules
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
// Components
import { SignInComponent } from './sign-in/sign-in.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { EditPresentationComponent } from './edit-presentation/edit-presentation.component';
import { HomeComponent } from './home/home.component';
import { PresentationPageComponent } from './presentation-page/presentation-page.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { AdminProfileComponent } from './admin-profile/admin-profile.component';
// Services
import { PresentationDetailResolverService } from './services/presentation-detail-resolver.service';
import { UserDetailResolverService } from './services/user-detail-resolver.service';
import { PresentationListPageDetailResolverService } from './services/presentation-list-page-detail-resolver.service';
import { UserListDetailResolverService } from './services/user-list-detail-resolver.service';
import { UserPresentationResolverService } from './services/user-presentation-resolver.service';
// Guards
import { AuthGuard } from './_guards/auth.guard';
import { AdminGuard } from './_guards/admin.guard';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    resolve: {
      page: PresentationListPageDetailResolverService
    }
  },
  { path: 'sign-in',
    component: SignInComponent },
  { path: 'sign-up',
    component: SignUpComponent },
  {
    path: 'new-presentation',
    component: EditPresentationComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'edit-presentation/:id',
    component: EditPresentationComponent,
    resolve: {
      presentation: PresentationDetailResolverService
    },
    canActivate: [AuthGuard]
  },
  {
    path: 'presentation-page/:id',
    component: PresentationPageComponent,
    resolve: {
      presentation: PresentationDetailResolverService,
    }
  },
  {
    path: 'user-profile',
    component: UserProfileComponent,
    resolve: {
      user: UserDetailResolverService,
      presentations : UserPresentationResolverService
    },
    canActivate: [AuthGuard]
  },
  {
    path: 'admin-profile',
    component: AdminProfileComponent,
    resolve: {
      users: UserListDetailResolverService,
      pages : PresentationListPageDetailResolverService
    },
    canActivate: [AdminGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {scrollPositionRestoration: 'enabled'})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
