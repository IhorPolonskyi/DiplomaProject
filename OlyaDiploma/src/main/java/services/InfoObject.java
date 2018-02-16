package services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoObject {
    private String data;
    private String temp;
    private String co2;

    public InfoObject(String temp, String co2) {
        setTemp(temp);
        setCo2(co2);
    }

}

