import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { TimepickerModule } from 'ngx-bootstrap/timepicker';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TestComponent } from './components/test/test.component';
import { HomeComponent } from './components/home/home.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MentorSearchComponent } from './components/mentor-search/mentor-search.component';
import { HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { MentorSearchProfileComponent } from './components/mentor-search-profile/mentor-search-profile.component';
import { MentorSignupComponent } from './components/signup/mentor-signup/mentor-signup.component';
import { UserSignupComponent } from './components/signup/user-signup/user-signup.component';
import { LoginComponent } from './components/login/login.component';
import { MentorProfileComponent } from './components/mentor-profile/mentor-profile.component';
import { httpInterceptorProviders } from './modules/authentication/auth-models/auth-interceptor';
import { AuthService } from './modules/authentication/auth-services/auth.service';
import { SkillService } from './services/skill.service';
import { MentorService } from './services/mentor.service';
import { MentorSkillService } from './services/mentor-skill.service';
import { ErrorInterceptorService } from './modules/authentication/auth-services/error-interceptor.service';

@NgModule({
  declarations: [
    AppComponent,
    TestComponent,
    HomeComponent,
    MentorSearchComponent,
    MentorSearchProfileComponent,
    MentorSignupComponent,
    UserSignupComponent,
    LoginComponent,
    MentorProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule.forRoot(),
    BsDatepickerModule.forRoot(),
    TimepickerModule.forRoot()
  ],
  providers: [
    httpInterceptorProviders,
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptorService, multi: true },
    AuthService,
    SkillService,
    MentorService,
    MentorSkillService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
