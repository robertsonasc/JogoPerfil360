## Jogo Perfil 360
Este jogo é baseado no jogo de tabuleiro Perfil 6 da empresa Grow, com algumas diferenças e adaptações.

## Para jogar 
#### Obs: Sobre o arquivo cartas.txt
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Este arquivo funciona como o "banco de dados" deste jogo. Perceba que ele possui um padrão de escrita, onde por exemplo, no trecho: 
`Sol-lugar:`
`Posso estar alto ou baixo.;`
`Tenho manchas.;`
"Sol" é a resposta, "lugar" é o tipo de coisa correspondente a resposta, e as frases que precedem o caractere ";" são as dicas, que sempre serão 20.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Uma ressalva importante sobre a possibilidade de modificação no arquivo cartas.txt, é que, se for seguido o padrão estabelecido de 5 cartas, e 20 dicas por carta, o jogo irá funcionar corretamente. 
#### Executando via IDE (Eclipse)
**Passe 1 - Clone o repositório:** Antes de qualquer coisa, você precisará ter o [git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git) instalado. Depois disso, clone o projeto para o seu computador, para isso, primeiro você deve entrar em uma pasta que deseja que o aplicativo execute, nela, execute o seguinte código:

`git clone https://github.com/robertsonasc/JogoPerfil360.git`

**Passo 2 - Abrindo o projeto com o Eclipse:** Abra a [IDE Eclipse](https://www.eclipse.org/downloads/), vá em File>Import>General>Existing Projects into Workspace, clique em "Next >" e abra a pasta do repositório que você acabou de clonar.

**Passo 3 - Modificando o arquivo TabuleiroControladorGeral.java:** Após realizar o passo 2, localize o arquivo TabuleiroControladorGeral.java (JogoPerfil>src>Controller), vá até a linha 65, e substitua o campo \<Caminho do arquivo cartas> pelo caminho do arquivo cartas.txt que você também baixou deste repositório - recomendo por em uma pasta de fácil acesso, ex: pasta Documents. Pronto, em seguida é só entrar no arquivo Tela.java (JogoPerfil>src>IO) e executar o projeto.

#### Executável .jar
**Passo 1 - Gerando o arquivo:** Caso você queira gerar o arquivo executável do jogo .jar, basta que depois de configurado os passos 1, 2, 3 da seção anterior, você vá até File>Export>Java>Runnable JAR file, clique em "Next >", em Launch configuration selecione Tela - JogoPerfil, informe o destino desejado do arquivo .jar e clique em "Finish".

**Passo 2 - Executando o arquivo:** No diretório que se encontra o arquivo .jar do jogo, execute o seguinte comando:

`java -jar nomeDoExecutavel.jar`

## Prints do Jogo
![](https://github.com/robertsonasc/JogoPerfil360/blob/master/prints/01%20-%20Perfil360.png "Informações iniciais")
***
![](https://github.com/robertsonasc/JogoPerfil360/blob/master/prints/02%20-%20Perfil360.png "Jogo executando")
***
![](https://github.com/robertsonasc/JogoPerfil360/blob/master/prints/03%20-%20Perfil360.png "Jogo executando")
