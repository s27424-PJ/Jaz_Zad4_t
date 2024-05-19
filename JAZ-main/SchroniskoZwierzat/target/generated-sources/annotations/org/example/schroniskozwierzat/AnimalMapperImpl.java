package org.example.schroniskozwierzat;

import com.example.api.model.AnimalRequest;
import com.example.api.model.AnimalResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-19T17:38:23+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class AnimalMapperImpl implements AnimalMapper {

    @Override
    public Animal mapToAnimal(AnimalRequest request) {
        if ( request == null ) {
            return null;
        }

        Animal animal = new Animal();

        animal.setName( request.getName() );
        animal.setSpecies( request.getSpecies() );
        if ( request.getAge() != null ) {
            animal.setAge( request.getAge() );
        }

        return animal;
    }

    @Override
    public AnimalResponse roResponse(Animal animal) {
        if ( animal == null ) {
            return null;
        }

        AnimalResponse animalResponse = new AnimalResponse();

        animalResponse.setId( animal.getId() );
        animalResponse.setName( animal.getName() );
        animalResponse.setSpecies( animal.getSpecies() );
        animalResponse.setAge( animal.getAge() );

        return animalResponse;
    }
}
