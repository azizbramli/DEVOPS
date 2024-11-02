package tn.esprit.tpfoyer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;
import tn.esprit.tpfoyer.service.EtudiantServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
@ExtendWith(MockitoExtension.class)

class EtudiantServiceImplMockTest {
    @Mock
    EtudiantRepository etudiantRepository;  // Mock the repository

    @InjectMocks
    EtudiantServiceImpl etudiantService;  // Inject the mock into the service implementation
    Etudiant etudiant1 = new Etudiant(1, "Dupont", "Jean", 12345678, new Date(), new HashSet<>());
    Etudiant etudiant2 = new Etudiant(2, "Martin", "Marie", 87654321, new Date(), new HashSet<>());
    List<Etudiant> listEtudiants = new ArrayList<>() {{
        add(etudiant1);
        add(etudiant2);
    }};
    @Test
    public void testRetrieveAllEtudiants() {
        Mockito.when(etudiantRepository.findAll()).thenReturn(listEtudiants);

        List<Etudiant> etudiants = etudiantService.retrieveAllEtudiants();

        Assertions.assertNotNull(etudiants);
        Assertions.assertEquals(2, etudiants.size());
    }
    @Test
    public void testRetrieveEtudiant() {
        Mockito.when(etudiantRepository.findById(1L)).thenReturn(Optional.of(etudiant1));

        Etudiant foundEtudiant = etudiantService.retrieveEtudiant(1L);

        Assertions.assertNotNull(foundEtudiant);
        Assertions.assertEquals("Dupont", foundEtudiant.getNomEtudiant());
    }
    @Test
    public void testAddEtudiant() {
        Mockito.when(etudiantRepository.save(Mockito.any(Etudiant.class))).thenReturn(etudiant1);

        Etudiant addedEtudiant = etudiantService.addEtudiant(etudiant1);

        Assertions.assertNotNull(addedEtudiant);
        Assertions.assertEquals("Dupont", addedEtudiant.getNomEtudiant());
    }
    @Test
    public void testModifyEtudiant() {
        Mockito.when(etudiantRepository.save(Mockito.any(Etudiant.class))).thenReturn(etudiant1);

        Etudiant modifiedEtudiant = etudiantService.modifyEtudiant(etudiant1);

        Assertions.assertNotNull(modifiedEtudiant);
        Assertions.assertEquals("Dupont", modifiedEtudiant.getNomEtudiant());
    }
    @Test
    public void testRemoveEtudiant() {
        Mockito.doNothing().when(etudiantRepository).deleteById(1L);

        etudiantService.removeEtudiant(1L);

        Mockito.verify(etudiantRepository, Mockito.times(1)).deleteById(1L);
    }
    @Test
    public void testRecupererEtudiantParCin() {
        Mockito.when(etudiantRepository.findEtudiantByCinEtudiant(12345678)).thenReturn(etudiant1);

        Etudiant foundEtudiant = etudiantService.recupererEtudiantParCin(12345678);

        Assertions.assertNotNull(foundEtudiant);
        Assertions.assertEquals("Dupont", foundEtudiant.getNomEtudiant());
    }






}