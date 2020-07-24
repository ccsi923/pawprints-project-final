import { ProviderOrderLineRequest } from './providerOrderLineRequest';
import { OrderReceptionStatus } from './orderReceptionStatus';

export interface ProviderOrder {
  id: number;
  providerOrderLines: ProviderOrderLineRequest[];
  status: OrderReceptionStatus;
  comments: string;
}
