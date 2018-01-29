import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { ConfigService } from './config.service';
import { Transaction } from './transaction.model';

@Injectable()
export class TransactionService {

  serverUrl = this.configService.getConfiguration().serverUrl;

  constructor(private http: HttpClient, private configService: ConfigService) { }

  getTransactions(): Observable<any> {
    return this.http.get<any>(this.serverUrl + 'transactions');
  }

}
