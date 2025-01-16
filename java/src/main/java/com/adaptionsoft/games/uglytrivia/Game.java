package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class Game {

	public static int NB_QUESTIONS = 50;
    
	private ArrayList<Player> players = new ArrayList<>();
    boolean[] inPenaltyBox  = new boolean[6];
    
    /*LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();*/

	ArrayList<Question> questions = new ArrayList<>(NB_QUESTIONS);
    
    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;
    
    public  Game(){
    	for (int i = 0; i < NB_QUESTIONS; i++) {
			/*popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast(("Science Question " + i));
			sportsQuestions.addLast(("Sports Question " + i));
			rockQuestions.addLast(createRockQuestion(i));*/
			questions.set(i, getQuestion(i));
    	}
    }

	/*public String createRockQuestion(int index){
		return "Rock Question " + index;
	}*/

	public Question getQuestion(int index) {
		Category cat = Category.Pop;
		if (index % 3 == 0) 
			cat = Category.Rock;
		else if (index % 2 == 0)
			cat = Category.Sports;
		else if (index % 1 == 0)
			cat = Category.Science;
		return new Question(cat);
	}

	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public boolean add(String playerName) {
		
		
	    players.add(new Player(playerName));
	    //places[howManyPlayers()] = 0;
	    //purses[howManyPlayers()] = 0;
	    inPenaltyBox[howManyPlayers()] = false;
	    
	    System.out.println(playerName + " was added");
		int nbPlayers = howManyPlayers();
	    System.out.println("They are " + nbPlayers + ((nbPlayers>1)?" players":" player"));
		return false;
	}
	
	public int howManyPlayers() {
		return players.size();
	}

	public void roll(int roll) {
		System.out.println(players.get(currentPlayer) + " is the current player");
		System.out.println("They have rolled a " + roll);
		
		if (inPenaltyBox[currentPlayer]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				
				System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
				movePlayerAndAskQuestion(roll);
			} else {
				System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}
			
		} else {

			movePlayerAndAskQuestion(roll);
		}
		
	}

	private void movePlayerAndAskQuestion(int roll) {
		Player curPlayer = players.get(currentPlayer);
		int position = curPlayer.getPosition() + roll;
		if (position> 11) position -= 12;
		curPlayer.setPosition(position);

		System.out.println(players.get(currentPlayer)
                + "'s new location is "
                + position);
		Question question =askQuestion();
		System.out.println("The category is " + question.getCategory());
	}

	private Question askQuestion() {
		return questions.remove(questions.size()-1);		
	}
	
	
	/*private String currentCategory() {
		if (places[currentPlayer] == 0) return "Pop";
		if (places[currentPlayer] == 4) return "Pop";
		if (places[currentPlayer] == 8) return "Pop";
		if (places[currentPlayer] == 1) return "Science";
		if (places[currentPlayer] == 5) return "Science";
		if (places[currentPlayer] == 9) return "Science";
		if (places[currentPlayer] == 2) return "Sports";
		if (places[currentPlayer] == 6) return "Sports";
		if (places[currentPlayer] == 10) return "Sports";
		return "Rock";
	}*/

	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[currentPlayer]){
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = 0;
				Player curPlayer = players.get(currentPlayer);
				curPlayer.addCoin();
				//purses[currentPlayer]++;
				System.out.println(curPlayer
						+ " now has "
						+ curPlayer.getNbCoins()
						+ " Gold Coins.");

				boolean winner = didPlayerWin();

				return winner;
			} else {
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = 0;
				return true;
			}
			
			
			
		} else {
		
			System.out.println("Answer was corrent!!!!");
			purses[currentPlayer]++;
			System.out.println(players.get(currentPlayer) 
					+ " now has "
					+ purses[currentPlayer]
					+ " Gold Coins.");
			
			boolean winner = didPlayerWin();
			currentPlayer++;
			if (currentPlayer == players.size()) currentPlayer = 0;
			
			return winner;
		}
	}
	
	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(players.get(currentPlayer)+ " was sent to the penalty box");
		inPenaltyBox[currentPlayer] = true;
		
		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;
		return true;
	}


	private boolean didPlayerWin() {
		return !(purses[currentPlayer] == 6);
	}
}
