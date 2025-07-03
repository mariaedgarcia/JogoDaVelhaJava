import java.util.Scanner;

public class JogoDaVelha {

    public static void imprimirTabuleiro(char[][] tabuleiro){
        limparTela();
        System.out.println();
        for (int linha = 0; linha < 3; linha++){
            for (int coluna = 0; coluna < 3; coluna++){
                System.out.print("\t"+tabuleiro[linha][coluna]);
                if (coluna < 2)
                    System.out.print("\t|");
            }
            System.out.println("");
            if (linha < 2)
                System.out.println("----------------------------------------------");
        }
        System.out.println();
    }

    public static void limparTela(){
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("Erro ao tentar limpar a tela");
        }
    }

    public static char verificarVencedor(char[][] tabuleiro) {

        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] != ' ' &&
                tabuleiro[i][0] == tabuleiro[i][1] &&
                tabuleiro[i][1] == tabuleiro[i][2]) {
                return tabuleiro[i][0];
            }
        }

        for (int j = 0; j < 3; j++) {
            if (tabuleiro[0][j] != ' ' &&
                tabuleiro[0][j] == tabuleiro[1][j] &&
                tabuleiro[1][j] == tabuleiro[2][j]) {
                return tabuleiro[0][j];
            }
        }

        if (tabuleiro[0][0] != ' ' &&
            tabuleiro[0][0] == tabuleiro[1][1] &&
            tabuleiro[1][1] == tabuleiro[2][2]) {
            return tabuleiro[0][0];
        }

        if (tabuleiro[0][2] != ' ' &&
            tabuleiro[0][2] == tabuleiro[1][1] &&
            tabuleiro[1][1] == tabuleiro[2][0]) {
            return tabuleiro[0][2];
        }

        return ' ';
    }

    public static void main(String[] args) {
        char[][] matriz = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '},
        };
        
        System.out.println("=== Jogo Da Velha ===");

        boolean jogoAcontecendo = true;
        char simboloAtual = 'X';
        int linha, coluna;

        Scanner entrada = new Scanner(System.in);

        while (jogoAcontecendo){
            imprimirTabuleiro(matriz);
            System.out.println("Vez do jogador " + simboloAtual);

            System.out.print("Informe a linha: ");
            linha = entrada.nextInt();
            System.out.print("Informe a coluna: ");
            coluna = entrada.nextInt();
            
            if (linha < 0 || linha > 2 || coluna < 0 || coluna > 2) {
                System.out.println("Essa posição não existe! Tente novamente entre 0 e 2.");
                continue;
            }

            if (matriz[linha][coluna] == ' ') {
                matriz[linha][coluna] = simboloAtual;

                char vencedor = verificarVencedor(matriz);
                if (vencedor != ' ') {
                    imprimirTabuleiro(matriz);
                    System.out.println("Jogador " + vencedor + " venceu o jogo!");
                    break; // Fim
                }

                simboloAtual = (simboloAtual == 'X') ? 'O' : 'X';
            } else {
                System.out.println("Posição ocupada! Tente novamente.");
            }
        }

        entrada.close();
        System.out.println("Fim de jogo");
    }
}