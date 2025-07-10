
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class PizzaOrderPage {
    private VBox view;
    private TextField nameField, mobileField, toppingsField;
    private CheckBox xlBox, lBox, mBox, sBox;
    private TableView<PizzaOrder> table;
    private ObservableList<PizzaOrder> orders;

    public PizzaOrderPage() {
        view = new VBox(10);
        view.setPadding(new Insets(20));

        Label title = new Label("Pizza Kiosk");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        nameField = new TextField();
        nameField.setPromptText("Customer Name");

        mobileField = new TextField();
        mobileField.setPromptText("Mobile Number");

        toppingsField = new TextField();
        toppingsField.setPromptText("Number of Toppings");

        xlBox = new CheckBox("XL");
        lBox = new CheckBox("L");
        mBox = new CheckBox("M");
        sBox = new CheckBox("S");

        HBox sizeBox = new HBox(10, xlBox, lBox, mBox, sBox);

        Button createBtn = new Button("Create");
        Button readBtn = new Button("Read");
        Button updateBtn = new Button("Update");
        Button deleteBtn = new Button("Delete");
        Button clearBtn = new Button("Clear");

        orders = FXCollections.observableArrayList();
        SampleData.loadSampleData(orders); // Load sample data
        table = new TableView<>(orders);

        TableColumn<PizzaOrder, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(cell -> cell.getValue().nameProperty());
        TableColumn<PizzaOrder, String> mobileCol = new TableColumn<>("Mobile");
        mobileCol.setCellValueFactory(cell -> cell.getValue().mobileProperty());
        TableColumn<PizzaOrder, String> sizeCol = new TableColumn<>("Size");
        sizeCol.setCellValueFactory(cell -> cell.getValue().sizeProperty());
        TableColumn<PizzaOrder, Integer> toppingsCol = new TableColumn<>("Toppings");
        toppingsCol.setCellValueFactory(cell -> cell.getValue().toppingsProperty().asObject());
        TableColumn<PizzaOrder, Double> billCol = new TableColumn<>("Total Bill");
        billCol.setCellValueFactory(cell -> cell.getValue().billProperty().asObject());

        table.getColumns().addAll(nameCol, mobileCol, sizeCol, toppingsCol, billCol);

        createBtn.setOnAction(e -> {
            String name = nameField.getText();
            String mobile = mobileField.getText();
            int toppings = Integer.parseInt(toppingsField.getText());
            String size = getSelectedSize();
            double bill = BillCalculator.calculate(size, toppings);
            PizzaOrder order = new PizzaOrder(name, mobile, size, toppings, bill);
            orders.add(order);
        });

        readBtn.setOnAction(e -> {
            PizzaOrder selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                nameField.setText(selected.nameProperty().get());
                mobileField.setText(selected.mobileProperty().get());
                toppingsField.setText(String.valueOf(selected.toppingsProperty().get()));
                String size = selected.sizeProperty().get();
                xlBox.setSelected("XL".equals(size));
                lBox.setSelected("L".equals(size));
                mBox.setSelected("M".equals(size));
                sBox.setSelected("S".equals(size));
            }
        });

        updateBtn.setOnAction(e -> {
            PizzaOrder selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                String name = nameField.getText();
                String mobile = mobileField.getText();
                int toppings = Integer.parseInt(toppingsField.getText());
                String size = getSelectedSize();
                double bill = BillCalculator.calculate(size, toppings);
                
                selected.nameProperty().set(name);
                selected.mobileProperty().set(mobile);
                selected.sizeProperty().set(size);
                selected.toppingsProperty().set(toppings);
                selected.billProperty().set(bill);
            }
        });

        deleteBtn.setOnAction(e -> {
            PizzaOrder selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                orders.remove(selected);
            }
        });

        clearBtn.setOnAction(e -> {
            nameField.clear();
            mobileField.clear();
            toppingsField.clear();
            xlBox.setSelected(false);
            lBox.setSelected(false);
            mBox.setSelected(false);
            sBox.setSelected(false);
        });

        view.getChildren().addAll(title, nameField, mobileField, sizeBox, toppingsField,
                new HBox(10, createBtn, readBtn, updateBtn, deleteBtn, clearBtn), table);
    }

    private String getSelectedSize() {
        if (xlBox.isSelected()) return "XL";
        if (lBox.isSelected()) return "L";
        if (mBox.isSelected()) return "M";
        if (sBox.isSelected()) return "S";
        return "";
    }

    public VBox getView() {
        return view;
    }
}
