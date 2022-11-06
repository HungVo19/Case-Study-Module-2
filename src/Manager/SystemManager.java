package Manager;

import java.io.Serializable;

public class SystemManager implements Serializable {
    private final AccountManager accountManager;
    private final ProductManager productManager;
    private final PromotionManager promotionManager;


    public SystemManager() {
        accountManager = new AccountManager();
        productManager = new ProductManager();
        promotionManager = new PromotionManager();
    }

    public AccountManager getAccountManager() {
        return accountManager;
    }
    public ProductManager getProductManager() {
        return productManager;
    }
    public PromotionManager getPromotionManager() {
        return promotionManager;
    }


}
