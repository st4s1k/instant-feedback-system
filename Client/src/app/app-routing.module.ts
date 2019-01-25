import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {SigninComponent} from './sign-in/sign-in.component';
import {SignupComponent} from './sign-up/sign-up.component';
import {AddPresComponent} from './edit-presentation/edit-presentation.component';



const routes: Routes = [
  {path: 'sign-in', component: SigninComponent},
  {path: 'sign-up', component: SignupComponent},
  {path: 'edit-presentation', component: AddPresComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
