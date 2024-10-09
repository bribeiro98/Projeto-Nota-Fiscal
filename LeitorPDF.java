import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class LeitorPDF {

    public static void main(String[] args) {
        // Caminho da pasta com arquivos PDF
        String pastaCaminho = "Projeto/";
        
        try {
            // Listar todos os arquivos na pasta
            List<Path> arquivosPDF = Files.list(Paths.get(pastaCaminho))
                                          .filter(p -> p.toString().endsWith(".pdf"))
                                          .toList();
                                          
            // Processar cada arquivo PDF
            for (Path arquivo : arquivosPDF) {
                String conteudo = lerPDF(arquivo.toFile());
                System.out.println("Conteúdo do arquivo " + arquivo.getFileName() + ":");
                System.out.println(conteudo);
                System.out.println("========================================");
            }
            
        } catch (IOException e) {
            System.err.println("Erro ao processar arquivos PDF: " + e.getMessage());
        }
    }
    
    // Função para ler o conteúdo de um arquivo PDF
    public static String lerPDF(File file) {
        try (PDDocument document = Loader.loadPDF(file)) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo PDF " + file.getName() + ": " + e.getMessage());
        }
        return "";
    }
}