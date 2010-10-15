package cards.experiments
import cards.util._

object BridgeExp1 {

	val outputFile : String = "/Users/dan/Dropbox/Dropbox_Documents/Eclipse-Java/Cards/src/cards/experiments/output";

def main(args: Array[String]): Unit = { 
//		println("testing two digits")
//		println(twoDigits(0.12345).toString)
		val writer = new java.io.FileWriter(outputFile);
		var out: String = "\"If I have x hearts (btw me and dummy), what are the chances that "
		out += "one of my opponents has only y hearts in his hand?\"\n\n "
		for (i <- 0 until 7){
			out += ("\t"+ i.toString)
		}
		out += "\n"
		val results = runExperiment
		for(i <- 0 until 14){
			out += i.toString
			for(j <- 0 until 7){
				out += ("\t" + twoDigits(results(i)(j)).toString)
			}
			out += "\n"
		}
		//println(out)
		try{
			writer.write(out)
		}finally{
		writer.close
		}
		//Now I guess maybe consider doing a little computation?
		
}

def runExperiment: Array[Array[Double]] = {
	val trump = "H"
	val runsToDo = 1000000
	var occurrencesPerMyNumber = Array.fill(14)(0.0)
	var outputTable: Array[Array[Double]] = Array.fill(14,7)(0.0);
	for(i <- 0 until runsToDo){
		val playersHands = Deck.deal
		def countTrumps(player: Int) = playersHands(player).count(c => c.mySuit.equals(Suit(trump)))
		val myTrumps: Int = countTrumps(0) + countTrumps(2)
		val theirTrumps = Math.min(countTrumps(1), countTrumps(3))
		outputTable(myTrumps)(theirTrumps) += 1.0
		occurrencesPerMyNumber(myTrumps) += 1.0
	}
	//normalize. list comprehension won't let you modify elements
	for(i <- 0 until 14){
		for(j <- 0 until 7){
			var opmn = occurrencesPerMyNumber(i)
			if(opmn != 0.0) outputTable(i)(j) /= opmn
		}
	}
	return outputTable
	}

def twoDigits(d: Double): Double = {
	var tmp = d * 100.0;
	tmp = tmp.asInstanceOf[Int]
	tmp = tmp.asInstanceOf[Double] 
	tmp /= 100.0;
	return tmp;
}

def nDigits(numDigits: Int, d: Double): Double = {
	import Math._
	round(d * pow(10.0, numDigits)).asInstanceOf[Double] / 100.0
}

}
