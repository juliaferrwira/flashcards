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
            System.out.println("3 - Editar flashcard");
            System.out.println("4 - Excluir flashcard");
            System.out.println("5 - Estudar");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = lerInt();

            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> editar();
                case 4 -> excluir();
                case 5 -> estudar();
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

    public static void editar() {
        if (flashcards.isEmpty()) {
            System.out.println("Nenhum flashcard para editar!");
            return;
        }

        listar();
        System.out.print("\nDigite o número do flashcard para editar (0 volta): ");
        int num = lerInt();

        if (num == 0) return;
        if (num < 1 || num > flashcards.size()) {
            System.out.println("Número inválido!");
            return;
        }

        Flashcard f = flashcards.get(num - 1);

        System.out.println("\nEDITANDO FLASHCARD:");
        System.out.println("Pergunta atual: " + f.pergunta);
        System.out.println("Resposta atual: " + f.resposta);

        System.out.print("Nova pergunta (ENTER = manter): ");
        String novaPergunta = sc.nextLine();
        if (!novaPergunta.isBlank()) f.pergunta = novaPergunta;

        System.out.print("Nova resposta (ENTER = manter): ");
        String novaResposta = sc.nextLine();
        if (!novaResposta.isBlank()) f.resposta = novaResposta;

        System.out.println("\nFlashcard atualizado!");
    }

    public static void excluir() {
        if (flashcards.isEmpty()) {
            System.out.println("Nenhum flashcard para excluir!");
            return;
        }

        listar();
        System.out.print("\nDigite o número do flashcard para excluir (0 volta): ");
        int num = lerInt();

        if (num == 0) return;
        if (num < 1 || num > flashcards.size()) {
            System.out.println("Número inválido!");
            return;
        }

        Flashcard f = flashcards.get(num - 1);

        System.out.println("\nCONFIRMAR EXCLUSÃO:");
        System.out.println("Pergunta: " + f.pergunta);
        System.out.println("Resposta: " + f.resposta);
        System.out.print("ENTER = excluir | 0 = cancelar: ");

        String escolha = sc.nextLine();
        if (!escolha.equals("0")) {
            flashcards.remove(num - 1);
            System.out.println("Flashcard excluído!");
        } else {
            System.out.println("Exclusão cancelada.");
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
