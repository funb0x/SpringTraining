package beans.services.impl;

import beans.daos.UserAccountDAO;
import beans.models.UserAccount;
import beans.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountDAO userAccountDAO;

    @Override
    public void withdrawMoney(UserAccount userAccount, double money) {
        updateUserAccount(userAccount, money, Operation.WITHDRAW);
    }

    @Override
    public void putMoney(UserAccount userAccount, double money) {
        updateUserAccount(userAccount, money, Operation.PUT);
    }

    private void updateUserAccount(UserAccount userAccount, double money, Operation operation) {
        if (Objects.isNull(userAccount)) {
            throw new IllegalStateException("UserAccount cannot be null");
        }
        UserAccount savedUserAccount = userAccountDAO.getAccount(userAccount.getId());
        if (Objects.isNull(savedUserAccount)) {
            throw new IllegalStateException("UserAccount not found by id = " + userAccount.getId());
        }

        if (Operation.WITHDRAW == operation) {
            if (savedUserAccount.getValue() < money) {
                throw new RuntimeException("not enough money on user account to book a ticket");
            }
            savedUserAccount.setValue(savedUserAccount.getValue() - money);
        } else {
            savedUserAccount.setValue(savedUserAccount.getValue() + money);
        }

        userAccountDAO.updateAccount(savedUserAccount);
    }

    private enum Operation {
        WITHDRAW, PUT
    }

}
