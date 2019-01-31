import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SignInComponent } from './sign-in/sign-in.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { EditPresentationComponent } from './edit-presentation/edit-presentation.component';
import { HomeComponent } from './home/home.component';
import { PresentationPageComponent } from './presentation-page/presentation-page.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { PresentationDetailResolverService } from './services/presentation-detail-resolver.service';



const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'sign-in', component: SignInComponent },
  { path: 'sign-up', component: SignUpComponent },
  { path: 'new-presentation', component: EditPresentationComponent },
  {
    path: 'edit-presentation/:id',
    component: EditPresentationComponent,
    resolve: {
      presentation: PresentationDetailResolverService
    }
  },
  {
    path: 'presentation-page/:id',
    component: PresentationPageComponent,
    resolve: {
      presentation: PresentationDetailResolverService
    }
  },
  { path: 'user-profile/:id', component: UserProfileComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
