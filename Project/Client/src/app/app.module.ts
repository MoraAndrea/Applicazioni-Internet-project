import { BrowserModule, HAMMER_LOADER } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { AppRoutingModule } from './modules/routing.module';
import { AppComponent } from './components/app/app.component';
import { MaterialModule } from './modules/material.module';
import { AuthService } from './services/auth/auth.service';
import { LoginComponent } from './components/authentication/login/login.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { RecoveryComponent } from './components/authentication/recovery/recovery.component';
import { LogoutComponent } from './components/authentication/logout.component';
import { PasswordResetComponent } from './components/authentication/password-reset/password-reset.component';
import { PasswordToggleDirective } from './directives/passwordToggle.directive';
import { ConfirmComponent } from './components/authentication/confirm/confirm.component';
import { MessageComponent } from './components/message/message.component';
import { MessageService } from './services/bridges/message.service';
import { HomeComponent } from './components/home/home.component';
import { UserService } from './services/user/user.service';
import { LineService } from './services/lines/line-races.service';
import { MessagesComponent } from './components/messages/messages.component';
import { AdminComponent } from './components/admin/admin.component';
import { CompanionComponent } from './components/companion/companion.component';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { LinesComponent } from './components/lines/lines.component';
import { SettingsComponent } from './components/settings/settings.component';
import { RegisterComponent } from './components/admin/register/register.component';
import { IsMobileService } from './services/bridges/is-mobile.service';
import { UserManagementComponent } from './components/admin/users-management/users-management.component';
import { RacesManagementComponent } from './components/admin/races-management/races-management.component';
import { AdminService } from './services/admin/admin.service';
import { AngularSvgIconModule } from 'angular-svg-icon';
import { JwtInterceptor } from './interceptors/jwt.interceptor';
import { ErrorInterceptor } from './interceptors/error.interceptor';
import { ConfirmDialog } from './components/dialogs/confirm-dialog/confirm.dialog';
import { MessageDialogComponent } from './components/dialogs/messege-dialog/messege.dialog';
import { ViewUserDialog } from './components/admin/users-management/view-user-dialog/view-user.dialog';
import { MAT_DIALOG_DEFAULT_OPTIONS } from '@angular/material/dialog';
import { EditUserDialog } from './components/admin/users-management/edit-user-dialog/edit-user.dialog';
import { PendingRequestsComponent } from './components/companion/companion-requests/pending-requests.component';
import { CompanionService } from './services/companion/companion.service'
import { RacesCompanionComponent } from './components/companion/races-companion/races-companion.component';
import { GiveAvailabilityDialog } from './components/companion/races-companion/give-availability-dialog/give-availability.dialog';

@NgModule({
  declarations: [
    AppComponent, WelcomeComponent,
    // Auth
    LoginComponent, LogoutComponent, RecoveryComponent, PasswordResetComponent, ConfirmComponent, RegisterComponent,

    // Home
    HomeComponent, MessagesComponent,

    // Lines
    LinesComponent,

    // Companion
    CompanionComponent, PendingRequestsComponent, RacesCompanionComponent,

    // Admin
    AdminComponent, UserManagementComponent, RacesManagementComponent, ViewUserDialog, EditUserDialog,

    // Settings
    SettingsComponent,

    // Dialogs
    ConfirmDialog, MessageDialogComponent, GiveAvailabilityDialog,

    // Dumb components
    MessageComponent,

    // Directives
    PasswordToggleDirective
  ],
  entryComponents: [
    ConfirmDialog, MessageDialogComponent, ViewUserDialog, EditUserDialog, GiveAvailabilityDialog
  ],
  imports: [
    BrowserModule, BrowserAnimationsModule,
    MaterialModule,
    AppRoutingModule,
    FormsModule, ReactiveFormsModule,
    HttpClientModule,
    AngularSvgIconModule
  ],
  providers: [
    AuthService,
    UserService,
    AdminService,
    CompanionService,
    LineService,
    MessageService,
    IsMobileService,
    { provide: LocationStrategy, useClass: HashLocationStrategy },
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
    { provide: MAT_DIALOG_DEFAULT_OPTIONS, useValue: { hasBackdrop: true } },
    { provide: HAMMER_LOADER, useValue: () => new Promise(() => { }) }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
