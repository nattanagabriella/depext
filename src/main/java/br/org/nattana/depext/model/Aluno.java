package br.org.nattana.depext.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import lombok.NonNull;

public class Aluno implements Comparable<Aluno> {
	private static final Logger logger = LoggerFactory.getLogger(Aluno.class);
	
    @NonNull
    @Getter
    private String nome;

    @Getter
    private String tratamento;
    
    private final Pattern pattern = Pattern.compile("((?<tratamento>[^\\.]+)\\. )?(?<nome>.*)"); 
    
    public Aluno(final String nomeCompleto) {
    	final Matcher m = this.pattern.matcher(nomeCompleto);
    	if(m.matches()) {
    		this.tratamento = m.group("tratamento");
    		this.nome = m.group("nome");
    	} else {
    		logger.error("Nome em formato inválido: " + nomeCompleto);
    		throw new RuntimeException();
    	}
    }

	@Override
	public int compareTo(final Aluno o) {
		return this.nome.compareTo(o.getNome());
	}
	
	@Override
	public String toString() {
		return (this.tratamento != null ? this.tratamento + ". " : "") + this.nome;
	}
}
