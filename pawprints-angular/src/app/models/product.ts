import { ProductType } from './productType';

export interface Product {
  productType: ProductType;
  productPrice: string;
  requestedQuantity: number;
}
