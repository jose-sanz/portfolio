package com.jsanz.portfolio.domain;

import java.util.Currency;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

public class Asset {

	@Field("name")
	@Indexed
	private String name;
	
	@Field("type")
	private AssetTypeEnum type;
	
	@Field("third_party_id")
	private String thirdPartyId;

	@Field("currency")
	private Currency currency;

	public Asset() {
		super();
	}

	public Asset(String name, AssetTypeEnum type, String thirdPartyId, Currency currency) {
		super();
		this.name = name;
		this.type = type;
		this.thirdPartyId = thirdPartyId;
		this.currency = currency;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AssetTypeEnum getType() {
		return type;
	}

	public void setType(AssetTypeEnum type) {
		this.type = type;
	}

	public String getMorningstarId() {
		return thirdPartyId;
	}

	public void setThirdPartyId(String thirdPartyId) {
		this.thirdPartyId = thirdPartyId;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name;
	}

}
