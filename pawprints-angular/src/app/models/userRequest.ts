import { Address } from './adress';

export interface UserRequest {
  userEmail: string;
  username: string;
  password: string;
  primaryAddress: Address;
  mailingAddress: Address;
}
