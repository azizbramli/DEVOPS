package tn.esprit.tpfoyer.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.entity.Bloc;

import java.util.List;


@Repository
 public interface BlocRepository extends JpaRepository<Bloc, Long> {



   List<Bloc> findAllByCapaciteBlocGreaterThan(long c);


    // Récupérer tous les Blocs qui ont un nom qui commence par "Bl" :
    List<Bloc> findAllByNomBlocStartingWith(String c);


    // Récuprer tous les blocs qui ont un nom donné et une capacité donnée :
    List<Bloc> findAllByNomBlocAndCapaciteBloc (String nom , long capacite );


    // Récupérer le bloc qui a un nom donné :
    Bloc findByNomBloc (String nom);


    Bloc findBlocByNomBlocAndCapaciteBlocGreaterThan(String nb, long c);

    // List des blocs non affectés à aucun foyer :
    List<Bloc> findAllByFoyerIsNull();




}
