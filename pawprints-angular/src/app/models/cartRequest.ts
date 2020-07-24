import { PaymentType } from './paymentType';
import { Product } from './product';

export interface CartRequest {
  animalId: number;
  paymentType: PaymentType;
  products: Product[];
  comments: string;
}
