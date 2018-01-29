import { BrowserModule } from '@angular/platform-browser';
import { NgModule, APP_INITIALIZER } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';
import { MatTableModule } from '@angular/material';

import { environment } from '../environments/environment';
import { AppComponent } from './app.component';
import { ConfigService } from './services/config.service';
import { TransactionService } from './services/transaction.service';
import { TransactionsComponent } from './components/transactions/transactions.component';
import { AppRoutingModule } from './app-routing.module';

export function configLoader(configService: ConfigService) {
  // Note: this factory need to return a function (that return a promise)
  return () => configService.load(environment.configFile);
}

@NgModule({
  declarations: [
    AppComponent,
    TransactionsComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    MatTableModule,
    AppRoutingModule
  ],
  providers: [
    ConfigService,
    {
      provide: APP_INITIALIZER,
      useFactory: configLoader,
      deps: [ConfigService],
      multi: true,
    },
    TransactionService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {

  // Diagnostic only: inspect router configuration
  constructor(router: Router) {
    console.log('Routes: ', JSON.stringify(router.config, undefined, 2));
  }
}
