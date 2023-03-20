
import java.util.*;
public class BlackJack {
    public static Scanner sc = new Scanner(System.in);
    public static boolean dilkontrol = false;
    public static String dil = "T";
    public static String karttur(){
        String kartcins[] = {"Sinek","Karo", "Maşa", "Kupa"};
        Random randcins = new Random();
        int randomindcins = randcins.nextInt(4);
        String karttur = kartcins[randomindcins];
        return karttur;
    } 
    public static String kartcek (String gor) {
        String kart[] = {"As","2", "3", "4", "5", "6", "7", "8", "9", "10", "Joker", "Kız", "Kral"};
        Random rand = new Random();
        int randomind = rand.nextInt(13);
        String kartcek = kart[randomind];
        if(gor == "Açık"){
            if(kartcek == "As"){
                System.out.println(" As çekildi.");
            }else {
                System.out.println(kartcek + " çekildi.");
            }
        }else if (gor == "Kapalı") {
            System.out.println("Kapalı kart çekildi.");
        }   
        return kartcek;
    }
    public static int Puanlar (int puan, String kart,boolean oyuncu) {
        int ilkpuan = 0;
        if(oyuncu == true){
            if(kart == "As" && puan<11){

                System.out.println("Hangi puanı tercih edersin: 1 yada 11?");
                sc.nextLine();
                while(!sc.hasNextInt()){
                    System.out.println("Lütfen Sayı Girin!!!");
                    sc.nextLine();
                }
                    int aspuan = sc.nextInt();
                    if (aspuan == 1) {
        
                        ilkpuan = 1;
                        System.out.println("1 puan kazanıldı.");
                    } else if (aspuan >= 11) {
        
                        ilkpuan = 11;
                        System.out.println("11 puan kazanıldı.");
                    } else {
        
                        System.out.println("Cevabın kabul edilmedi! 11 puan kazanıldı.");
                        ilkpuan = 11;
                    }
                 
            } else if (kart == "10" || kart == "Joker" || kart == "Kız" || kart == "Kral") {
    
                ilkpuan = 10;
                    System.out.println("10 puan kazanıldı.");
            } else if (kart == "As" && puan >= 11) {
    
                ilkpuan = 1;
                    System.out.println("1 puan kazanıldı.");
            } else {
    
                ilkpuan = Integer.parseInt(kart);
                System.out.println(kart + " puan kazanıldı.");
            }
        }else if(oyuncu == false){
            if(kart == "As" ){
                if(puan<11){
                    ilkpuan = 11;
                    System.out.println("Bilgisayar ası 11 puan olarak tercih ediyor.");
                 }else{
                     ilkpuan = 1;
                     System.out.println("Bilgisayar ası 1 puan olarak tercih ediyor.");
                 }
            }else if (kart == "10" || kart == "Joker" || kart == "Queen" || kart == "King") {
    
                ilkpuan = 10;
                    System.out.println("10 puan kazanıldı.");
            } else if (kart == "As" && puan >= 11) {
    
                ilkpuan = 1;
                    System.out.println("1 puan kazanıldı.");
            } else {
    
                ilkpuan = Integer.parseInt(kart);
                System.out.println(kart + " puan kazanıldı.");
            }
        }
        
        return ilkpuan;
    }
    public static int oyuncukartdagitim() {
        int oyuncupuan = 0;
        System.out.println("Oyuncunun sırası. iki(2)kart çekiliyor.");
        System.out.print(karttur() + " ");
        String oyuncukart1 = kartcek("Açık");
        oyuncupuan = Puanlar(oyuncupuan, oyuncukart1,true);
        System.out.print(karttur() + " ");
        String oyuncukart2 = kartcek("Açık");
        oyuncupuan += Puanlar(oyuncupuan, oyuncukart2,true);
        System.out.println("Oyuncunun puanı " + oyuncupuan + ".\n");
        return oyuncupuan;
    }
    public static String[] pckartdagitim() {
        int pcpuan = 0;
        System.out.println("Bilgisayarın sırası. iki(2)kart çekildi: biri açık diğeri kapalı.");
        System.out.print(karttur() + " ");
        String pcacikkart = kartcek("Açık");
        pcpuan = Puanlar(pcpuan, pcacikkart,false);
        String pckapalikart = kartcek("Kapalı");
        System.out.println("Bilgisayarın puanı " + pcpuan + ".\n");
        String[] tab = new String[3];
        tab[0] = pcacikkart;
        tab[1] = pckapalikart;
        tab[2] = String.valueOf(pcpuan);
        return tab;
    }
    public static char Devammi() {
        System.out.println("Kart çekmek ister misiniz? e or h");
        char oyuncudevam = sc.next().charAt(0);
        System.out.println();
        return oyuncudevam;
    }
    public static int yenikartcek(int puan) {
        int oyuncupuan = puan;
        System.out.print(karttur() + " ");
        String yenikart = kartcek("Açık");
        int kazanilanpuan = Puanlar(oyuncupuan, yenikart,true);
        oyuncupuan += kazanilanpuan;
        System.out.println("Yeni puanınız " + oyuncupuan + ".");
        return oyuncupuan;
    }
    public static int yenikartdagitim(int puan, char cevap) {
        int mevcutpuan = puan;   
        while ( mevcutpuan < 21 && (cevap == 'e' || cevap == 'E' )) {
            mevcutpuan = yenikartcek(mevcutpuan);
            if ( mevcutpuan > 21) {
                System.out.println("Kaybettin.");
                System.out.println();
            } else if ( mevcutpuan == 21 ) {
                System.out.println("Oyuncu puanı 21 oldu.");
                System.out.println();
            } else {
                cevap = Devammi();
            }
        }
        System.out.println("Oyuncu son puanı " + mevcutpuan + ".");
        return mevcutpuan;
    }
    public static int bitis(int oyuncupuan, int pcpuan, String acikkart, String kapalikart) {

        int yenipcpuan = pcpuan;
        if (oyuncupuan <= 21) {

            System.out.println("\nBilgisayarın açık kartı " + acikkart + ".");
            System.out.println("Bilgisayarın kapalı kartı açılır: bir "+karttur() + kapalikart + ".");
            int kapalikartpuan = Puanlar (pcpuan, kapalikart,false);
            yenipcpuan = pcpuan + kapalikartpuan;
            System.out.println("Bilgisayarın puanı " + yenipcpuan + ".\n");
            while (yenipcpuan < 17 ) {

                yenipcpuan = yenikartcek(yenipcpuan);
                if (yenipcpuan > 21) {

                    System.out.println("Bilgisayar kaybetti.");
                } else if ( yenipcpuan == 21 ) {

                    System.out.println("Bilgisayar 21 ulaştı.");
                } else {

                    System.out.println("Bilgisayarın son puan " + yenipcpuan + ".");
                }
            }
        }
        System.out.println();
        return yenipcpuan;
    }
    public static void kazanansec(int puan1, int puan2) {

        if ((puan1 > 21 || puan1 < puan2) && (puan2 < 22)) {

            System.out.println("Bilgisayar kazandı.\n");
        } else if ((puan2 > 21 || puan1 > puan2) && (puan1 < 22)) {

            System.out.println("Oyuncu kazandı.\n");
        } else if ((puan1 == puan2) && (puan1 < 22) && (puan2 < 22)) {

            System.out.println("Oyuncu ve bilgisayar berabere kaldı.\n");
        }
    }
    public static String cardtype(){
        String cardtype[] = {"Clubs","Diamonds", "Hearts", "Spades"};
        Random randtype = new Random();
        int randomind = randtype.nextInt(4);
        String cardstype = cardtype[randomind];
        return cardstype;
    }
    public static String drawCard (String visibility) {
        String cards[] = {"Ace","2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        Random r = new Random();
        int randomIndex = r.nextInt(13);
        String drawnCard = cards[randomIndex];
        if (visibility == "faceUp") {
            if (drawnCard == "Ace") {
                System.out.println("An Ace is drawn.");                               
            } else {
                System.out.println(drawnCard + " is drawn.");
            }
        } else if (visibility == "faceDown") {
                System.out.println("A face-down card is drawn.");
            }        
        return drawnCard;  
    }
    public static int assignPoints (int score, String card,boolean player) {
        int earnedPoints = 0;
        if(player == true){
            if(card == "Ace" && score<11){
                System.out.println("Which score you prefer: 1 or 11?");
                sc.nextLine();
                while(!sc.hasNextInt()){
                    System.out.println("Please Enter Number!!!");
                    sc.nextLine();
                }
                    int acepuan = sc.nextInt();
                    if (acepuan == 1) {
        
                        earnedPoints = 1;
                        System.out.println("1 point earned.");
                    } else if (acepuan >= 11) {
        
                        earnedPoints = 11;
                        System.out.println("11 point earned.");
                    } else {
        
                        System.out.println("Your answer is not accept! 11 point earned.");
                        earnedPoints = 11;
                    }
            } else if (card == "10" || card == "Jack" || card == "Queen" || card == "King") {
                earnedPoints = 10;
                System.out.println("10 point earned.");
            } else if (card == "Ace" && score >= 11) {
                earnedPoints = 1;
                System.out.println("1 point earned.");
            } else {
                earnedPoints = Integer.parseInt(card);
                System.out.println(card + " point earned.");
            }
        }else if(player == false){
            if(card == "Ace" ){
                if(score<11){
                    earnedPoints = 11;
                    System.out.println("Computer prefer Ace 11 point.");
                 }else{
                    earnedPoints = 1;
                     System.out.println("Computer prefer Ace 1 point.");
                 }
            }else if (card == "10" || card == "Jack" || card == "Queen" || card == "King") {
    
                earnedPoints = 10;
                System.out.println("10 point earned.");
            } else if (card == "Ace" && score >= 11) {
    
                earnedPoints = 1;
                System.out.println("1 point earned.");
            } else {
    
                earnedPoints = Integer.parseInt(card);
                System.out.println(card + " point earned.");
            }
        }
        return earnedPoints;
    }
    public static int playerCardsDistribution() {
        int playerScore = 0;
        System.out.println("Player's turn. Two cards are drawn.");
        System.out.print(cardtype() + " ");
        String playerCard1 = drawCard("faceUp");
        playerScore = assignPoints(playerScore, playerCard1,true);
        System.out.print(cardtype() + " ");
        String playerCard2 = drawCard("faceUp");
        playerScore += assignPoints(playerScore, playerCard2,true);
        System.out.println("The Player's score is " + playerScore + ".\n");
        return playerScore;
    }
    public static String[] computerCardsDistribution() {
        int computerScore = 0;
        System.out.println("Computer's turn. Two cards are drawn: one face-up and one face-down.");
        System.out.print(cardtype() + " ");
        String computerCardFaceUp = drawCard("faceUp");
        computerScore = assignPoints(computerScore, computerCardFaceUp,false);
        String computerCardFaceDown = drawCard("faceDown");
        System.out.println("The Computer's score is " + computerScore + ".\n");
        String[] tab = new String[3];
        tab[0] = computerCardFaceUp;
        tab[1] = computerCardFaceDown;
        tab[2] = String.valueOf(computerScore);
        return tab;
    }
    public static char askContinue() {
        System.out.println("Would you like another card? y or n");
        char continuePlayer = sc.next().charAt(0);
        System.out.println();
        return continuePlayer;
    }
    public static int drawNewCard(int score) {
        int playerScore = score;
        System.out.print(cardtype() + " ");
        String newCard = drawCard("faceUp");
        int earnedPoints = assignPoints(playerScore, newCard,true);
        playerScore += earnedPoints;
        System.out.println("The new score is " + playerScore + ".");
        return playerScore;
    }
    public static int newCardProcess(int score, char answer) {
        int currentScore = score;   
        while ( currentScore <= 21 && (answer == 'y' || answer == 'Y')) {
            currentScore = drawNewCard(currentScore);
            if ( currentScore > 21) {
                System.out.println("The Player loses.");
                System.out.println();
            } else if ( currentScore == 21 ) {
                System.out.println("The Player's score reaches 21.");
                System.out.println();
            } else {
                answer = askContinue();
            }
        }
        System.out.println("The Player's final score is " + currentScore + ".");
        return currentScore;
    }
    public static int endingProcess(int playerScore, int computerScore, String faceUpCard, String faceDownCard) {
        int newcomputerScore = computerScore;
        if (playerScore <= 21) {
            System.out.println("\nThe computer's face-up card was " + faceUpCard + ".");
            System.out.println("The computer's face-down card is revealed: it's a "+cardtype() + faceDownCard + ".");
            int faceDownCardPoints = assignPoints (computerScore, faceDownCard,false);
            newcomputerScore = computerScore + faceDownCardPoints;
            System.out.println("His score is " + newcomputerScore + ".\n");
            while (newcomputerScore < 17 ) {
                newcomputerScore = drawNewCard(newcomputerScore);
                if (newcomputerScore > 21) {
                    System.out.println("The computer loses.");
                } else if ( newcomputerScore == 21 ) {
                    System.out.println("The computer's score reaches 21.");
                } else {
                    System.out.println("The new computer's score is " + newcomputerScore + ".");
                }
            }
        }
        System.out.println();
        return newcomputerScore;
    }
    public static void winnerSelection(int score1, int score2) {
        if ((score1 > 21 || score1 < score2) && (score2 < 22)) {
            System.out.println("The computer wins.\n");
        } else if ((score2 > 21 || score1 > score2) && (score1 < 22)) {
            System.out.println("The Player wins.\n");
        } else if ((score1 == score2) && (score1 < 22) && (score2 < 22)) {
            System.out.println("The Player and the computer are ex aequo.\n");
        }
    }
    public static void main(String[] args) {
        if(!dilkontrol){
            dilsecim();
            dil = sc.next();
            dilkontrol = true;
        }
        if(dilkontrol){
            if(dil.equals("T")||dil.equals("t")||dil.equals("Turkish")||dil.equals("Türkçe")||dil.equals("TÜRKÇE")){
                int oyuncupuan = 0;
                int pcpuan = 0;
                char cevap;
                
                System.out.println("Oyun Başlatılıyor...");
                
                // Oyuncu sırası. Oyuncuya 2 kart ver
                oyuncupuan = oyuncukartdagitim();
        
                // Pc'nin sırası. Bilgisayara biri açık diğeri kapalı 2 kart ver.
                String [] masapcpuan = pckartdagitim();
                String pcacikkart = masapcpuan[0];
                String pckapalikart = masapcpuan[1];
                pcpuan = Integer.parseInt(masapcpuan[2]);
        
                // Oyuncu sırası: oyuncu devam edip etmeyeceğine karar vericek.
                cevap = Devammi();
        
                // Oyuncu puanı 21 eşit ve ya altı olduğu sürece her seferinde kart alıp almayacağına karar verecek.
                oyuncupuan = yenikartdagitim(oyuncupuan, cevap);
        
                // Pc kapalı kartını açar ve oyun devam eder
                pcpuan = bitis(oyuncupuan, pcpuan, pcacikkart, pckapalikart);
        
                //Kazanan seç
                kazanansec(oyuncupuan, pcpuan);
        
                //Tekrar başlat
                tekrar();
                
                }else if(dil.equals("I")||dil.equals("i")||dil.equals("English")||dil.equals("İngilizce")||dil.equals("ENGLISH")||dil.equals("e")||dil.equals("anObject")){
                int playerScore = 0;
                int computerScore = 0;
                char answer;
        
                System.out.println("Game starting...");
        
                // Player's turn. Two cards are given to the Player.
                playerScore = playerCardsDistribution();
        
                // Computer's turn. Two cards are given to the Computer: one face-up and one face-down.
                String [] tableScorecomputer = computerCardsDistribution();
                String computerFaceUpCard = tableScorecomputer[0];
                String computerFaceDownCard = tableScorecomputer[1];
                computerScore = Integer.parseInt(tableScorecomputer[2]);
        
                // Player's turn: he decides if he continues or not.
                answer = askContinue();
        
                // Until the player score is 21 or under, he can decide to ask for additional cards, one at a time.
                playerScore = newCardProcess(playerScore, answer);
        
                // The computer's face-down card is revealed and he plays.
                computerScore = endingProcess(playerScore, computerScore, computerFaceUpCard, computerFaceDownCard);
        
                // Winner selection.
                winnerSelection(playerScore, computerScore); 
        
                //Restarting
                restart();
                }else{
                System.out.println("Sistemde kayıtlı olmayan bir yazı girdiğiniz için otomatik olarak ingilizce ayarlandı.");
                System.out.println("Automatically chosen english because of you enter input is not registered on system.");
                int playerScore = 0;
                int computerScore = 0;
                char answer;
        
                System.out.println("Game starting...");
        
                // Player's turn. Two cards are given to the Player.
                playerScore = playerCardsDistribution();
        
                // computer's turn. Two cards are given to the computer: one face-up and one face-down.
                String [] tableScorecomputer = computerCardsDistribution();
                String computerFaceUpCard = tableScorecomputer[0];
                String computerFaceDownCard = tableScorecomputer[1];
                computerScore = Integer.parseInt(tableScorecomputer[2]);
        
                // Player's turn: he decides if he continues or not.
                answer = askContinue();
        
                // Until the player score is 21 or under, he can decide to ask for additional cards, one at a time.
                playerScore = newCardProcess(playerScore, answer);
        
                // The computer's face-down card is revealed and he plays.
                computerScore = endingProcess(playerScore, computerScore, computerFaceUpCard, computerFaceDownCard);
        
                // Winner selection.
                winnerSelection(playerScore, computerScore); 
        
                //Restarting
                restart();
                } 
        }
    }
    public static void dilsecim(){
        System.out.println("Which language you prefer?:English or Turkish?:T or E");
        System.out.println("Hangi dili tercih edersiniz?:İngilizce mi? ; Türkçe mi?:T yada E");
        
        dilkontrol = true;
    }
    public static char tekrar(){
        System.out.println("Tekrar oynamak ister misin?");
        char tekrar = sc.next().charAt(0);
        System.out.println();
        if(tekrar == 'e'||tekrar == 'E'){
            System.out.println("Tekrar Başlatılıyor...");
            main(null);
            
        }else{
            System.out.println("Oyundan çıkılıyor...");
            System.exit(0);
        }
        return tekrar;
    }
    public static char restart(){
        System.out.println("Do you want play one more game?");
        char restart = sc.next().charAt(0);
        System.out.println();
        if(restart == 'y'||restart == 'Y'){
            System.out.println("Starting Again...");
            main(null);
        }else{
            System.out.println("Quit Game...");
            System.exit(0);
        }
        return restart;
    }
}