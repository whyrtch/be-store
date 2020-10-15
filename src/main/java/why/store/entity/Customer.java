package why.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "j_customer")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Customer extends BaseEntity {

    @NotNull
    String fullname;
    @NotNull
    String email;
    @NotNull
    String mobileNumber;
    @NotNull
    String address;

    @OneToMany(mappedBy="customer", cascade= CascadeType.ALL,  fetch= FetchType.LAZY)
    @Fetch(FetchMode.SELECT) @JsonIgnoreProperties("customer") @JsonManagedReference
    List<Order> orders ;
}
