package cards.util
import cards.util._

class Deck {
	val cards = Deck.shuffle(Deck.allCards)
}

object Deck {
	val allCards = {
		var out: List[Card] = Nil
		Suit.s .foreach{ suit => CardNum.valueMap .keySet.foreach { cardnum =>
		out = Card(cardnum, suit) :: out;
		}
		}
		out
	}
	
	def main(args:Array[String]) = {
		Deck.allCards.foreach{card => println(card.toString)}
		println("********");
		var test = new Deck()
		test.cards .foreach{card => println(card.toString)}
	}
	

	def shuffle[T](xs: List[T]): List[T] = {
		//This is exactly what I was going to do anyway
		val rand = new scala.util.Random()//seed OK? Presumably, since that other guy was happy w it
		xs match {
		case List() => List()
		case xs => { 
			val i = rand.nextInt(xs.size);
			xs(i) :: shuffle(xs.take(i) ++ xs.drop(i+1))
		}
		}
}
	
	def deal: Array[List[Card]] = deal(4)
	
	/**
	 * Deals out all the cards to n players
	 * @param n
	 * @return
	 */
	def deal(n:Int): Array[List[Card]] = {
		var deck = new Deck()
		var playersHands: Array[List[Card]] = Array.fill(n)(Nil)
		def dealFromPartialDeck(cards: List[Card], numDealt: Int): Unit = cards match {
				case List() => Unit
				case x::xs=>{
					playersHands(numDealt % n) = x :: playersHands(numDealt % n)
					dealFromPartialDeck(xs, numDealt+1)
				}
		}
		dealFromPartialDeck(deck.cards, 0);
		playersHands;//this will be returned, ought to have all the cards distributed
	}
	
}