package dev.abhinavreddy.guruva.customtypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExternalLinks {
    private String linkedIn;
    private String github;
    private String twitter;
}
