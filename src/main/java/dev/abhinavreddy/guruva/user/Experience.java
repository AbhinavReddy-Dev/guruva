package dev.abhinavreddy.guruva.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Experience {
    private String company;
    private Integer years;
    private ArrayList<String> skillsUsed;
}
