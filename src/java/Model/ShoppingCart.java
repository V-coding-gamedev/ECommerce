package Model;

public class ShoppingCart {
    private int card_id;
    private int user_id;
    private  int product_id;
    private int quantity; 

    public ShoppingCart() {
    }

    public ShoppingCart(int card_id, int user_id, int product_id, int quantity) {
        this.card_id = card_id;
        this.user_id = user_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    public int getCard_id() {
        return card_id;
    }

    public void setCard_id(int card_id) {
        this.card_id = card_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" + "card_id=" + card_id + ", user_id=" + user_id + ", product_id=" + product_id + ", quantity=" + quantity + '}';
    }
}
