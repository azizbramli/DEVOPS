package tn.esprit.tpfoyer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.repository.FoyerRepository;
import tn.esprit.tpfoyer.service.FoyerServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FoyerServiceImplTest {

    @Mock
    private FoyerRepository foyerRepository;

    @InjectMocks
    private FoyerServiceImpl foyerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllFoyers_Success() {
        // Arrange
        List<Foyer> foyers = List.of(new Foyer(), new Foyer());
        when(foyerRepository.findAll()).thenReturn(foyers);

        // Act
        List<Foyer> result = foyerService.retrieveAllFoyers();

        // Assert
        assertEquals(2, result.size(), "Expected to retrieve 2 foyers");
        verify(foyerRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveAllFoyers_EmptyList() {
        // Arrange
        when(foyerRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<Foyer> result = foyerService.retrieveAllFoyers();

        // Assert
        assertTrue(result.isEmpty(), "Expected an empty list of foyers");
        verify(foyerRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveFoyer_ExistingId() {
        // Arrange
        Foyer foyer = new Foyer();
        foyer.setIdFoyer(1L);
        when(foyerRepository.findById(1L)).thenReturn(Optional.of(foyer));

        // Act
        Foyer result = foyerService.retrieveFoyer(1L);

        // Assert
        assertNotNull(result, "Expected a non-null Foyer");
        assertEquals(1L, result.getIdFoyer(), "Expected foyer ID to match");
        verify(foyerRepository, times(1)).findById(1L);
    }

    @Test
    void testRetrieveFoyer_NonExistingId() {
        // Arrange
        when(foyerRepository.findById(99L)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> {
            foyerService.retrieveFoyer(99L);
        }, "Expected NoSuchElementException for non-existing ID");
        verify(foyerRepository, times(1)).findById(99L);
    }

    @Test
    void testAddFoyer_Success() {
        // Arrange
        Foyer foyer = new Foyer();
        when(foyerRepository.save(foyer)).thenReturn(foyer);

        // Act
        Foyer result = foyerService.addFoyer(foyer);

        // Assert
        assertNotNull(result, "Expected a non-null Foyer");
        verify(foyerRepository, times(1)).save(foyer);
    }

    @Test
    void testAddFoyer_NullFoyer() {
        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> {
            foyerService.addFoyer(null);
        }, "Expected IllegalArgumentException for null Foyer");
    }

    @Test
    void testModifyFoyer_ExistingFoyer() {
        // Arrange
        Foyer foyer = new Foyer();
        foyer.setIdFoyer(1L);
        when(foyerRepository.save(foyer)).thenReturn(foyer);

        // Act
        Foyer result = foyerService.modifyFoyer(foyer);

        // Assert
        assertNotNull(result, "Expected a non-null Foyer");
        assertEquals(1L, result.getIdFoyer(), "Expected ID to match");
        verify(foyerRepository, times(1)).save(foyer);
    }

    @Test
    void testModifyFoyer_NullFoyer() {
        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> {
            foyerService.modifyFoyer(null);
        }, "Expected IllegalArgumentException for null Foyer");
    }

    @Test
    void testRemoveFoyer_ExistingId() {
        // Arrange
        Long foyerId = 1L;
        doNothing().when(foyerRepository).deleteById(foyerId);

        // Act
        foyerService.removeFoyer(foyerId);

        // Assert
        verify(foyerRepository, times(1)).deleteById(foyerId);
    }

    @Test
    void testRemoveFoyer_NonExistingId() {
        // Arrange
        Long nonExistingId = 99L;
        doThrow(new IllegalArgumentException("ID not found")).when(foyerRepository).deleteById(nonExistingId);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> {
            foyerService.removeFoyer(nonExistingId);
        }, "Expected IllegalArgumentException for non-existing ID");
        verify(foyerRepository, times(1)).deleteById(nonExistingId);
    }
}
