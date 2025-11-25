import java.util.*;

public class Main {

    static class Flashcard {
        String pergunta;
        String resposta;

        Flashcard(String pergunta, String resposta) {
            this.pergunta = pergunta;
            this.resposta = resposta;
        }
    }

    static Scanner sc = new Scanner(System.in);
    static List<Flashcard> flashcards = new ArrayList<>();

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("\nOPÇÕES:");
            System.out.println("1 - Cadastrar flashcard");
            System.out.println("2 - Listar flashcards");
            System.out.println("3 - Estudar");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = lerInt();

            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> estudar();
                case 0 -> System.out.println("Finalizando...");
                default -> System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    public static void cadastrar() {
        System.out.println("\nCADASTRAR FLASHCARD");

        while (true) {
            System.out.print("Digite a pergunta: ");
            String pergunta = sc.nextLine();

            System.out.print("Digite a resposta: ");
            String resposta = sc.nextLine();

            System.out.println("\nCONFIRMAR FLASHCARD:");
            System.out.println("Pergunta: " + pergunta);
            System.out.println("Resposta: " + resposta);
            System.out.print("ENTER = confirmar | 0 = editar: ");

            String escolha = sc.nextLine();
            if (!escolha.equals("0")) {
                flashcards.add(new Flashcard(pergunta, resposta));
                System.out.println("Flashcard cadastrado com sucesso!");
                return;
            }

            System.out.println("\nVoltando para edição...\n");
        }
    }

    public static void listar() {
        System.out.println("\n=== FLASHCARDS CADASTRADOS ===");

        if (flashcards.isEmpty()) {
            System.out.println("Nenhum flashcard cadastrado.");
            return;
        }

        int i = 1;
        for (Flashcard f : flashcards) {
            System.out.println(i + ") Pergunta: " + f.pergunta);
            System.out.println("   Resposta: " + f.resposta);
            i++;
        }
    }

    public static void estudar() {
        if (flashcards.isEmpty()) {
            System.out.println("Nenhum flashcard para visualização!");
            return;
        }

        List<Flashcard> copia = new ArrayList<>(flashcards);
        Collections.shuffle(copia);

        for (Flashcard f : copia) {
            System.out.println("\n--- PERGUNTA ---");
            System.out.println(f.pergunta);

            System.out.print("ENTER = ver resposta | 0 = menu: ");
            String cmd = sc.nextLine();
            if (cmd.equals("0")) return;

            System.out.println("\nResposta: " + f.resposta);

            System.out.print("\nENTER = próxima | 0 = menu: ");
            cmd = sc.nextLine();
            if (cmd.equals("0")) return;
        }

        System.out.println("\nVocê viu todas as perguntas!");
    }

    public static int lerInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.print("Digite um número válido: ");
            }
        }
    }
}
