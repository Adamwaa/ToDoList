package io.github.Adam.Lang;

import io.github.Adam.HibernateUtil;

import java.util.Optional;

//Hibernate connection
public class LangRepository {
//Session open/commit transaction
    public Optional<Lang> findById(Integer id) {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        var result = session.get(Lang.class, id);
        transaction.commit();
        session.close();
        return Optional.ofNullable(result);

    }


}
