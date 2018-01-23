package com.jsanz.portfolio.server.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.jsanz.portfolio.domain.Transaction;

@RepositoryRestResource
public interface TransactionRepository extends MongoRepository<Transaction, String> {

	List<Transaction> findByAssetName(@Param("asset.name") String name);

}
