package br.org.nattana.depext.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Turma {
	private static final Logger logger = LoggerFactory.getLogger(Turma.class);
	
    @NonNull
    @Getter
	private String nome;
    
    @Getter
	private final Collection<Aluno> alunos;
    
	public Turma mergeWithAndSort(String nomeTurma, Turma outraTurma) {
    	final Turma turmaFinal = new Turma(nomeTurma, new TreeSet<Aluno>());
    	
    	turmaFinal.getAlunos().addAll(this.getAlunos());
    	turmaFinal.getAlunos().addAll(outraTurma.getAlunos());
    	
    	return turmaFinal;
    }
	
	public void printToFile(final String path) {
		logger.debug("Escrevendo informações no arquivo: " + path);
		try (final BufferedWriter output = new BufferedWriter(new FileWriter(path))) {
			output.write("TURMA: " + this.getNome());
			output.newLine();
			output.write("TOTAL: " + this.getAlunos().size());
			output.newLine();
			output.write("ALUNOS:");
			output.newLine();
			
			alunos.forEach(a -> {
				try {
					output.write(a.toString());
					output.newLine();
				} catch (final IOException e) {
					throw new RuntimeException(e);
				}
			});
			
			output.newLine();
		} catch (final Exception e) {
			logger.error("Erro ao manipular o arquivo: " + path, e);
			throw new RuntimeException(e);
		}
	}
	
	public void readFromFile(final String path) {
		logger.debug("Lendo alunos do arquivo: " + path);
        try (final BufferedReader input = new BufferedReader(new FileReader(path))) {
            String nomeCompleto;
            while ((nomeCompleto = input.readLine()) != null) {
                this.alunos.add(new Aluno(nomeCompleto));
            }
        } catch (IOException e) {
        	logger.error("Erro ao manipular o arquivo: " + path, e);
        	throw new RuntimeException(e);
        }
	}
}
