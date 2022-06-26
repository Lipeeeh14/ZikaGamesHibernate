package br.com.fateczl.zikagames.boundary;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.fateczl.zikagames.control.ClienteControl;
import br.com.fateczl.zikagames.entity.Cliente;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.NumberStringConverter;

public class ZikaGamesBoundary extends Application {

	private TextField txtNomeCliente = new TextField();
	private TextField txtCpf = new TextField();
	private TextField txtDataNasc = new TextField();
	private TextField txtTelefone = new TextField();
	private TextField txtEmail = new TextField();
	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("Pesquisar");
	
	private ComboBox<String> cmbClientesAluguel = new ComboBox<>();	
	private ComboBox<String> cmbJogosAluguel = new ComboBox<>();
	private TextField txtDataDevolucao = new TextField();
	private TextField txtValor = new TextField();
	private Button btnAlugar = new Button("Alugar");
	private Button btnPesquisarAluguel = new Button("Pesquisar");
	
	private TextField txtJogo = new TextField();
	private ComboBox<String> cmbClassInd = new ComboBox<>();
	private TextField txtPreco = new TextField();
	private Button btnAdicionarJogo = new Button("Adicionar Game");
	private Button btnPesquisarJogo = new Button("Pesquisar Game");
	
	private ComboBox<String> cmbClientesVenda = new ComboBox<>();	
	private ComboBox<String> cmbJogosVenda = new ComboBox<>();
	private TextField txtValorVenda = new TextField();
	private Button btnVender = new Button("Vender");
	private Button btnPesquisarVenda = new Button("Pesquisar");
	
	private ClienteControl clienteControl = new ClienteControl();
	
	private LocalDateStringConverter dtc = 
			new LocalDateStringConverter(DateTimeFormatter.ofPattern("dd/MM/yyyy"), null); 
	
	public static void main(String[] args) {
		Application.launch(ZikaGamesBoundary.class, args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		TabPane tabPane = new TabPane();
		//tabPane.getTabs().addAll(clienteTab(), gameTab(), aluguelTab(), vendaTab());
		tabPane.getTabs().addAll(clienteTab());
		VBox vBox = new VBox(tabPane);
		
		btnAdicionar.setOnAction((e) -> {
			try {
				clienteControl.adicionar();
				
				clienteControl.getTable();
				txtNomeCliente.setText("");
				txtCpf.setText("");
				txtDataNasc.setText("");
				txtTelefone.setText("");
				txtEmail.setText("");
				JOptionPane.showMessageDialog(null, "Cliente Adicionado com Sucesso!");
				
				//carregarCombosAluguel();
				//carregarCombosVenda();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		});
		
		btnPesquisar.setOnAction((e) -> {
			clienteControl.pesquisar();
		});
		
		Scene scn = new Scene(vBox, 600, 300);
		stage.setScene(scn);
	
		stage.setTitle("Zika Games");
		stage.show();
	}
	
	private Tab clienteTab() {
		Tab tabCliente = new Tab("Cliente");
		tabCliente.setClosable(false);
		
		BorderPane borderCliente = new BorderPane();
		GridPane gridCliente = new GridPane();
		borderCliente.setTop(gridCliente);
		componentesGridCliente(gridCliente);
		gridCliente.setVgap(5);
		gridCliente.setHgap(10);
		
		comunicacaoControlCliente();
		
		borderCliente.setCenter(clienteControl.getTable());
		
		tabCliente.setContent(borderCliente);
		
		return tabCliente;
	}
	
	private void componentesGridCliente(GridPane gridCliente) {
		gridCliente.add(new Label("Nome do Cliente: "), 0, 0);
		gridCliente.add(txtNomeCliente, 1, 0);
		gridCliente.add(new Label("CPF: "), 2, 0);
		gridCliente.add(txtCpf, 3, 0);
		gridCliente.add(new Label("Data de Nascimento: "), 0, 1);
		gridCliente.add(txtDataNasc, 1, 1);
		gridCliente.add(new Label("Email: "), 2, 1);
		gridCliente.add(txtEmail, 3, 1);
		gridCliente.add(new Label("Telefone: "), 0, 2);
		gridCliente.add(txtTelefone, 1, 2);
		gridCliente.add(btnAdicionar, 0, 5);
		gridCliente.add(btnPesquisar, 1, 5);
	}
	
	private void comunicacaoControlCliente() {
		Bindings.bindBidirectional(clienteControl.getNome(), txtNomeCliente.textProperty());
		Bindings.bindBidirectional(clienteControl.getCpf(), txtCpf.textProperty());
		Bindings.bindBidirectional(txtDataNasc.textProperty(), clienteControl.getDataNascimento(), dtc);
		Bindings.bindBidirectional(clienteControl.getEmail(), txtEmail.textProperty());
		Bindings.bindBidirectional(clienteControl.getTelefone(), txtTelefone.textProperty());
	}

}
