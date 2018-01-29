export interface Transaction {
    asset: Asset;
    type: string;
    shares: number;
    price: number;
    date: Date;
}

export interface Asset {
    name: string;
    type: string;
    thirdPartyId: number;
    currency: string;
}
