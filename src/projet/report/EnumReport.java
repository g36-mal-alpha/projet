package projet.report;


public enum EnumReport implements IEnumReport {

	
	// Valeurs
	
	ParticipantsList		( "participants/participantList.jrxml" ),
	;

	
	// Champs
	
	private String		path;

	
	// Constructeur 
	
	EnumReport( String path ) {
		this.path = path;
	}

	
	// Getters & setters

	@Override
	public String getPath() {
		return path;
	}

}
