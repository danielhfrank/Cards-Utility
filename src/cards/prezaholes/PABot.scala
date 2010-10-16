package cards.prezaholes

class PABot(gm:PAGameManager) extends PAPlayer(gm) {
	override def move: Any = {
		return "PASS"
	}
	
	override def badMove(eMsg:String) = throw new Exception("Bot made a bad move, universe imploding")
}