import org.zkoss.bind.annotation.Command
import org.zkoss.bind.annotation.NotifyChange

class zkMvvmViewModel{
    private int index
    private double price

    int getIndex() {
        return index
    }

    void setIndex(int index) {
        this.index = index
    }

    double getPrice() {
        return price
    }

    void setPrice(double price) {
        this.price = price
    }


    @Command
    @NotifyChange("index")
    void incrementIndexByTen(){
        index += 10
    }

    @Command
    @NotifyChange("price")
    void decrementPriceByFive(){
        price -= 5.0
    }








}
