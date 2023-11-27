package rocket.Util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DBUtil {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    public static EntityManagerFactory getEmf()
    {
        return emf;
    }
}
