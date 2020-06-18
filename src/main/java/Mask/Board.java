package Mask;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Board_table")
public class Board {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private String maskType;
        private Integer qty;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
        public String getMaskType() {
            return maskType;
        }

        public void setMaskType(String maskType) {
            this.maskType = maskType;
        }
        public Integer getQty() {
            return qty;
        }

        public void setQty(Integer qty) {
            this.qty = qty;
        }

}
