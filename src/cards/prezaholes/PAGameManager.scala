package cards.prezaholes
import cards.util._

class PAGameManager(numPlayers: Int) {
	if (numPlayers > PAGameManager.MAX_PLAYERS)
		throw new IllegalArgumentException("Exceeded max allowable players");
	
	private var players:Array[PAPlayer] = Array.fill(numPlayers)(new PABot(this))
	
	var board:Any = null
	
	/**
	 * Make sure to check valid elsewhere!
	 * @return
	 */
	def boardNum:CardNum = board match{
		case x: Card => x.myCardNum
		case null => null
		case xs:List[Card]  => xs(0).myCardNum
	}
	
	/**
	 * Return OK if move is good, otherwise return an error message. Will be better this way
	 * when we have human players as well
	 * @param move
	 * @param player
	 * @return
	 */
	def validateMove(move:Any, player:PAPlayer): String = {
		if(!players.contains(player)) return "Bad player id, not in this game"
		move match {
			case x: Card => {
				if(!player.hand .contains(x)) return "Can't play "+x.toString+", it isn't in yr hand"
				if(x.myCardNum <= boardNum) return "Need to play a higher card than the board"
			}
			case "PASS" => "OK"//I think this is how we will do a pass..?
			case xs:List[Card] => {
				if(!PAGameManager.listOKAsMove(xs)) return "Not legit: mismatched numbers or something"
				xs.foreach{card => if(!player.hand .contains(card)) return "Can't play "+card.toString+", it isn't in yr hand"}
				if(xs(0).myCardNum <= boardNum) return "Need to play a higher card than the board"
			}
		}
		return "OK"
	}
	
	def runGame{
		var movesMade = 0
		var gameOver = false
		while(!gameOver){
			val player:PAPlayer = players(movesMade % numPlayers)
			val moveMade = player.move
			val response:String = validateMove(moveMade, player)
			if(!(response=="OK")) 
					player.badMove(response);
			//move is OK, so handle it
			moveMade match {
				case x: Card => board = x
				case xs:List[Card] => board = xs
				//can skip pass because we don't need to do anything, and we already know move valid
			}
			if (moveMade != "PASS") player.finalizeMove(moveMade)
			if (player.hand .isEmpty){
				gameOver = true;
				println("Game over: Player "+(movesMade % numPlayers)+" wins")
			}
		}
	}
	
}

object PAGameManager {
	val MAX_PLAYERS : Int = 8
	
	/**
	 * Perphaps this could have been done as a closure, but I think it will be reused so better here
	 */
	def listOKAsMove(move:List[Card]): Boolean = {
		if (move == Nil) return false
		val num: CardNum = move(0).myCardNum 
		move.foreach { card => if(card.myCardNum!=num) return false}
		return true
	}
}