package br.com.fateczl.zikagames.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import br.com.fateczl.zikagames.entity.Aluguel;
import br.com.fateczl.zikagames.entity.Cliente;
import br.com.fateczl.zikagames.entity.Jogo;
import br.com.fateczl.zikagames.entity.Venda;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	
	public HibernateUtil() {
	}
	
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();
				Properties prop = new Properties();
				prop.put(Environment.DRIVER, "org.mariadb.jdbc.Driver");
				prop.put(Environment.URL, "jdbc:mariadb://localhost:3306/zikagameshibernate?createDatabaseIfNotExist=true");
				prop.put(Environment.USER, "root");
				prop.put(Environment.PASS, "123");
				prop.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
				prop.put(Environment.SHOW_SQL, "true");
				prop.put(Environment.HBM2DDL_AUTO, "update");
				
				configuration.addProperties(prop);
				configuration.addAnnotatedClass(Cliente.class);
				configuration.addAnnotatedClass(Jogo.class);
				configuration.addAnnotatedClass(Aluguel.class);
				configuration.addAnnotatedClass(Venda.class);
				
				ServiceRegistry registry = new StandardServiceRegistryBuilder()
											.applySettings(configuration.getProperties())
											.build();
				sessionFactory = configuration.buildSessionFactory(registry);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return sessionFactory;
	}

}
