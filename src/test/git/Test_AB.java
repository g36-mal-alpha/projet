package test.git;

public class Test_AB {
	
	
	public void bonjour() {
<<<<<<< HEAD
		System.out.println( "Bonjour" );
=======
		System.out.println( "Adieu" );
>>>>>>> branch 'master' of https://github.com/g36-mal-alpha/projet
	}
	
	
	private String[] adresses = {
			"14 Rue Mozart, Paris",
			"77 Rue Picasso, Toulouse", 
			"53 Rue des fleurs, Limoges",
	};

	
	public String getAdresse( int i ) {

		if ( 0 <= i && i < adresses.length ) {
			return adresses[i];
		} else {
			return null;
		}
	}
	
}
