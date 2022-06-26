package br.com.fateczl.zikagames.control;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.com.fateczl.zikagames.dao.ClienteDAO;
import br.com.fateczl.zikagames.entity.Cliente;
import br.com.fateczl.zikagames.entity.builder.ClienteBuilder;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClienteControl implements IBaseControl<Cliente> {
	
	private StringProperty nome = new SimpleStringProperty();
	private StringProperty cpf = new SimpleStringProperty();
	private ObjectProperty<LocalDate> dataNascimento = new SimpleObjectProperty<LocalDate>();
	private StringProperty email = new SimpleStringProperty();
	private StringProperty telefone = new SimpleStringProperty();
	
	private ObservableList<Cliente> clientes = FXCollections.observableArrayList();
	private TableView<Cliente> table = new TableView<Cliente>();
	
	@SuppressWarnings("unchecked")
	public ClienteControl() {
		TableColumn<Cliente, String> nomeColumn = new TableColumn<Cliente, String>("Nome");
		nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		TableColumn<Cliente, String> cpfColumn = new TableColumn<Cliente, String>("CPF");
		cpfColumn.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		
		TableColumn<Cliente, String> dataNascColumn = new TableColumn<Cliente, String>("Data de Nascimento");
		dataNascColumn.setCellValueFactory((itemData) -> {
			LocalDate data = itemData.getValue().getDataNascimento();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			return new ReadOnlyStringWrapper(data.format(formatter));
		});
		
		TableColumn<Cliente, String> emailColumn = new TableColumn<Cliente, String>("Email");
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
		
		TableColumn<Cliente, String> telefoneColumn = new TableColumn<Cliente, String>("Telefone");
		telefoneColumn.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		
		table.getColumns().addAll(nomeColumn, cpfColumn, dataNascColumn, emailColumn, telefoneColumn);
		
		table.setItems(clientes);
	}
	
	public StringProperty getNome() {
		return nome;
	}

	public void setNome(StringProperty nome) {
		this.nome = nome;
	}

	public StringProperty getCpf() {
		return cpf;
	}

	public void setCpf(StringProperty cpf) {
		this.cpf = cpf;
	}

	public ObjectProperty<LocalDate> getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(ObjectProperty<LocalDate> dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public StringProperty getEmail() {
		return email;
	}

	public void setEmail(StringProperty email) {
		this.email = email;
	}

	public StringProperty getTelefone() {
		return telefone;
	}

	public void setTelefone(StringProperty telefone) {
		this.telefone = telefone;
	}

	@Override
	public void adicionar() throws Exception {
		ClienteBuilder builder = ClienteBuilder.builder();
		
		Cliente cliente = builder.addCpf(cpf.get())
				.addNome(nome.get())
				.addDataNascimento(dataNascimento.get())
				.addTelefone(telefone.get())
				.addEmail(email.get())
				.get();
		
		ClienteDAO dao = new ClienteDAO();
		dao.adicionar(cliente);
	}

	@Override
	public void pesquisar() {
		clientes.clear();
		ClienteDAO dao = new ClienteDAO();
		clientes.addAll(dao.pesquisar(nome.get()));
	}

	@Override
	public void atualizar() throws Exception {
		ClienteBuilder builder = ClienteBuilder.builder();
		
		Cliente cliente = builder.addCpf(cpf.get())
				.addNome(nome.get())
				.addDataNascimento(dataNascimento.get())
				.addTelefone(telefone.get())
				.addEmail(email.get())
				.get();
		
		ClienteDAO dao = new ClienteDAO();
		dao.atualizar(cliente);
	}

	@Override
	public void excluir() {
		ClienteBuilder builder = ClienteBuilder.builder();
		Cliente cliente = builder.addCpf(cpf.get()).get();
		
		ClienteDAO dao = new ClienteDAO();
		dao.excluir(cliente);
	}
	
	public TableView<Cliente> getTable() { return table; }
}
