package beans.daos;

import beans.models.UserAccount;

/**
 * Created by Анна on 17.09.2017.
 */
public interface UserAccountDAO {

    UserAccount getAccount(long accountId);
    void updateAccount(UserAccount userAccount);
}
