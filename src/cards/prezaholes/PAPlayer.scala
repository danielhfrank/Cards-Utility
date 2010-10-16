package cards.prezaholes
import cards.util._

abstract class PAPlayer(gm:PAGameManager) {
	var hand: List[Card] = Nil
	def move: Any
	def badMove(eMsg: String)
	def finalizeMove(move:Any) = move match {
		case x:Card => hand = hand.remove(card => card==x)
		case xs:List[Card] => hand= hand.remove(card => xs.contains(card))
		//anything else would be bad, so should throw error
	}
}