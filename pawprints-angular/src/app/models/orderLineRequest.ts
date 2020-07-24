import { ProductType } from './productType';

export interface OrderLineRequest {
  id: number;
  productType: ProductType;
  requestedQuantity: number;
}
