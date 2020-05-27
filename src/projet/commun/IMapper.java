package projet.commun;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import projet.data.Benevole;
import projet.data.Categorie;
import projet.data.Compte;
import projet.data.Personne;
import projet.data.Poste;
import projet.data.Service;
import projet.data.Participant;


@Mapper
public interface IMapper {  
	
	Compte update( @MappingTarget Compte target, Compte source  );
	
	Categorie update( @MappingTarget Categorie target, Categorie source );

	@Mapping( target="categorie", expression="java( source.getCategorie() )" )
	Personne update( @MappingTarget Personne target, Personne source );

	Service update( @MappingTarget Service target, Service source );
	
	Poste update( @MappingTarget Poste target, Poste source );
	
	Participant update( @MappingTarget Participant target, Participant source );
	
	Benevole update( @MappingTarget Benevole target, Benevole source );
	
}
