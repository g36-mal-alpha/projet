package projet.commun;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import projet.data.Benevole;
import projet.data.Categorie;
import projet.data.Compte;
import projet.data.Epreuve;
import projet.data.Equipe;
import projet.data.Personne;
import projet.data.Poste;
import projet.data.Service;
import projet.data.Participant;
import projet.data.Sexe;
import projet.data.Hierarchie;


@Mapper
public interface IMapper {  
	
	Compte update( @MappingTarget Compte target, Compte source  );
	
	Categorie update( @MappingTarget Categorie target, Categorie source );
	
	Sexe update( @MappingTarget Sexe target, Sexe source );
	

	Hierarchie update( @MappingTarget Hierarchie target, Hierarchie source );

	@Mapping( target="categorie", expression="java( source.getCategorie() )" )
	Personne update( @MappingTarget Personne target, Personne source );

	Service update( @MappingTarget Service target, Service source );
	
	Poste update( @MappingTarget Poste target, Poste source );
	
	Participant update( @MappingTarget Participant target, Participant source );
	
	Benevole update( @MappingTarget Benevole target, Benevole source );
	
	Equipe update( @MappingTarget Equipe target, Equipe source );

	Epreuve update( @MappingTarget Epreuve target, Epreuve source );


	
	
}
