import org.zkoss.bind.ValidationContext
import org.zkoss.bind.Validator
import org.zkoss.bind.annotation.Command
import org.zkoss.bind.annotation.NotifyChange
import org.zkoss.bind.validator.AbstractValidator

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


    public Validator getRangeValidator(){
        return new AbstractValidator() {
            @Override
            void validate(ValidationContext ctx) {
                Integer val = (Integer) ctx.getProperty().getValue()
                if (val < 200) {
                    println("invalid")
//                    addInvalidMessage(ctx, "intBox", "invalid input")
                }
            }
        }
    }







}
