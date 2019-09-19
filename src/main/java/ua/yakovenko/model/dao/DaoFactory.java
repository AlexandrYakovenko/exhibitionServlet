package ua.yakovenko.model.dao;

import ua.yakovenko.model.dao.impl.DaoFactoryImpl;

public abstract class DaoFactory  {
    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();

    public abstract ExhibitionDao createExhibitionDao();

    public static DaoFactory getInstance(){
        if( daoFactory == null ){
            synchronized (DaoFactory.class){
                if(daoFactory==null){
                    DaoFactory temp = new DaoFactoryImpl();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }
}
