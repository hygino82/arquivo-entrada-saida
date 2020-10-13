package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		List<Product> lista = new ArrayList<>();
		
		String arqOrigem ="C:\\temp\\lista.txt";
		File origem = new File(arqOrigem);
		origem.getPath();
		
		System.out.print("Diretório para gravar: ");
		String diretorio = "c:\\temp";
		System.out.println(diretorio);
		//File path = new File(diretorio);

		System.out.println("Source file:");
		int n = 4;// número de produtos

		for (int i = 0; i < n; i++) {
			String produto = sc.nextLine();
			String p[] = produto.split(",");
			// separa a string pela vírgula
			String name = p[0];
			double price = Double.parseDouble(p[1]);
			int quantity = Integer.parseInt(p[2]);
			lista.add(new Product(name, price, quantity));
		}
		System.out.println("Fim da entrada de dados\n");
		System.out.println("Output file (out/summary.csv):");
		for (Product p : lista) {
			System.out.println(p);
		}
		//gravação do arquivo de saída
		boolean success = new File(diretorio + "\\out").mkdir();
		// cria a pasta out
		System.out.println("Directory created successfully: " + success);

		// gerar arquivo .csv
		File caminhoArquivo = new File(diretorio + "\\out\\summary.csv");

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))) {
			for (Product line : lista) {
				bw.write(line.toString());
				
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		sc.close();
	}

}
