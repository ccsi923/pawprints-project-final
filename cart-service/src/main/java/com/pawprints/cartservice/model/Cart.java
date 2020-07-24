package com.pawprints.cartservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer customerId;

    @Column(name = "subtotal", precision = 10)
    private BigDecimal subtotal;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cart")
    @Cascade(CascadeType.SAVE_UPDATE)
    private List<CartItem> cartItems = new ArrayList<CartItem>();

    private LocalDate purchaseDate;

    public BigDecimal calculateTotal(){
        BigDecimal total = BigDecimal.ZERO;
        for (CartItem cartItem : this.cartItems) {
           total = total.add( (cartItem.getProduct().getProductPrice())
                   .multiply(new BigDecimal(cartItem.getProduct().getRequestedQuantity())), new MathContext(10));
        }
        return total;
    }



}
