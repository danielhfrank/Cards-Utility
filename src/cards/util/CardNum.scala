package cards.util

case class CardNum(newVal : Any) extends Ordered[CardNum] {
	def num: Int = newVal match {
	case x: Int if 2 until 15 contains x => x;
	case x: String if CardNum.valueMap.keySet.contains(x) => CardNum.valueMap(x);
	//want to throw exception if constr arg not valid, OK
}

val value = newVal.toString;
override def compare(that: CardNum) = num - that.num;
override def toString = value;
}

object CardNum {
	val valueMap = Map("2" -> 2,"3" -> 3,"4" -> 4,"5" -> 5,"6" -> 6,"7" -> 7,"8" -> 8,"9" -> 9,"10" -> 10,
			"J"->11,
			"Q"->12,
			"K"->13,
			"A"->14)

}







