object Scala {
    def main(args: Array[String]) {
      var num = 5;
      val nums : List[Int]= List(1,2,3,4,5)
      if(member(num,nums)){
          println("true")
      }
      println("true")
      println("false")
    }
   
   def member(a:Int, lizt:List[Int]): Boolean = {
       if( lizt == null ) {
           return false
       } else if (lizt.head == a) {
           return true
       } else {
           return member(a, lizt.tail)
       }
   }
}