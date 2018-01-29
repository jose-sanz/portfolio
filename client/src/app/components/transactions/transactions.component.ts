import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';

import { TransactionService } from '../../services/transaction.service';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent implements OnInit {

  displayedColumns = ['name', 'type', 'shares', 'date', 'currency', 'price'];
  dataSource = new MatTableDataSource();

  constructor(private transactionService: TransactionService) { }

  ngOnInit() {
    this.getTransactions();
  }

  getTransactions() {

    this.transactionService.getTransactions().subscribe(
      data => {
        this.dataSource.data = data._embedded.transactions;
      },
      error => {
        console.log(<any>error);
      }
    );

  }

}
