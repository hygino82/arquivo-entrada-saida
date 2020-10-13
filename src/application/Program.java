package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import entities.Product;

public class Program {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Locale.setDefault(Locale.US);
		List<Product> lista = new ArrayList<>();
		
		String arqOrigem ="/home/hygino/Documentos/lista.txt";
		
		File origem = new File(arqOrigem);
		
		System.out.println(origem.getPath());
		//mostra caminho completo até o arquivo
		System.out.print("Diretório para gravar: ");
		String diretorio = origem.getParent();
		//mostra caminho completo até a pasta em que está o arquivo
		System.out.println(diretorio);
		
		File caminhoArquivo = new File(diretorio + "/out/summary.csv");
		
		//gravação do arquivo de saída
		boolean success = new File(diretorio + "/out").mkdir();
		// cria a pasta out
		System.out.println("Directory created successfully: " + success);
		
		
		//arquivo de saída
		try (BufferedReader bf = new BufferedReader(new FileReader(origem))){
			String linha = bf.readLine();
		
			//lê a linha
			while (linha!= null) {
				String p[] = linha.split(",");
				String name = p[0];
				double price = Double.parseDouble(p[1]);
				int quantity = Integer.parseInt(p[2]);
				lista.add(new Product(name, price, quantity));
				linha = bf.readLine();
			}
			
		}
		catch (IOException e) {
			System.out.println("Ocorreu algum erro");
		}
		
		
		
		System.out.println("Fim da entrada de dados\n");
		//System.out.println("Output file (out/summary.csv):");
		for (Product p : lista) {
			System.out.println(p);
		}
		
		
		
		

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))) {
			for (Product line : lista) {
				bw.write(line.toString());
				
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		
		System.out.print("\nArquivo gravado em: ");
		System.out.println(caminhoArquivo.getPath());
	}

}
