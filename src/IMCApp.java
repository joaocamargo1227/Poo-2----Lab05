import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class IMCApp extends Application {

    private ObservableList<Pessoa> lista = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {

        TextField nomeField = new TextField();
        TextField alturaField = new TextField();
        TextField pesoField = new TextField();

        Label imcResultado = new Label("0.00");
        imcResultado.setStyle("-fx-font-size: 60px;");

        Button calcular = new Button("Calcular IMC");
        Button salvar = new Button("Salvar");
        Button carregar = new Button("Carregar Dados");

        GridPane form = new GridPane();
        form.setVgap(10);
        form.setHgap(10);

        form.add(new Label("Nome:"), 0, 0);
        form.add(nomeField, 1, 0);

        form.add(new Label("Altura:"), 0, 1);
        form.add(alturaField, 1, 1);

        form.add(new Label("Peso:"), 0, 2);
        form.add(pesoField, 1, 2);

        HBox botoes = new HBox(10, calcular, salvar, carregar);

        TableView<Pessoa> tabela = new TableView<>();

        TableColumn<Pessoa, Number> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(data ->
                new SimpleIntegerProperty(data.getValue().getId()));

        TableColumn<Pessoa, String> nomeCol = new TableColumn<>("Nome");
        nomeCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getNome()));

        TableColumn<Pessoa, String> alturaCol = new TableColumn<>("Altura");
        alturaCol.setCellValueFactory(data ->
                new SimpleStringProperty(String.valueOf(data.getValue().getAltura())));

        TableColumn<Pessoa, String> imcCol = new TableColumn<>("IMC");
        imcCol.setCellValueFactory(data ->
                new SimpleStringProperty(String.format("%.2f", data.getValue().getImc())));

        TableColumn<Pessoa, String> classCol = new TableColumn<>("Classificação");
        classCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getClassificacao()));

        tabela.getColumns().addAll(idCol, nomeCol, alturaCol, imcCol, classCol);
        tabela.setItems(lista);

        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox esquerda = new VBox(10, form, botoes);

        VBox direita = new VBox(10, new Label("Cálculo IMC"), imcResultado);

        HBox topo = new HBox(40, esquerda, direita);
        topo.setPadding(new Insets(15));

        VBox root = new VBox(10, topo, tabela);

        Scene scene = new Scene(root, 650, 450);

        stage.setTitle("Calculadora de IMC");
        stage.setScene(scene);
        stage.show();

        calcular.setOnAction(e -> {

            try {

                String nome = nomeField.getText();
                double altura = Double.parseDouble(alturaField.getText());
                double peso = Double.parseDouble(pesoField.getText());

                Pessoa pessoa = new Pessoa(nome, altura, peso);

                lista.add(pessoa);

                imcResultado.setText(String.format("%.2f", pessoa.getImc()));

                nomeField.clear();
                alturaField.clear();
                pesoField.clear();

            } catch (Exception ex) {

                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Erro");
                alert.setHeaderText("Entrada inválida");
                alert.setContentText("Digite valores válidos para altura e peso.");

                alert.showAndWait();
            }

        });

        salvar.setOnAction(e -> {
            ArquivoUtil.salvar(lista);
        });

        carregar.setOnAction(e -> {

            lista.clear();
            lista.addAll(ArquivoUtil.carregar());

        });

    }

    public static void main(String[] args) {
        launch();
    }
}