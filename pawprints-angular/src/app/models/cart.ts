import { DateBetween } from './dateBetween';

export interface Cart {
  id: number;
  customerId: number;
  subtotal: number;
  purchaseDate: Date;
}
