package IO;

import java.util.*;

import Controller.TabuleiroControladorGeral;
import Dados.Dica;
import Dados.DicaAcao;

public class Tela {
	
	private static Scanner entrada = new Scanner(System.in);
	
	public static void main(String[] args){
		do {
			quantosJogadores();
			System.out.println("\nBom, � importante dizer que os jogadores recebem um n�mero de identifica��o, por exemplo,"
					+ " se voc� informou que participar�o 3 jogadores,\n suas respectivas identifica��es ser�o: jogador 1, "
					+ "jogador 2, e jogador 3. Voc� sempre ser� o jogador 1. "
					+ "Lembrando que euzinho aqui, o PC,\n posso ganhar de voc�s todos hein, pois eu "
					+ "sempre serei o mediador u.u hahah... \nVamos come�ar.");
			TabuleiroControladorGeral.zeraPosiJog();
			TabuleiroControladorGeral.inicializaMediador();
			TabuleiroControladorGeral.inicializaCartas();
			TabuleiroControladorGeral.setJogadorDaVez(1);
			boolean alguemGanhou = false;
			
			while(!alguemGanhou) {
				System.out.println("\n##############\n\nPuxando uma nova carta...");
				String categoria = TabuleiroControladorGeral.puxarNovaCarta().getCategoria();
				TabuleiroControladorGeral.inicializaDicasSelec();
				
				do {
					String dicaStr = new String(); 
					do {
						System.out.println("\n[ EU SOU UM(A) "+ categoria + " ]");
						System.out.print("\nLista de dicas que foram escolhidas at� agora: ");
						TabuleiroControladorGeral.dicasJaSelec();
						mostraAndamentoPartida();
						System.out.println("VEZ DO JOGADOR " + TabuleiroControladorGeral.getJogadorDaVez());
						System.out.println("Escolha uma dica de 1 a 20 (que ainda n�o foi escolhida): ");
						dicaStr = entrada.nextLine();
					}while(!ehDicaValida(dicaStr));
					
					int dicaInt = Integer.parseInt(dicaStr);
					TabuleiroControladorGeral.addDicaJaSelec(dicaInt);
					
					Dica dicaDaVez = TabuleiroControladorGeral.getCartaAtual().getDicaNoi(dicaInt-1);
					
					if(dicaDaVez.acao()) {
						System.out.println("Essa dica � uma a��o: " + dicaDaVez.getTexto());
						int resAcao = realizarAcao(dicaDaVez);
						if(resAcao == 1) {
							System.out.println("Opaa! Parece que temos um vencedor da partida...");
							System.out.println("� VOC� MESMO JOGADOR " + TabuleiroControladorGeral.getJogadorDaVez());
							System.out.println("Meus parab�ns.\n#############");
							alguemGanhou = true;
							break;
						}
						if(resAcao != 0) {
							System.out.println("Opaa! Parece que temos um vencedor da partida...");
							System.out.println("� VOC� MESMO JOGADOR " + resAcao);
							System.out.println("Meus parab�ns.\n#############");
							alguemGanhou = true;
							break;
						}
						int jogadorDaVez = TabuleiroControladorGeral.getJogadorDaVez();
						TabuleiroControladorGeral.setJogadorDaVez(jogadorDaVez+1);
					}else {
						System.out.println("A dica �: " + dicaDaVez.getTexto());
						String opcao = new String();
						do {
							System.out.println("Voc� quer dar um palpite? (s/n)");
							opcao = entrada.nextLine();
						}while(!respostaSN(opcao));
						opcao = opcao.trim();
						if(opcao.equals("s")) {
							System.out.println("Qual seu palpite?");
							String palpite = entrada.nextLine();
							if(palpite.equalsIgnoreCase(TabuleiroControladorGeral.getCartaAtual().getOqSou())) {
								System.out.println("Voc� acertou o palpite com o total de "  + TabuleiroControladorGeral.getIndiceDicasJaSelec() + 
										" dicas relevadas. Boa!");
								int fichasVermelhas = TabuleiroControladorGeral.getIndiceDicasJaSelec();
								System.out.println("O jogador " + TabuleiroControladorGeral.getJogadorDaVez() + " est� avan�ando " + (20-fichasVermelhas) + 
										" posi��es.");
								if(TabuleiroControladorGeral.avancaPeao(20-fichasVermelhas) == 1) {
									System.out.println("Opaa! Parece que temos um vencedor da partida...");
									System.out.println("� VOC� MESMO JOGADOR " + TabuleiroControladorGeral.getJogadorDaVez());
									System.out.println("Meus parab�ns.\n#############");
									alguemGanhou = true;
									break;
								}
								System.out.println("O PC est� avan�ando " + fichasVermelhas + " posi��es.");
								if(TabuleiroControladorGeral.avancaMediador(fichasVermelhas) == 1) {
									System.out.println("WOOOW! Dessa vez as m�quinas levaram a melhor, eu (mediador computador) ganhei"
											+ " a partida! Obrigado.");
									alguemGanhou = true;
									break;
								}
								int jogadorDaVez = TabuleiroControladorGeral.getJogadorDaVez();
								TabuleiroControladorGeral.setJogadorDaVez(jogadorDaVez+1);
								break;
							}else {
								System.out.println("Voc� errou jovem, vez do pr�ximo jogador");
								int jogadorDaVez = TabuleiroControladorGeral.getJogadorDaVez();
								TabuleiroControladorGeral.setJogadorDaVez(jogadorDaVez+1);
							}
							
						}else {
							int jogadorDaVez = TabuleiroControladorGeral.getJogadorDaVez();
							TabuleiroControladorGeral.setJogadorDaVez(jogadorDaVez+1);
						}
					}
					if(TabuleiroControladorGeral.getIndiceDicasJaSelec() == 20) {
						System.out.println("Voc�s n�o acertaram nenhum palpite, agora eu (pc) vou avan�ar 20 posi��es uhuuul.");
						if(TabuleiroControladorGeral.avancaMediador(20) == 1) {
							System.out.println("WOOOW! Dessa vez as m�quinas levaram a melhor, eu (mediador computador) ganhei"
									+ " a partida! Obrigado.");
							alguemGanhou = true;
							break;
						}
					}
				}while(TabuleiroControladorGeral.getIndiceDicasJaSelec() < 20);
			}
		}while(jogarNovamente());
	}
	
	//Retorna true se a string recebida � uma n�mero de dica v�lida.
	private static boolean ehDicaValida(String str){
		str = str.trim();
		if(str.matches("[0-9]+")) {
			int i = Integer.parseInt(str);
			if(i >= 1 && i <= 20 && !contem(TabuleiroControladorGeral.getDicasJaSelec(), i)) {
				return true;
			}
		}
		System.out.println("Erro: informe uma dica v�lida.");
		return false;
	}
	//Retorna true se o valor est� contido no vetor.
	private static boolean contem(int vet[], int valor) {
		for(int i=0; i < vet.length; i++) {
			if(valor == vet[i]) {
				return true;
			}
		}
		return false;
	}
	
	//Return true se o jogador deseja jogar novamente.
	private static boolean jogarNovamente() {
		String str;
		do {
			System.out.println("Deseja jogar outra partida? (s/n)");
			str = entrada.nextLine();
		}while(!respostaSN(str));
		if(str.equals("s"))
			return true;
		return false;
	}
	//Retorna true se str for s ou n.
	private static boolean respostaSN(String str) {
		str = str.trim();
		if((str.length() == 1) && (str.equalsIgnoreCase("s") || str.equalsIgnoreCase("n"))){
			return true;
		}
		System.out.println("Erro: informe apenas 's' ou 'n'.");
		return false;
	}
	//L� quantos jogadores participar�o da partida (1 � 3). 
	private static void quantosJogadores() {
		System.out.println("#############\nBem vindo ao Jogo Perfil 360");
		String qtdJogadores = new String();
		do {
			System.out.println("A primeira coisa que voc� precisa fazer � me informar quantos jogadores ir�o participar (m�ximo 6):");
			qtdJogadores = entrada.nextLine();
		}while(!TabuleiroControladorGeral.setQtdJogadores(qtdJogadores));
	}
	//Executa a a��o que a dica informa.
	private static int realizarAcao(Dica dicaDaVez) {
		DicaAcao dicaAcao = (DicaAcao)dicaDaVez;
		char tipoAcao = dicaAcao.getTipoAcao();
		
		switch(tipoAcao) {
			case '%':{
				return 0;
			}
			case '$':{
				int qtdAvancos = dicaAcao.numNaDica();
				System.out.println("O jogador " + TabuleiroControladorGeral.getJogadorDaVez() + " est� avan�ando " + qtdAvancos + " posi��es.");
				return TabuleiroControladorGeral.avancaPeao(qtdAvancos);
			}
			case '&':{
				int qtdAvancos = dicaAcao.numNaDica();
				String strJogador = new String();
				do {
					System.out.println("Informe o n�mero do jogador (o mediador � representando como o ultimo jogador + 1): ");
					strJogador = entrada.nextLine();
				}while(!ehJogadorValido(strJogador) || !naoEhVoceMesmo(strJogador));
				int intJogador = Integer.parseInt(strJogador);
				if(intJogador == TabuleiroControladorGeral.getQtdJogadores()+1) {
					System.out.println("O PC est� avan�ando " + qtdAvancos + " posi��es.");
					return TabuleiroControladorGeral.avancaMediador(qtdAvancos);
				}
				System.out.println("O jogador " + intJogador + " est� avan�ando " + qtdAvancos + " posi��es.");
				if(TabuleiroControladorGeral.avancaPeaoDoJogadorNoi(intJogador, qtdAvancos) == 1)
					return intJogador;
				return TabuleiroControladorGeral.avancaPeaoDoJogadorNoi(intJogador, qtdAvancos);
			}
			case '|':{
				int qtdRecuo = dicaAcao.numNaDica();
				String strJogador = new String();
				do {
					System.out.println("Informe o n�mero do jogador (o mediador � representando como o ultimo jogador + 1): ");
					strJogador = entrada.nextLine();
				}while(!ehJogadorValido(strJogador) || !naoEhVoceMesmo(strJogador));
				int intJogador = Integer.parseInt(strJogador);
				if(intJogador == TabuleiroControladorGeral.getQtdJogadores()+1) {
					System.out.println("O PC est� voltando " + qtdRecuo + " posi��es.");
					TabuleiroControladorGeral.voltaMediador(qtdRecuo);
				}
				else {
					System.out.println("O jogador " + intJogador + " est� voltando " + qtdRecuo + " posi��es.");
					TabuleiroControladorGeral.voltaPeaoDoJogadorNoi(intJogador, qtdRecuo);
				}
				return 0;
			}
			default:
				return 0;
		}
	}
	//Retorna true se o jogador informado � v�lido
	private static boolean ehJogadorValido(String strJogador) {
		strJogador = strJogador.trim();
		if(strJogador.length() == 1 && strJogador.matches("[1-7]+")) {
			if(Integer.parseInt(strJogador) <= TabuleiroControladorGeral.getQtdJogadores()+1) {
				return true;
			}
		}
		System.out.println("Erro: jogador informado n�o � v�lido.");
		return false;
	}
	//Retorna true se o jogador informado n�o � o pr�prio jogador da vez.
	private static boolean naoEhVoceMesmo(String strJogador) {
		if(Integer.parseInt(strJogador) != TabuleiroControladorGeral.getJogadorDaVez()) {
			return true;
		}
		System.out.println("Erro: voc� n�o pode escolher voc� mesmo.");
		return false;
	}
	//Printa na tela em quais posi��es est�o os jogadores e o mediador.
	private static void mostraAndamentoPartida() {
		int linhas = TabuleiroControladorGeral.getQtdJogadores()+1;
		int chegada = TabuleiroControladorGeral.getFimPartida();
		String tabuleiro[][] = new String[linhas][chegada+2];
		for(int i=0; i < linhas; i++) {
			tabuleiro[i][0] = "[ ";
			tabuleiro[i][chegada+1] = " ]";
		}
		for(int i=0; i < linhas; i++) {
			for(int j = 1; j < chegada+1; j++) {
				if(j == chegada)
					tabuleiro[i][j] = "Chegada";
				else
					tabuleiro[i][j] = Integer.toString(j-1) + " ";
			}
		}
		for(int i=0; i < linhas; i++) {
			
			if(i+1 == linhas) 
				tabuleiro[i][TabuleiroControladorGeral.getMediador()+1] = "  (PC)  ";
			else
				tabuleiro[i][TabuleiroControladorGeral.getPeaoJogador(i)+1] = "(JOG " + Integer.toString(i+1) + ") ";
		}
		
		System.out.println("\nTabuleiro Atualmente:");
		for(int i=0; i < linhas; i++) {
			String strLinha = new String();
			for(int j = 0; j < chegada+2; j++) {
				strLinha += tabuleiro[i][j];
			}
			System.out.println(strLinha);
		}
		System.out.println();
	}
	
}















