package beans.services;

import beans.models.UserAccount;

/**
 * Created by Анна on 17.09.2017.
 */
public interface UserAccountService {

    void withdrawMoney(UserAccount userAccount, double money);
    void putMoney(UserAccount userAccount, double money);

}
