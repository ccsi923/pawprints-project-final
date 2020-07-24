export interface Address {
  addressStreet: string;
  addressZipCode: string;
  addressCity: string;
  addressCountry: string;
  addressPhone: string; //// @Pattern(regexp="([0-9]{9})", message = "Nine numbers")
}
