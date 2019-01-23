import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {KillComponent} from './kill/kill.component';

const routes: Routes = [
  {path:'kill', component:KillComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
