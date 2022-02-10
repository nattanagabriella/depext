package br.org.nattana.depext;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.org.nattana.depext.model.Aluno;
import br.org.nattana.depext.model.Turma;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

public class Main {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	private static final String CONFIG_PROPERTIES = "./src/main/resources/config.properties";
	
	public static void main(String args[]) {
		logger.info("==== INÍCIO ====");
		
		final Turma turmaBanco = new Turma("Turma Banco de Dados", new ArrayList<Aluno>());
		final Turma turmaJava = new Turma("Turma Java", new ArrayList<Aluno>());
		
		final Properties prop = getConfigProperties();
		
		turmaBanco.readFromFile(Arquivo.BANCO_DADOS.getNomeArquivo(Operacao.LEITURA, prop));
		turmaJava.readFromFile(Arquivo.JAVA.getNomeArquivo(Operacao.LEITURA, prop));
		
		final Turma turmaGeral = turmaBanco.mergeWithAndSort("Turma Geral", turmaJava);
		
		turmaBanco.printToFile(Arquivo.BANCO_DADOS.getNomeArquivo(Operacao.ESCRITA, prop));
		turmaJava.printToFile(Arquivo.JAVA.getNomeArquivo(Operacao.ESCRITA, prop));
		turmaGeral.printToFile(Arquivo.GERAL.getNomeArquivo(Operacao.ESCRITA, prop));
		
		logger.info("==== FIM ====");
	}
	
	public static Properties getConfigProperties() {
		final Properties prop = new Properties();
		
		try (final Reader input = new FileReader(CONFIG_PROPERTIES)) {
			logger.debug("Lendo arquivo de configurações.");
			prop.load(input);
		} catch (final IOException e) {
			logger.error("Erro na leitura do arquivo de configurações: " + CONFIG_PROPERTIES, e);
			throw new RuntimeException(e);
		}
		
		return prop;
	}
	

	@RequiredArgsConstructor
	private enum Operacao {
		LEITURA("arq.in"),
		ESCRITA("arq.out");
		
	    @NonNull
	    @Getter
		private final String parteProp;
	    
	}
	
	@RequiredArgsConstructor
	private enum Arquivo {
		BANCO_DADOS("arq.nome.bancodados"),
		JAVA("arq.nome.java"),
		GERAL("arq.nome.geral");
		
	    @NonNull
		private final String key;

	    public String getNomeArquivo(final Operacao operacao, final Properties prop) {
	    	return prop.getProperty(operacao.parteProp + ".path") 
	    			+ prop.getProperty(key) 
	    			+ prop.getProperty(operacao.parteProp + ".sufixo");
	    }
	}
}
