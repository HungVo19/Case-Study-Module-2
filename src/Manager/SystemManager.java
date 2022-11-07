package Manager;

import java.io.Serializable;

public class SystemManager implements Serializable {
    private final AccountManager accountManager;
    private final ProductManager productManager;
    private final NotificationManager notificationManager;


    public SystemManager() {
        accountManager = new AccountManager();
        productManager = new ProductManager();
        notificationManager = new NotificationManager();
    }

    public AccountManager getAccountManager() {
        return accountManager;
    }
    public ProductManager getProductManager() {
        return productManager;
    }
    public NotificationManager getNotificationManager() {
        return notificationManager;
    }


}
