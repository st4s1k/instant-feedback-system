import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {ReactiveFormsModule} from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NavComponent } from './nav/nav.component';
import { SignupComponent } from './sign-up/sign-up.component';
import { SigninComponent } from './sign-in/sign-in.component';
import { FooterComponent } from './footer/footer.component';
import { AddPresComponent } from './edit-presentation/edit-presentation.component';


@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    SignupComponent,
    SigninComponent,
    FooterComponent,
    AddPresComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule.forRoot(),
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
