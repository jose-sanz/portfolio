import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Configuration } from './config.model';

@Injectable()
export class ConfigService {

  private config: Configuration;

  constructor(private http: HttpClient) { }

  load(url: string) {
    return this.http.get<Configuration>(url)
      .toPromise()
      .then(config => this.config = config);
  }

  getConfiguration(): Configuration {
    return this.config;
  }
}
