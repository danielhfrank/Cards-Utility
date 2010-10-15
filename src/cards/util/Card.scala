package cards.util

import cards.util._

case class Card(newCardNum: Any, newSuit: Any) {
	val myCardNum: CardNum = newCardNum match{
		case CardNum(x) => CardNum(x);
		case x:Any => CardNum(x);//if it's not already a cardnum, try to construct a cardnum
	}

	val mySuit: Suit = newSuit match {
		case Suit(x) => Suit(x);
		case x:String => Suit(x);
	}
	override def toString = myCardNum.toString() + " of " + mySuit.toString()

}



