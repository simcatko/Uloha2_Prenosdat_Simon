package sk.fri.uniza.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(name = "IotNode_findByHouseHold",
                query = "from IotNode where HouseHoldId = :houseHoldId"),
        @org.hibernate.annotations.NamedQuery(name = "IotNode_findAll",
                query = "from IotNode"),
})
@Entity
public class IotNode {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @ApiModelProperty(accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    private Long id;
    @NotEmpty
    @ApiModelProperty(example = "SiMa byt") // Príklad pre swagger doku.
    private String Name;
    @ManyToOne
    @JsonIgnore
    private HouseHold houseHold;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public HouseHold getHouseHold() {
        return houseHold;
    }

    public void setHouseHold(HouseHold houseHold) {
        this.houseHold = houseHold;
    }
}
