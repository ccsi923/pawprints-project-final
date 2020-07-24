import { AnimalType } from './animalType';

export interface Animal {
  id: number;
  name: string;
  animal: AnimalType;
  age: number;
}
