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
    private String hum;

    public InfoObject(String temp, String co2, String hum) {
        setTemp(temp);
        setCo2(co2);
        setHum(hum);
    }

}

