import { OrderLineRequest } from './orderLineRequest';

export interface OrderRequest {
  orderLineRequests: OrderLineRequest[];
  comments: string;
}
