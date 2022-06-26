package application;
	
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.fateczl.zikagames.control.AluguelControl;
import br.com.fateczl.zikagames.control.ClienteControl;
import br.com.fateczl.zikagames.control.JogoControl;
import br.com.fateczl.zikagames.entity.Cliente;
import br.com.fateczl.zikagames.entity.Jogo;
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

public class Main extends Application {
	
	private TextField txtNomeCliente = new TextField();
	private TextField txtCpf = new TextField();
	private TextField txtDataNasc = new TextField();
	private TextField txtTelefone = new TextField();
	private TextField txtEmail = new TextField();
	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("Pesquisar");
	private Button btnAtualizar = new Button("Atualizar");
	private Button btnExcluir = new Button("Excluir");
	
	private TextField txtIdAluguel = new TextField();
	private ComboBox<String> cmbClientesAluguel = new ComboBox<>();	
	private ComboBox<String> cmbJogosAluguel = new ComboBox<>();
	private TextField txtDataDevolucao = new TextField();
	private TextField txtValor = new TextField();
	private Button btnAlugar = new Button("Alugar");
	private Button btnPesquisarAluguel = new Button("Pesquisar");
	private Button btnAtualizarAluguel = new Button("Atualizar");
	private Button btnExcluirAluguel = new Button("Excluir");
	
	private TextField txtIdJogo = new TextField();
	private TextField txtJogo = new TextField();
	private ComboBox<String> cmbClassInd = new ComboBox<>();
	private TextField txtPreco = new TextField();
	private Button btnAdicionarJogo = new Button("Adicionar");
	private Button btnPesquisarJogo = new Button("Pesquisar");
	private Button btnAtualizarJogo = new Button("Atualizar");
	private Button btnExcluirJogo = new Button("Excluir");
	
	private ClienteControl clienteControl = new ClienteControl();
	private JogoControl jogoControl = new JogoControl();
	private AluguelControl aluguelControl = new AluguelControl();
	
	private LocalDateStringConverter dtc = 
			new LocalDateStringConverter(DateTimeFormatter.ofPattern("dd/MM/yyyy"), null); 
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {		
			TabPane tabPane = new TabPane();
			tabPane.getTabs().addAll(clienteTab(), gameTab(), aluguelTab());
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
					
					carregarCombosAluguel();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			});
			
			btnPesquisar.setOnAction((e) -> {
				clienteControl.pesquisar();
			});
			
			btnAtualizar.setOnAction((e) -> {
				try {
					clienteControl.atualizar();
					txtNomeCliente.setText("");
					txtCpf.setText("");
					txtDataNasc.setText("");
					txtTelefone.setText("");
					txtEmail.setText("");
					JOptionPane.showMessageDialog(null, "Cliente atualizado com Sucesso!");
					carregarCombosAluguel();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			});
			
			btnExcluir.setOnAction((e) -> {
				clienteControl.excluir();
				JOptionPane.showMessageDialog(null, "Cliente excluído com Sucesso!");
				carregarCombosAluguel();
			});
			
			btnAdicionarJogo.setOnAction((e) -> {
				try {
					jogoControl.adicionar();
					txtJogo.setText("");
					txtPreco.setText("");
					JOptionPane.showMessageDialog(null, "Jogo adicionado com Sucesso!");
					
					carregarCombosAluguel();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			});
			
			btnPesquisarJogo.setOnAction((e) -> {
				jogoControl.pesquisar();
			});
			
			btnAtualizarJogo.setOnAction((e) -> {
				try {
					int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o id do jogo que deseja atualizar"));
					txtIdJogo.setText(Integer.toString(id));
					jogoControl.atualizar();
					txtIdJogo.setText("");
					txtJogo.setText("");
					txtPreco.setText("");
					JOptionPane.showMessageDialog(null, "Jogo atualizado com Sucesso!");
					
					carregarCombosAluguel();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
			});
			
			btnExcluirJogo.setOnAction((e) -> {
				int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o id do jogo que deseja excluir"));
				txtIdJogo.setText(Integer.toString(id));
				jogoControl.excluir();
				txtIdJogo.setText("");
				JOptionPane.showMessageDialog(null, "Jogo excluído com Sucesso!");
				
				carregarCombosAluguel();
			});
			
			btnAlugar.setOnAction((e) -> {
				try {
					aluguelControl.adicionar();
					txtValor.setText("");
					txtDataDevolucao.setText("");
					JOptionPane.showMessageDialog(null, "Aluguel realizado com Sucesso!");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			});
			
			btnPesquisarAluguel.setOnAction((e) -> {
				aluguelControl.pesquisar();
			});
			
			btnAtualizarAluguel.setOnAction((e) -> {
				try {
					int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o id do aluguel que deseja atualizar"));
					txtIdAluguel.setText(Integer.toString(id));
					aluguelControl.atualizar();
					txtValor.setText("");
					txtDataDevolucao.setText("");
					JOptionPane.showMessageDialog(null, "Aluguel atualizado com Sucesso!");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			});
			
			btnExcluirAluguel.setOnAction((e) -> {
				int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o id do aluguel que deseja excluir"));
				txtIdAluguel.setText(Integer.toString(id));
				aluguelControl.excluir();
				txtIdAluguel.setText("");
				txtValor.setText("");
				txtDataDevolucao.setText("");
				JOptionPane.showMessageDialog(null, "Aluguel excluído com Sucesso!");
			});
			
			Scene scene = new Scene(vBox, 650, 300);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Zika Games");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
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
		gridCliente.add(btnAtualizar, 2, 5);
		gridCliente.add(btnExcluir, 4, 5);
	}
	
	private void comunicacaoControlCliente() {
		Bindings.bindBidirectional(clienteControl.getNome(), txtNomeCliente.textProperty());
		Bindings.bindBidirectional(clienteControl.getCpf(), txtCpf.textProperty());
		Bindings.bindBidirectional(txtDataNasc.textProperty(), clienteControl.getDataNascimento(), dtc);
		Bindings.bindBidirectional(clienteControl.getEmail(), txtEmail.textProperty());
		Bindings.bindBidirectional(clienteControl.getTelefone(), txtTelefone.textProperty());
	}
	
	private Tab gameTab() {
		Tab tabGame = new Tab("Jogos");
		tabGame.setClosable(false);

		BorderPane borderGame = new BorderPane();
		GridPane gridGame = new GridPane();
		borderGame.setTop(gridGame);
		componentesGridGame(gridGame);
		gridGame.setVgap(5);
		gridGame.setHgap(10);

		comunicacaoControlJogo();

		borderGame.setCenter(jogoControl.getTable());

		tabGame.setContent(borderGame);

		return tabGame;
	}
	
	private void componentesGridGame(GridPane gridGame) {
		gridGame.add(new Label("Nome do Jogo: "), 0, 0);
		gridGame.add(txtJogo, 1, 0);
		gridGame.add(new Label("Classificação Indicativa: "), 2, 0);
		cmbClassInd.getItems().addAll("Livre", "12", "14", "16", "18");
		gridGame.add(cmbClassInd, 3, 0);
		gridGame.add(new Label("Preço: "), 0, 1);
		gridGame.add(txtPreco, 1, 1);
		gridGame.add(btnAdicionarJogo, 0, 5);
		gridGame.add(btnPesquisarJogo, 1, 5);
		gridGame.add(btnAtualizarJogo, 3, 5);
		gridGame.add(btnExcluirJogo, 4, 5);
	}

	@SuppressWarnings("unchecked")
	private void comunicacaoControlJogo() {
		Bindings.bindBidirectional(jogoControl.getNome(), txtJogo.textProperty());
		cmbClassInd.valueProperty().bindBidirectional(jogoControl.getClassificacaoInd());
		
		StringConverter<? extends Number> converterNumber = new NumberStringConverter();
		Bindings.bindBidirectional(txtPreco.textProperty(), jogoControl.getPreco(), (StringConverter<Number>)
				converterNumber);
		
		Bindings.bindBidirectional(txtIdJogo.textProperty(), jogoControl.getId(), (StringConverter<Number>)
				converterNumber);
	}
	
	private Tab aluguelTab() {
		Tab tabAluguel = new Tab("Aluguel");
		tabAluguel.setClosable(false);
		
		BorderPane borderAluguel = new BorderPane();
		GridPane gridAluguel = new GridPane();
		borderAluguel.setTop(gridAluguel);
		componentesGridAluguel(gridAluguel);
		gridAluguel.setVgap(5);
		gridAluguel.setHgap(10);
		
		carregarCombosAluguel();
		comunicacaoControlAluguel();
		
		borderAluguel.setCenter(aluguelControl.getTable());
		
		tabAluguel.setContent(borderAluguel);
		
		return tabAluguel;
	}
	
	private void componentesGridAluguel(GridPane gridAluguel) {
		gridAluguel.add(new Label("Clientes: "), 0, 0);
		gridAluguel.add(cmbClientesAluguel, 1, 0);
		gridAluguel.add(new Label("Jogos: "), 0, 1);
		gridAluguel.add(cmbJogosAluguel, 1, 1);
		gridAluguel.add(new Label("Valor: "), 2, 0);
		gridAluguel.add(txtValor, 3, 0);
		gridAluguel.add(new Label("Vencimento: "), 2, 1);
		gridAluguel.add(txtDataDevolucao, 3, 1);
		gridAluguel.add(btnAlugar, 0, 2);
		gridAluguel.add(btnPesquisarAluguel, 1, 2);
		gridAluguel.add(btnAtualizarAluguel, 3, 2);
		gridAluguel.add(btnExcluirAluguel, 4, 2);
	}
	
	private void carregarCombosAluguel() {
		cmbClientesAluguel.getItems().removeAll(cmbClientesAluguel.getItems());
		cmbJogosAluguel.getItems().removeAll(cmbJogosAluguel.getItems());
		txtNomeCliente.setText("");
		clienteControl.pesquisar();	
		jogoControl.pesquisar();
		List<String> clientesCombo = obterValoresComboClientes(clienteControl.getClientes());
		List<String> jogosCombo = obterValoresComboJogos(jogoControl.getJogos());
		cmbClientesAluguel.getItems().addAll(clientesCombo);
		cmbJogosAluguel.getItems().addAll(jogosCombo);
	}
	
	private List<String> obterValoresComboClientes(List<Cliente> listaClientes) {
		List<String> dadosCombo = new ArrayList<>();
		for (Cliente cliente : listaClientes) {
			dadosCombo.add(cliente.getCpf() + " - " + cliente.getNome());
		}
		
		return dadosCombo;
	}
	
	private List<String> obterValoresComboJogos(List<Jogo> listaJogos) {
		List<String> dadosCombo = new ArrayList<>();
		for (Jogo jogo : listaJogos) {
			dadosCombo.add(jogo.getId() + " - " + jogo.getNome());
		}
		
		return dadosCombo;
	}
	
	@SuppressWarnings("unchecked")
	private void comunicacaoControlAluguel() {
		cmbClientesAluguel.valueProperty().bindBidirectional(aluguelControl.getClienteOption());
		cmbJogosAluguel.valueProperty().bindBidirectional(aluguelControl.getJogoOption());
		
		StringConverter<? extends Number> converterNumber = new NumberStringConverter();
		Bindings.bindBidirectional(txtValor.textProperty(), aluguelControl.getValor(), (StringConverter<Number>)
				converterNumber);
		Bindings.bindBidirectional(txtDataDevolucao.textProperty(), aluguelControl.getDataDevolucao(), dtc);
	
		Bindings.bindBidirectional(txtIdAluguel.textProperty(), aluguelControl.getId(), (StringConverter<Number>)
				converterNumber);
	}
}
