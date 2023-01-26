package dao;

public final class DaoFactory {
    public static UserDao getPastryDao() {
//        return new PastryJpaDao();
        return new UserJpaDao();
    }

}
