import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Configuration } from './config.model';

@Injectable()
export class ConfigService {

  private config: Configuration;

  constructor(private httpClient: HttpClient) { }

  load(url: string) {
    console.log('load');
    return this.httpClient.get<Configuration>(url)
      .toPromise()
      .then(config => this.config = config);
  }

  getConfiguration(): Configuration {
    return this.config;
  }
}
