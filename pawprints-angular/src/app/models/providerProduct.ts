import { ProductOrderType } from './productOrderType';
export interface ProviderProduct {
  id: number;
  name: string;
  type: ProductOrderType;
  minStock: number;
  totalRemainingUnits: number;
  purchaseUnits: number;
  pricePerUnit: number;
}
