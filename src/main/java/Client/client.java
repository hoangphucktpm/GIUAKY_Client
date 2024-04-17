package Client;

import iuh.fit.dao.IngredientDao;
import iuh.fit.dao.foodDao;
import iuh.fit.dao.impl.foodImpl;
import iuh.fit.dao.impl.ingredientImpl;
import iuh.fit.dao.impl.itemImpl;
import iuh.fit.dao.itemDao;
import iuh.fit.entity.Food;
import iuh.fit.entity.Ingredient;
import iuh.fit.entity.Type;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.List;

public class client {
    private static final String URL = "rmi://192.168.1.2:2951/";
    static foodDao f;
    static itemDao i;
    static IngredientDao in;

    @BeforeAll
    public static void setUp() throws RemoteException {
        f = new foodImpl();
        i = new itemImpl();
        in = new ingredientImpl();
    }
    @AfterAll
    public static void tearDown() {
        f = null;
        i = null;
        in = null;
    }

    @Test
    public void testAddFood() throws RemoteException {
        Food food = new Food("F4", "Pham Dang Khoi",
                100.0, "Paintfull Cake", true,
                null, Type.MAIN_COURSE, 10,  10);
        if(f.addFood(food)) {
            System.out.println("Thêm food thành công");
        } else {
            System.out.println("Thêm food thất bại");
        }
    }
    @Test
    public void testGetAllFood() throws RemoteException {
        f.getAllFood().forEach(System.out::println);
    }
    @Test
    public void testListItem() throws RemoteException {
        i.listItems("Supplier A").forEach(System.out::println);
    }
    @Test
    public void testListServing() throws RemoteException {
        f.getListServingFood().forEach(System.out::println);
    }
    @Test
    public void testListIngredient() throws RemoteException {
        in.findExpireDate().forEach(System.out::println);
    }
    @Test
    public void deleteIngredient() throws RemoteException {
        List<Ingredient> list = in.findExpireDate();
        for (Ingredient ingredient : list) {
            if(in.deleteIngredient(ingredient)) {
                System.out.println("Xóa thành công");
            } else {
                System.out.println("Xóa thất bại");
            }
        }
        in.findExpireDate().forEach(System.out::println);
    }
}
