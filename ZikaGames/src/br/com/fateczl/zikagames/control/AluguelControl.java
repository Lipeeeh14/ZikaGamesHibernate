package br.com.fateczl.zikagames.control;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.com.fateczl.zikagames.dao.AluguelDAO;
import br.com.fateczl.zikagames.dao.ClienteDAO;
import br.com.fateczl.zikagames.dao.JogoDAO;
import br.com.fateczl.zikagames.entity.Aluguel;
import br.com.fateczl.zikagames.entity.Cliente;
import br.com.fateczl.zikagames.entity.Jogo;
import br.com.fateczl.zikagames.entity.builder.AluguelBuilder;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AluguelControl implements IBaseControl<Aluguel> {
	
	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty clienteOption = new SimpleStringProperty();
	private StringProperty jogoOption = new SimpleStringProperty();
	private ObjectProperty<LocalDate> dataDevolucao = new SimpleObjectProperty<LocalDate>();
	private DoubleProperty valor = new SimpleDoubleProperty();

	private ObservableList<Aluguel> alugueis = FXCollections.observableArrayList();
	private TableView<Aluguel> table = new TableView<Aluguel>();
	
	@SuppressWarnings("unchecked")
	public AluguelControl() {
		TableColumn<Aluguel, Integer> idColumn = new TableColumn<Aluguel, Integer>("Id");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		TableColumn<Aluguel, String> clienteCpfColumn = new TableColumn<Aluguel, String>("CPF do Cliente");
		clienteCpfColumn.setCellValueFactory(new PropertyValueFactory<>("cliente"));

		TableColumn<Aluguel, String> jogoIdColumn = new TableColumn<Aluguel, String>("Id do Jogo");
		jogoIdColumn.setCellValueFactory(new PropertyValueFactory<>("jogo"));

		TableColumn<Aluguel, String> dataAluguelColumn = new TableColumn<Aluguel, String>("Data do Aluguel");
		dataAluguelColumn.setCellValueFactory((itemData) -> {
			LocalDate data = itemData.getValue().getDataAluguel();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			return new ReadOnlyStringWrapper(data.format(formatter));
		});

		TableColumn<Aluguel, String> dataDevolucaoColumn = new TableColumn<Aluguel, String>("Vencimento");
		dataDevolucaoColumn.setCellValueFactory((itemData) -> {
			LocalDate data = itemData.getValue().getDataDevolucao();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			;
			return new ReadOnlyStringWrapper(data.format(formatter));
		});

		TableColumn<Aluguel, String> valorColumn = new TableColumn<Aluguel, String>("Valor");
		valorColumn.setCellValueFactory(new PropertyValueFactory<>("valor"));

		TableColumn<Aluguel, String> ativoColumn = new TableColumn<Aluguel, String>("Ativo");
		ativoColumn.setCellValueFactory(new PropertyValueFactory<>("ativo"));

		TableColumn<Aluguel, String> emAtrasoColumn = new TableColumn<Aluguel, String>("Em Atraso");
		emAtrasoColumn.setCellValueFactory(new PropertyValueFactory<>("atraso"));

		table.getColumns().addAll(idColumn, clienteCpfColumn, jogoIdColumn, dataAluguelColumn, dataDevolucaoColumn, valorColumn,
				ativoColumn, emAtrasoColumn);

		table.setItems(alugueis);
	}
	
	public IntegerProperty getId() {
		return id;
	}

	public StringProperty getClienteOption() {
		return clienteOption;
	}

	public StringProperty getJogoOption() {
		return jogoOption;
	}

	public ObjectProperty<LocalDate> getDataDevolucao() {
		return dataDevolucao;
	}

	public DoubleProperty getValor() {
		return valor;
	}
	
	@Override
	public void adicionar() throws Exception {
		AluguelBuilder builder = AluguelBuilder.builder();
		ClienteDAO clienteDao = new ClienteDAO();
		JogoDAO jogoDao = new JogoDAO();
		AluguelDAO dao = new AluguelDAO();
		
		Cliente cliente = clienteDao.pesquisarPorCPF(clienteOption.get().split(" ")[0]);
		Jogo jogo = jogoDao.pesquisarPorId(
				Integer.parseInt(jogoOption.get().split(" ")[0]));
		Aluguel aluguel = builder.addCliente(cliente)
				.addJogo(jogo)
				.addDataAluguel()
				.addDataDevolucao(dataDevolucao.get())
				.addAtivo(true)
				.addValor(valor.get())
				.get();
		
		dao.adicionar(aluguel);
	}

	@Override
	public void pesquisar() {
		alugueis.clear();
		AluguelDAO dao = new AluguelDAO();
		alugueis.addAll(dao.pesquisar(clienteOption.get().split(" ")[0]));
	}

	@Override
	public void atualizar() throws Exception {
		AluguelBuilder builder = AluguelBuilder.builder();
		ClienteDAO clienteDao = new ClienteDAO();
		JogoDAO jogoDao = new JogoDAO();
		AluguelDAO dao = new AluguelDAO();
		
		Cliente cliente = clienteDao.pesquisarPorCPF(clienteOption.get().split(" ")[0]);
		Jogo jogo = jogoDao.pesquisarPorId(
				Integer.parseInt(jogoOption.get().split(" ")[0]));
		Aluguel aluguel = builder
				.addId(id.get())
				.addCliente(cliente)
				.addJogo(jogo)
				.addDataDevolucao(dataDevolucao.get())
				.addDataAluguel()
				.addAtivo(true)
				.addValor(valor.get())
				.get();
		
		dao.atualizar(aluguel);
	}

	@Override
	public void excluir() {
		AluguelBuilder builder = AluguelBuilder.builder();
		Aluguel aluguel = builder.addId(id.get()).get();
		
		AluguelDAO dao = new AluguelDAO();
		dao.excluir(aluguel);
	}
	
	public TableView<Aluguel> getTable() { return table; }
}
