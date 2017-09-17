package beans.daos.db;

import beans.daos.AbstractDAO;
import beans.daos.UserAccountDAO;
import beans.models.UserAccount;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;

/**
 * Created by Анна on 17.09.2017.
 */
@Repository
public class UserAccountDAOImpl extends AbstractDAO implements UserAccountDAO {

    @Override
    public UserAccount getAccount(long accountId) {
        return (UserAccount) createBlankCriteria(UserAccount.class).add(Restrictions.eq("id", accountId)).uniqueResult();
    }

    @Override
    public void updateAccount(UserAccount userAccount) {
        getCurrentSession().merge(userAccount);
    }

}
