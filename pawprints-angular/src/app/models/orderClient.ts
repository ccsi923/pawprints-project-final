import { OrderStatus } from './orderStatus';
import { OrderLineRequest } from './orderLineRequest';
import { ProductOrderType } from './productOrderType';
export interface OrderClient {
  id: number;
  status: OrderStatus;
  newStatus: OrderStatus;
  kitStatus: ProductOrderType;
  comments: string;
  stockOrderLineList: OrderLineRequest[];
}
