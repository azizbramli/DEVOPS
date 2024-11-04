package tn.esprit.tpfoyer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChambreTest {

    private Chambre chambre;

    @BeforeEach
    void setUp() {
        chambre = new Chambre();
        chambre.setNumeroChambre(101);
        chambre.setTypeC(TypeChambre.SIMPLE); // assuming SIMPLE is a valid enum value
    }

    @Test
    void testGetIdChambre() {
        // Optionally, you can test the id field too if it's set
        // chambre.setIdChambre(1);
        // assertEquals(1, chambre.getIdChambre());
    }

    @Test
    void testGetNumeroChambre() {
        assertEquals(101, chambre.getNumeroChambre());
    }

    @Test
    void testGetTypeC() {
        assertEquals(TypeChambre.SIMPLE, chambre.getTypeC());
    }
}
