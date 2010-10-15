package cards.util


case class Suit(whichOne: String) {
	val which:String = whichOne match {
		case x if Suit.s contains x => Suit.shortNames(x)
//		case x: String => Suit.shortNames .values.foreach {arg => 
//		if(x.toLowerCase==arg.toLowerCase) arg}
	}
	override def toString = which;
}

object Suit{
	val shortNames = Map("H"->"Hearts", "C"->"Clubs", "D"-> "Diamonds", "S"->"Spades")
	val s = shortNames.keySet//as a shortcut
}

