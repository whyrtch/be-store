package why.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Entity
@Table(name = "j_order")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
@EqualsAndHashCode(of = "id")
public class Order extends BaseEntity {

    @ManyToOne(fetch= FetchType.LAZY)
    @Fetch(FetchMode.JOIN) @JsonManagedReference
    @JoinColumn(name="customer_id")
    private Customer customer ;

    @ManyToOne(fetch= FetchType.LAZY)
    @JsonManagedReference @JoinColumn(name="product_id")
    Product product;

}
