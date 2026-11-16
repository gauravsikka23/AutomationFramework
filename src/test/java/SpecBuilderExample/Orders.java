package SpecBuilderExample;

import lombok.Data;

import java.util.List;

@Data
public class Orders {

    private List<OrderDetails> orders;
}
