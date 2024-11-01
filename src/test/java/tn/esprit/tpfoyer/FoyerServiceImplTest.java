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
    void retrieveAllFoyers() {
        List<Foyer> foyers = new ArrayList<>();
        foyers.add(new Foyer());
        foyers.add(new Foyer());

        when(foyerRepository.findAll()).thenReturn(foyers);

        List<Foyer> result = foyerService.retrieveAllFoyers();

        assertEquals(2, result.size());
        verify(foyerRepository, times(1)).findAll();
    }

    @Test
    void retrieveFoyer() {
        Foyer foyer = new Foyer();
        foyer.setIdFoyer(1L);

        when(foyerRepository.findById(1L)).thenReturn(Optional.of(foyer));

        Foyer result = foyerService.retrieveFoyer(1L);

        assertNotNull(result);
        assertEquals(1L, result.getIdFoyer());
        verify(foyerRepository, times(1)).findById(1L);
    }

    @Test
    void addFoyer() {
        Foyer foyer = new Foyer();

        when(foyerRepository.save(foyer)).thenReturn(foyer);

        Foyer result = foyerService.addFoyer(foyer);

        assertNotNull(result);
        verify(foyerRepository, times(1)).save(foyer);
    }

    @Test
    void modifyFoyer() {
        Foyer foyer = new Foyer();
        foyer.setIdFoyer(1L);

        when(foyerRepository.save(foyer)).thenReturn(foyer);

        Foyer result = foyerService.modifyFoyer(foyer);

        assertNotNull(result);
        assertEquals(1L, result.getIdFoyer());
        verify(foyerRepository, times(1)).save(foyer);
    }

    @Test
    void removeFoyer() {
        Long foyerId = 1L;

        doNothing().when(foyerRepository).deleteById(foyerId);

        foyerService.removeFoyer(foyerId);

        verify(foyerRepository, times(1)).deleteById(foyerId);
    }


}
