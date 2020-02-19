package sample;

public class Product implements Comparable<Product>{
    private static int tot_Order=1;
    private int productID ;
    private String productName;
    private String product_Description;
    private double product_Price;
    private static double coupon = 0.1;
    //private int quantity;

   public Product(){
        Product.tot_Order = Product.getTot_Inventory() + 1;
        this.productID = Product.getTot_Inventory();
    }

    public Product(String productName,String product_Description, double product_Price){
        Product.tot_Order = Product.getTot_Inventory() + 1;
        this.productID = Product.getTot_Inventory();
        this.productName = productName;
        this.product_Description = product_Description;
        this.product_Price = product_Price;

    }

    public static int getTot_Inventory() {
        return tot_Order;
    }

    public static double getCoupon() {
        return coupon;
    }

    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProduct_Price() {
        return product_Price;
    }

    public void setProduct_Price(double product_Price) {
        this.product_Price = product_Price;
    }


    public void removeorder(){
       productID -=1;
    }
    public String getProduct_Description() {
        return product_Description;
    }

    public void setProduct_Description(String product_Description) {
        this.product_Description = product_Description;
    }

    public static void changeCouponValue(double newcoupon){
       Product.coupon = newcoupon;
    }



    public String toString(){
       return getProductID() + " "+getProductName()+" "+getProduct_Description()+" "+getProduct_Price()+"\n";
    }



    @Override
    public int compareTo(Product other) {
       int compareProduct = this.product_Description.compareTo(other.getProduct_Description());
       if(this.getProduct_Description().charAt(0)<other.getProduct_Description().charAt(0)){
           return -1;
       }

       else if(this.getProduct_Description().charAt(0)>other.getProduct_Description().charAt(0)){
           return 1;
       }
       else{
           return 0;
       }

    }
}
