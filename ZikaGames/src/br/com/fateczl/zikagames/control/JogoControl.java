package br.com.fateczl.zikagames.control;

import java.util.List;

import br.com.fateczl.zikagames.dao.JogoDAO;
import br.com.fateczl.zikagames.entity.Jogo;
import br.com.fateczl.zikagames.entity.builder.JogoBuilder;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class JogoControl implements IBaseControl<Jogo>{
	
	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty nome = new SimpleStringProperty();
	private StringProperty classificacaoInd = new SimpleStringProperty();
	private DoubleProperty preco = new SimpleDoubleProperty();
	
	private ObservableList<Jogo> jogos = FXCollections.observableArrayList();
	private TableView<Jogo> table = new TableView<Jogo>();
	
	@SuppressWarnings("unchecked")
	public JogoControl() {
		TableColumn<Jogo, Integer> idColumn = new TableColumn<Jogo, Integer>("Id");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		TableColumn<Jogo, String> nomeColumn = new TableColumn<Jogo, String>("Nome");
		nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		TableColumn<Jogo, String> classificacaoIndColumn = new TableColumn<Jogo, String>("Classificação Indicativa");
		classificacaoIndColumn.setCellValueFactory(new PropertyValueFactory<>("classificacaoInd"));
		
		TableColumn<Jogo, String> precoColumn = new TableColumn<Jogo, String>("Preço");
		precoColumn.setCellValueFactory(new PropertyValueFactory<>("preco"));
		
		table.getColumns().addAll(idColumn, nomeColumn, classificacaoIndColumn, precoColumn);
		
		table.setItems(jogos);
	}
	
	public IntegerProperty getId() {
		return id;
	}
	
	public StringProperty getNome() {
		return nome;
	}

	public StringProperty getClassificacaoInd() {
		return classificacaoInd;
	}

	public DoubleProperty getPreco() {
		return preco;
	}

	@Override
	public void adicionar() throws Exception {
		JogoBuilder builder = JogoBuilder.builder();
		
		Jogo jogo = builder.addNome(nome.get())
				.addClassificacaoInd(classificacaoInd.get())
				.addPreco(preco.get())
				.get();
		
		JogoDAO dao = new JogoDAO();
		dao.adicionar(jogo);
	}

	@Override
	public void pesquisar() {
		jogos.clear();
		JogoDAO dao = new JogoDAO();
		jogos.addAll(dao.pesquisar(nome.get()));
	}
	
	@Override
	public void atualizar() throws Exception {
		JogoBuilder builder = JogoBuilder.builder();
		
		Jogo jogo = builder.addId(id.get())
				.addNome(nome.get())
				.addClassificacaoInd(classificacaoInd.get())
				.addPreco(preco.get())
				.get();
		
		JogoDAO dao = new JogoDAO();
		dao.atualizar(jogo);
	}

	@Override
	public void excluir() {
		JogoBuilder builder = JogoBuilder.builder();
		Jogo jogo = builder.addId(id.get()).get();
		
		JogoDAO dao = new JogoDAO();
		dao.excluir(jogo);
	}
	
	public List<Jogo> getJogos() { return jogos; }
	
	public TableView<Jogo> getTable() { return table; }
}
