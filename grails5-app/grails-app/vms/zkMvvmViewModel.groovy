import org.zkoss.bind.ValidationContext
import org.zkoss.bind.Validator
import org.zkoss.bind.annotation.Command
import org.zkoss.bind.annotation.NotifyChange
import org.zkoss.bind.validator.AbstractValidator

class zkMvvmViewModel{
    private int index
    private double price
    private List<Item> itemList = [new Item("Item 1", "Description 1"), new Item("Item 2", "Description 2")]
    private boolean shouldDisplayInfo = false

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

    List<Item> getItemList() {
        return itemList
    }

    boolean getShouldDisplayInfo() {
        return shouldDisplayInfo
    }

    void setShouldDisplayInfo(boolean shouldDisplayInfo) {
        this.shouldDisplayInfo = shouldDisplayInfo
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
//                    println("invalid")
                    addInvalidMessage(ctx, "intBox1", "invalid input")
                }
            }
        }
    }



    static class Item{
        String name
        String description

        Item(String name, String description) {
            this.name = name
            this.description = description
        }

        String getName() {
            return name
        }

        String getDescription() {
            return description
        }

        void setName(String name) {
            this.name = name
        }

        void setDescription(String description) {
            this.description = description
        }
    }







}
