import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TestComponent } from './components/test/test.component';
import { HomeComponent } from './components/home/home.component';
import { MentorSearchComponent } from './components/mentor-search/mentor-search.component';
import { MentorSearchProfileComponent } from './components/mentor-search-profile/mentor-search-profile.component';
import { PageURL } from './utils/PageURL';
import { LoginComponent } from './components/login/login.component';
import { MentorSignupComponent } from './components/signup/mentor-signup/mentor-signup.component';
import { UserSignupComponent } from './components/signup/user-signup/user-signup.component';
import { MentorProfileComponent } from './components/mentor-profile/mentor-profile.component';
import { AuthGuardService } from './modules/authentication/auth-services/auth-guard.service';
import { Role } from './utils/role.enum';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { UserTrainingComponent } from './components/user-training/user-training.component';
import { MentorTrainingComponent } from './components/mentor-training/mentor-training.component';
import { AdminProfileComponent } from './components/admin-profile/admin-profile.component';
import { AdminSkillSettingComponent } from './components/admin-skill-setting/admin-skill-setting.component';
import { AdminPaymentSettingComponent } from './components/admin-payment-setting/admin-payment-setting.component';
import { AdminUserSettingsComponent } from './components/admin-user-settings/admin-user-settings.component';
import { AdminSettingsComponent } from './components/admin-settings/admin-settings.component';

const routes: Routes = [
  {
    path: 'test', 
    component: TestComponent
  },

  {
    path : '', 
    component : HomeComponent,
    canActivate: [AuthGuardService]
  },

  {
    path:       PageURL.HOME,
    component:  HomeComponent
  },

  {
    path:       PageURL.SEARCH_MENTOR,
    component:  MentorSearchComponent
  },

  {
    path:       PageURL.SEARCH_MENTOR_PROFILE,
    component:  MentorSearchProfileComponent
  },

  {
    path:       PageURL.LOGIN,
    component:  LoginComponent
  },

  {
    path:       PageURL.MENTOR_SIGNUP,
    component:  MentorSignupComponent
  },

  {
    path:       PageURL.USER_SIGNUP,
    component:  UserSignupComponent
  },

  {
    path:         PageURL.MENTOR_PROFILE,
    component:    MentorProfileComponent,
    canActivate:  [AuthGuardService],
    data:         { roles:[Role.Mentor] } 
  },

  {
    path :        PageURL.MENTOR_TRAINING,
    component:    MentorTrainingComponent,
    canActivate:  [AuthGuardService],
    data:         { roles:[Role.Mentor] }
  },

  {
    path :        PageURL.USER_PROFILE,
    component:    UserProfileComponent,
    canActivate:  [AuthGuardService],
    data:         {roles:[Role.User]}
  },

  {
    path :        PageURL.USER_TRAINING,
    component:    UserTrainingComponent,
    canActivate:  [AuthGuardService],
    data:         {roles:[Role.User]}
  },

  {
    path :        PageURL.ADMIN_SETTINGS,
    component:    AdminSettingsComponent,
    canActivate:  [AuthGuardService],
    data:         {roles:[Role.Admin]}
  },

  {
    path :        PageURL.ADMIN_PROFILE,
    component:    AdminProfileComponent,
    canActivate:  [AuthGuardService],
    data:         {roles:[Role.Admin]}
  },

  {
    path :        PageURL.ADMIN_SETTINGS_SKILL,
    component:    AdminSkillSettingComponent,
    canActivate:  [AuthGuardService],
    data:         {roles:[Role.Admin]}
  },

  {
    path :        PageURL.ADMIN_SETTINGS_PAYMENT,
    component:    AdminPaymentSettingComponent,
    canActivate:  [AuthGuardService],
    data:         {roles:[Role.Admin]}
  },

  {
    path :        PageURL.ADMIN_SETTINGS_USER,
    component:    AdminUserSettingsComponent,
    canActivate:  [AuthGuardService],
    data:         {roles:[Role.Admin]}
  },

  {
    path: '**',
    redirectTo: ''
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }