import { PaymentType } from './paymentType';
import { OrderStatus } from './orderStatus';

export interface ClientOrder {
  total: number;
  orderDate: Date;
  userId: number;
  animalId: number;
  payment: PaymentType;
  statusOrder: OrderStatus;
}
