package services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServerObject {
    private String dbName;
    private String host;
    private String user;
    private String password;
    private String comPort;
    private String table;
}
