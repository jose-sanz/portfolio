import { BrowserModule } from '@angular/platform-browser';
import { NgModule, APP_INITIALIZER } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { ConfigService } from './services/config.service';
import { environment } from '../environments/environment';

export function configLoader(configService: ConfigService) {
  console.log('configLoader');
  // Note: this factory need to return a function (that return a promise)
  return () => configService.load(environment.configFile);
}

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule
  ],
  providers: [
    ConfigService,
    {
      provide: APP_INITIALIZER,
      useFactory: configLoader,
      deps: [ConfigService],
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
