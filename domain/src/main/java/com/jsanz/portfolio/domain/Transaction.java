package com.jsanz.portfolio.domain;

import java.util.Currency;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "transactions")
public class Transaction {

	@Id
	private String id;

	@Field("asset")
	private Asset asset;

	@Field("type")
	private TransactionTypeEnum type;

	@Field("shares")
	private Double shares;

	@Field("price")
	private Double price;

	@Field("date")
	@Indexed
	private DateTime date; //TODO: Use ZonedDateTime: https://stackoverflow.com/questions/41127665/zoneddatetime-with-mongodb

	public Transaction() {
		super();
	}

	public Transaction(String id, Asset asset, TransactionTypeEnum type, Double shares, Double price, DateTime date) {
		super();
		this.id = id;
		this.asset = asset;
		this.type = type;
		this.shares = shares;
		this.price = price;
		this.date = date;
	}

	public Transaction(String id, String assetName, AssetTypeEnum assetType, String assetMorningstarId, Currency assetCurrency,
			TransactionTypeEnum type, Double shares, Double price, DateTime date) {
		super();
		this.id = id;
		this.asset = new Asset(assetName, assetType, assetMorningstarId, assetCurrency);
		this.type = type;
		this.shares = shares;
		this.price = price;
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public TransactionTypeEnum getType() {
		return type;
	}

	public void setType(TransactionTypeEnum type) {
		this.type = type;
	}

	public Double getShares() {
		return shares;
	}

	public void setShares(Double shares) {
		this.shares = shares;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Transaction other = (Transaction) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Transaction [asset=" + asset + ", type=" + type + ", shares=" + shares + ", price=" + price + ", date=" + date
				+ "]";
	}

}
