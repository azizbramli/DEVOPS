package tn.esprit.tpfoyer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;
import tn.esprit.tpfoyer.service.ChambreServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChambreTest {

    @Mock
    private ChambreRepository chambreRepository;

    @InjectMocks
    private ChambreServiceImpl chambreService;

    private Chambre chambre;

    @BeforeEach
    void setUp() {
        chambre = new Chambre();
        chambre.setNumeroChambre(101);
        chambre.setTypeC(TypeChambre.SIMPLE); // assuming SIMPLE is a valid enum value
    }

    @Test
    void testRetrieveAllChambres() {
        // Given
        when(chambreRepository.findAll()).thenReturn(Arrays.asList(chambre));

        // When
        List<Chambre> chambres = chambreService.retrieveAllChambres();

        // Then
        assertNotNull(chambres);
        assertEquals(1, chambres.size());
        assertEquals(101, chambres.get(0).getNumeroChambre());
        assertEquals(TypeChambre.SIMPLE, chambres.get(0).getTypeC());
    }

    @Test
    void testRetrieveChambre() {
        // Given
        Long chambreId = 1L;
        when(chambreRepository.findById(chambreId)).thenReturn(Optional.of(chambre));

        // When
        Chambre retrievedChambre = chambreService.retrieveChambre(chambreId);

        // Then
        assertNotNull(retrievedChambre);
        assertEquals(101, retrievedChambre.getNumeroChambre());
        assertEquals(TypeChambre.SIMPLE, retrievedChambre.getTypeC());
    }

    @Test
    void testAddChambre() {
        // Given
        when(chambreRepository.save(chambre)).thenReturn(chambre);

        // When
        Chambre addedChambre = chambreService.addChambre(chambre);

        // Then
        assertNotNull(addedChambre);
        assertEquals(101, addedChambre.getNumeroChambre());
        assertEquals(TypeChambre.SIMPLE, addedChambre.getTypeC());
    }

    @Test
    void testModifyChambre() {
        // Given
        when(chambreRepository.save(chambre)).thenReturn(chambre);

        // When
        Chambre modifiedChambre = chambreService.modifyChambre(chambre);

        // Then
        assertNotNull(modifiedChambre);
        assertEquals(101, modifiedChambre.getNumeroChambre());
        assertEquals(TypeChambre.SIMPLE, modifiedChambre.getTypeC());
    }

    @Test
    void testRemoveChambre() {
        Long chambreId = 1L;

        // When
        chambreService.removeChambre(chambreId);

        // Then
        verify(chambreRepository, times(1)).deleteById(chambreId);
    }

    @Test
    void testRecupererChambresSelonTyp() {
        // Given
        TypeChambre typeChambre = TypeChambre.SIMPLE;
        when(chambreRepository.findAllByTypeC(typeChambre)).thenReturn(Arrays.asList(chambre));

        // When
        List<Chambre> chambres = chambreService.recupererChambresSelonTyp(typeChambre);

        // Then
        assertNotNull(chambres);
        assertEquals(1, chambres.size());
        assertEquals(101, chambres.get(0).getNumeroChambre());
        assertEquals(TypeChambre.SIMPLE, chambres.get(0).getTypeC());
    }

    @Test
    void testTrouverChambreSelonEtudiant() {
        // Given
        long cin = 12345L;
        when(chambreRepository.trouverChselonEt(cin)).thenReturn(chambre);

        // When
        Chambre retrievedChambre = chambreService.trouverchambreSelonEtudiant(cin);

        // Then
        assertNotNull(retrievedChambre);
        assertEquals(101, retrievedChambre.getNumeroChambre());
        assertEquals(TypeChambre.SIMPLE, retrievedChambre.getTypeC());
    }
}
