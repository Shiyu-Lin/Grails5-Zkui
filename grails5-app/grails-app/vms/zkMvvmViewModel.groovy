import groovyjarjarpicocli.CommandLine
import org.zkoss.bind.BindContext
import org.zkoss.bind.Converter
import org.zkoss.bind.ValidationContext
import org.zkoss.bind.Validator
import org.zkoss.bind.annotation.AfterCompose
import org.zkoss.bind.annotation.BindingParam
import org.zkoss.bind.annotation.Command
import org.zkoss.bind.annotation.ContextParam
import org.zkoss.bind.annotation.ContextType
import org.zkoss.bind.annotation.DefaultCommand
import org.zkoss.bind.annotation.Destroy
import org.zkoss.bind.annotation.GlobalCommand
import org.zkoss.bind.annotation.Immutable
import org.zkoss.bind.annotation.Init
import org.zkoss.bind.annotation.NotifyChange
import org.zkoss.bind.validator.AbstractValidator
import org.zkoss.lang.Library
import org.zkoss.mesg.Messages
import org.zkoss.zk.ui.Component
import org.zkoss.zk.ui.Executions
import org.zkoss.zk.ui.select.Selectors
import org.zkoss.zk.ui.select.annotation.Wire
import org.zkoss.zul.Column
import org.zkoss.zul.Grid

import java.text.SimpleDateFormat

class zkMvvmViewModel extends parentViewModel{

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
    private boolean shouldDisplayInfo = true
    private boolean shouldDisplayPopup = false
    private String message =  "Initial message"
    private String creationDate = "20000101"
    private Converter dateConverter = new DateFormatConverter()
    private boolean selection = true
    private Book currentBook = new Book("556677", null, null, 0.00)
    private Book anotherBook = new Book("778899", "Another book", "Someone2", 29.99)
    private NeverChange neverChangeObject = new NeverChange("never change object", 99.98)


    @Init(superclass = true)
    void init(){
        println("this function will do something when the viewmodel is initialized")
    }

    @Destroy
    void destroyViewModel(){
        println("this function will do something when the viewmodel is concluded")
    }

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

    boolean getSelection() {
        return selection
    }

    void setSelection(boolean selection) {
        this.selection = selection
    }

    Book getCurrentBook() {
        return currentBook
    }

    void setCurrentBook(Book currentBook) {
        this.currentBook = currentBook
    }

    Book getAnotherBook() {
        return anotherBook
    }

    void setAnotherBook(Book anotherBook) {
        this.anotherBook = anotherBook
    }

    NeverChange getNeverChangeObject() {
        return neverChangeObject
    }

    void setNeverChangeObject(NeverChange neverChangeObject) {
        this.neverChangeObject = neverChangeObject
    }

    String concat(String str1, String str2){
        return str1 + " " + str2
    }

    void save(){
        println("form submitted")
        println(currentBook)
    }

    @DefaultCommand
    void defaultCommand(){
        println("Triggered default command")
    }

    @GlobalCommand
    void refresh(){
        println("refresh in global command")
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

    @Command
    @NotifyChange("currentBook")
    void updateBookId(){
        currentBook.setId("112233")
    }

    @Command
    @NotifyChange("neverChangeObject")
    void updateNCOValue(){
        neverChangeObject.setValue(123.334)
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

    static class Book{
        String id
        String name
        String author
        double price

        Book() {
        }

        Book(String id, String name, String author, double price) {
            this.id = id
            this.name = name
            this.author = author
            this.price = price
        }

        String getId() {
            return id
        }

        void setId(String id) {
            this.id = id
        }

        String getName() {
            return name
        }

        void setName(String name) {
            this.name = name
        }

        String getAuthor() {
            return author
        }

        void setAuthor(String author) {
            this.author = author
        }

        double getPrice() {
            return price
        }

        void setPrice(double price) {
            this.price = price
        }


        @Override
        public String toString() {
            return "Book{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", author='" + author + '\'' +
                    ", price=" + price +
                    '}';
        }
    }

    @Immutable
    static class NeverChange{
        String message
        double value

        NeverChange(String message, double value) {
            this.message = message
            this.value = value
        }

        String getMessage() {
            return message
        }

        void setMessage(String message) {
            this.message = message
        }

        double getValue() {
            return value
        }

        void setValue(double value) {
            this.value = value
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
