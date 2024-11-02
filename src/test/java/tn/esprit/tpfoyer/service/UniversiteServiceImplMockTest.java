package tn.esprit.tpfoyer.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.repository.UniversiteRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UniversiteServiceImplMockTest {

    @Mock
    UniversiteRepository universiteRepository;  // Mock the repository

    @InjectMocks
    UniversiteServiceImpl universiteService;  // Inject the mock into the service implementation

    // Dummy instances of Universite
    Universite universite1;
    Universite universite2;
    List<Universite> listUniversites;

    @BeforeEach
    void setUp() {
        // Initialize dummy Universite instances
        universite1 = new Universite();
        universite1.setIdUniversite(1L);
        universite1.setNomUniversite("University One");
        universite1.setAdresse("Address One");

        universite2 = new Universite();
        universite2.setIdUniversite(2L);
        universite2.setNomUniversite("University Two");
        universite2.setAdresse("Address Two");

        listUniversites = new ArrayList<>();
        listUniversites.add(universite1);
        listUniversites.add(universite2);
    }

    @Test
    public void testRetrieveAllUniversites() {
        // Mock the repository's findAll method
        Mockito.when(universiteRepository.findAll()).thenReturn(listUniversites);

        // Call the service method
        List<Universite> universites = universiteService.retrieveAllUniversites();

        // Assertions
        Assertions.assertNotNull(universites);
        Assertions.assertEquals(2, universites.size());
        Assertions.assertEquals("University One", universites.get(0).getNomUniversite());

        // Verify interaction with the repository
        Mockito.verify(universiteRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void testRetrieveUniversite() {
        // Mock the repository's findById method
        Mockito.when(universiteRepository.findById(1L)).thenReturn(Optional.of(universite1));

        // Call the service method
        Universite retrievedUniversite = universiteService.retrieveUniversite(1L);

        // Assertions
        Assertions.assertNotNull(retrievedUniversite);
        Assertions.assertEquals("University One", retrievedUniversite.getNomUniversite());

        // Verify interaction with the repository
        Mockito.verify(universiteRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    public void testAddUniversite() {
        // Mock the repository's save method
        Mockito.when(universiteRepository.save(Mockito.any(Universite.class))).thenReturn(universite1);

        // Call the service method
        Universite addedUniversite = universiteService.addUniversite(universite1);

        // Assertions
        Assertions.assertNotNull(addedUniversite);
        Assertions.assertEquals("University One", addedUniversite.getNomUniversite());

        // Verify interaction with the repository
        Mockito.verify(universiteRepository, Mockito.times(1)).save(universite1);
    }

    @Test
    public void testRemoveUniversite() {
        // Define behavior for repository deleteById
        Mockito.doNothing().when(universiteRepository).deleteById(1L);

        // Call the service method
        universiteService.removeUniversite(1L);

        // Verify interaction with the repository
        Mockito.verify(universiteRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void testModifyUniversite() {
        // Mock the repository's save and findById methods
        Mockito.when(universiteRepository.findById(1L)).thenReturn(Optional.of(universite1));
        Mockito.when(universiteRepository.save(Mockito.any(Universite.class))).thenReturn(universite1);

        // Modify the Universite instance
        universite1.setNomUniversite("Updated University One");

        // Call the service method
        Universite modifiedUniversite = universiteService.modifyUniversite(universite1);

        // Assertions
        Assertions.assertNotNull(modifiedUniversite);
        Assertions.assertEquals("Updated University One", modifiedUniversite.getNomUniversite());

        // Verify interaction with the repository
        Mockito.verify(universiteRepository, Mockito.times(1)).save(universite1);
    }
}
