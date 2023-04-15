package fck.personalDetails.dtos.agify;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgifyResponse {
    private int age;
    private int count;
    private String name;
}
