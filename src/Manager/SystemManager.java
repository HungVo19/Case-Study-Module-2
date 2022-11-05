package Manager;

import MenuPrinter.MenuPrinter;
import Validation.Validation;

import java.io.Serializable;

public class SystemManager implements Serializable {
    private AccountManager accountManager;
    private ProductManager productManager;
    private MenuPrinter menuPrinter;
    private Validation validation;

    public SystemManager() {
        accountManager = new AccountManager();
        productManager = new ProductManager();
        menuPrinter = new MenuPrinter();
        validation = new Validation();
    }

    public AccountManager getAccountManager() {
        return accountManager;
    }

    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public ProductManager getProductManager() {
        return productManager;
    }

    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }

    public MenuPrinter getMenuPrinter() {
        return menuPrinter;
    }

    public void setMenuPrinter(MenuPrinter menuPrinter) {
        this.menuPrinter = menuPrinter;
    }
}
