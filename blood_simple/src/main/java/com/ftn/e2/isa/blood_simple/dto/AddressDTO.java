package com.ftn.e2.isa.blood_simple.dto;

import com.ftn.e2.isa.blood_simple.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

        private Long id;
        private String street;
        private String number;
        private String city;
        private String country;
        private double x;
        private double y;

        public AddressDTO(Address address) {
            this.id = address.getId();
            this.street = address.getStreet();
            this.number = address.getNumber();
            this.city = address.getCity();
            this.country = address.getCountry();
            this.x = address.getX();
            this.y = address.getY();
        }

}
