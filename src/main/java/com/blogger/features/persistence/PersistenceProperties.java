package com.blogger.features.persistence;

import com.blogger.features.exceptions.DaoException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import static org.hibernate.cfg.AvailableSettings.JPA_JDBC_PASSWORD;
import static org.hibernate.cfg.AvailableSettings.JPA_JDBC_URL;
import static org.hibernate.cfg.AvailableSettings.JPA_JDBC_USER;

public class PersistenceProperties {

    private static final String DATABASE_URL = "DATABASE_URL";

    public Properties getConfigPersistence() {
        try {
            return variaveisAmbienteJdbc();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Properties variaveisAmbienteJdbc() throws DaoException {
        Properties props = new Properties();

        if (System.getenv().containsKey(DATABASE_URL)) {
            System.out.println(DATABASE_URL + " : " + System.getenv(DATABASE_URL));

            props.putAll(extrairInformacaoDaUrl());

        } else {
            throw new DaoException("Não foi  possível carregar as informaçõs da conexão");
        }
        return props;
    }

    private Map<String, String> extrairInformacaoDaUrl() {
        Map<String, String> mapJdbcProps = new HashMap<>();
        String url = System.getenv("DATABASE_URL");
        System.out.println("DATABASE_URL: ".concat(url));

        url = url.substring(url.indexOf("//") + 2);
        List<String> props = Arrays.asList(url.split("@"));
        List<String> userAndPasswords = Arrays.asList(props.get(0).split(":"));

        mapJdbcProps.put(JPA_JDBC_USER, userAndPasswords.get(0));
        mapJdbcProps.put(JPA_JDBC_PASSWORD, userAndPasswords.get(1));
        mapJdbcProps.put(JPA_JDBC_URL, "jdbc:postgresql://".concat(props.get(1)));
        System.out.println(props);
        return mapJdbcProps;
    }
}
