import org.zkoss.bind.BindContext
import org.zkoss.bind.Converter
import org.zkoss.bind.ValidationContext
import org.zkoss.bind.Validator
import org.zkoss.bind.annotation.AfterCompose
import org.zkoss.bind.annotation.BindingParam
import org.zkoss.bind.annotation.Command
import org.zkoss.bind.annotation.ContextParam
import org.zkoss.bind.annotation.ContextType
import org.zkoss.bind.annotation.NotifyChange
import org.zkoss.bind.validator.AbstractValidator
import org.zkoss.zk.ui.Component
import org.zkoss.zk.ui.select.Selectors
import org.zkoss.zk.ui.select.annotation.Wire
import org.zkoss.zul.Column
import org.zkoss.zul.Grid

import java.text.SimpleDateFormat

class zkMvvmViewModel{

    // UI Components
    @Wire("#grid2")
    Grid grid2
    @Wire("#column1_grid2")
    Column column1_grid2

    @AfterCompose
    void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false)
    }

    private int index
    private double price
    private List<Item> itemList = [new Item("Item 1", "Description 1"), new Item("Item 2", "Description 2")]
    private boolean shouldDisplayInfo = false
    private boolean shouldDisplayPopup = false
    private String message =  "Initial message"
    private String creationDate = "20000101"

    private Converter dateConverter = new DateFormatConverter()


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

    boolean getShouldDisplayPopup() {
        return shouldDisplayPopup
    }

    void setShouldDisplayPopup(boolean shouldDisplayPopup) {
        this.shouldDisplayPopup = shouldDisplayPopup
    }

    String getMessage() {
        return message
    }

    void setMessage(String message) {
        this.message = message
    }

    String getCreationDate() {
        return creationDate
    }

    void setCreationDate(String creationDate) {
        this.creationDate = creationDate
    }

    Converter getDateConverter() {
        return dateConverter
    }

    void setDateConverter(Converter dateConverter) {
        this.dateConverter = dateConverter
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

    @Command
    @NotifyChange("shouldDisplayPopup")
    void popup(){
        shouldDisplayPopup = !shouldDisplayPopup
    }

    @Command
    @NotifyChange("message")
    void showIndex(@BindingParam("index") Integer index) {
        message = "item index: " + index
    }

    @Command
    @NotifyChange("itemList")
    void delete(@BindingParam("item") Item item ) {
        int i = itemList.indexOf(item)
        itemList.remove(item)
        message = "remove item index " + i
    }

    Validator getRangeValidator(){
        return new AbstractValidator() {
            @Override
            void validate(ValidationContext ctx) {
                Integer val = (Integer) ctx.getProperty().getValue()
                if (val < 200) {
                    println(grid2.isVflex())
                    println(column1_grid2.getParent())
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


    static class DateFormatConverter implements Converter{

        Object coerceToUi(Object beanProp, Component component, BindContext ctx) {
            final String format = (String) ctx.getConverterArg("format")
            if(format == null) throw new NullPointerException("format attribute not found.")
            return beanProp == null ? null : new SimpleDateFormat(format).parse(beanProp as String)
        }

        Object coerceToBean(Object compAttr, Component component, BindContext ctx) {
            return compAttr
        }
    }







}
