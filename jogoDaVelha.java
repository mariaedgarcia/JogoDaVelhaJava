import java.util.Scanner;

public class jogoDaVelha {

    public static void main(String[] args) {
        Espacos[][] jogo = new Espacos[3][3];
        char simboloAtual = 'X'; // Para vermos o símbolo de vez
        boolean game = true;
        String vitoria = "";
        Scanner scan = new Scanner(System.in); // Qual a posição desejada para marcar

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                jogo[i][j] = new Espacos();
            }
        }

        while (game) {
            desenhaJogo(jogo);
            vitoria = verificarVitoria(jogo);

            if (!vitoria.equals("")) {
                System.out.printf("Jogador %s venceu%n", vitoria);
                break;
            }

            try {
                int[] pos = jogar(scan, simboloAtual);
                boolean jogadaValida = verificarJogada(jogo, pos, simboloAtual);

                if (jogadaValida) {
                    simboloAtual = (simboloAtual == 'X') ? 'O' : 'X';
                } else {
                    System.out.println("Jogada inválida. Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Erro durante a jogada.");
                scan.nextLine(); // Limpa 
            }
        }

        System.out.println("Fim de jogo");
    }

    public static void desenhaJogo(Espacos[][] jogo) {
        System.out.println("     0   1   2");
        for (int i = 0; i < 3; i++) {
            System.out.print(" " + i + "  ");
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + jogo[i][j].getSimbolo() + " ");
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) System.out.println("    -----------");
        }
    }

    public static void limparTela() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static int[] jogar(Scanner scan, char simbolo) {
        int[] p = new int[2];
        System.out.printf("Quem joga: %c%n", simbolo);
        System.out.print("Informe a linha: ");
        p[0] = scan.nextInt();
        System.out.print("Informe a coluna: ");
        p[1] = scan.nextInt();
        return p;
    }

    public static boolean verificarJogada(Espacos[][] jogo, int[] p, char simboloAtual) {
        if (jogo[p[0]][p[1]].getSimbolo() == ' ') {
            jogo[p[0]][p[1]].setSimbolo(simboloAtual);
            return true;
        } else {
            return false;
        }
    }

    public static String verificarVitoria(Espacos[][] jogo) {
    
        for (int i = 0; i < 3; i++) {
            if (jogo[i][0].getSimbolo() != ' ' &&
                jogo[i][0].getSimbolo() == jogo[i][1].getSimbolo() &&
                jogo[i][1].getSimbolo() == jogo[i][2].getSimbolo()) {
                return String.valueOf(jogo[i][0].getSimbolo());
            }
        }
        return "";
    }
}

class Espacos {
    private char simbolo;

    public Espacos() {
        this.simbolo = ' ';
    }

    public char getSimbolo() {
        return this.simbolo;
    }

    public void setSimbolo(char simbolo) {
        if (this.simbolo == ' ') {
            this.simbolo = simbolo;
        } else {
            System.out.println("Campo já usado");
        }
    }
}
