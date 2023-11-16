package tn.esprit.rh.achat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.rh.achat.entities.CategorieFournisseur;
import tn.esprit.rh.achat.entities.DetailFournisseur;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.DetailFournisseurRepository;
import tn.esprit.rh.achat.repositories.FournisseurRepository;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;
import tn.esprit.rh.achat.services.FournisseurServiceImpl;

@ContextConfiguration(classes = {FournisseurServiceImpl.class})
@ExtendWith(value = SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class FournisseurTest {

    @Mock
    private FournisseurRepository fournisseurRepository;

    @Mock
    private DetailFournisseurRepository detailFournisseurRepository;

    @Mock
    private ProduitRepository produitRepository;

    @Mock
    private SecteurActiviteRepository secteurActiviteRepository;

    @InjectMocks
    private FournisseurServiceImpl fournisseurService;

    private Fournisseur fournisseur;

    @Before
    public void setUp() {
        fournisseur = new Fournisseur();
        fournisseur.setIdFournisseur(1L);
        fournisseur.setCode("F123");
        fournisseur.setLibelle("Supplier1");
        fournisseur.setCategorieFournisseur(CategorieFournisseur.CONVENTIONNE);

        DetailFournisseur detailFournisseur = new DetailFournisseur();
        detailFournisseur.setIdDetailFournisseur(1L);
        detailFournisseur.setDateDebutCollaboration(new Date());
        fournisseur.setDetailFournisseur(detailFournisseur);

        List<SecteurActivite> secteurActivites = new ArrayList<>();
        SecteurActivite secteurActivite1 = new SecteurActivite();
        secteurActivite1.setIdSecteurActivite(1L);
        secteurActivites.add(secteurActivite1);
    }

    @Test
    public void testRetrieveAllFournisseurs() {
        when(fournisseurRepository.findAll()).thenReturn(List.of(fournisseur));

        List<Fournisseur> result = fournisseurService.retrieveAllFournisseurs();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Supplier1", result.get(0).getLibelle());
        // Add more assertions based on your specific implementation
    }

    @Test
    public void testAddFournisseur() {
        when(fournisseurRepository.save(any(Fournisseur.class))).thenReturn(fournisseur);

        Fournisseur result = fournisseurService.addFournisseur(fournisseur);

        assertNotNull(result);
        assertEquals("F123", result.getCode());
        // Add more assertions based on your specific implementation
    }

    @Test
    public void testUpdateFournisseur() {
        when(fournisseurRepository.save(any(Fournisseur.class))).thenReturn(fournisseur);

        Fournisseur result = fournisseurService.updateFournisseur(fournisseur);

        assertNotNull(result);
        assertEquals("F123", result.getCode());
        // Add more assertions based on your specific implementation
    }

    @Test
    public void testDeleteFournisseur() {
        fournisseurService.deleteFournisseur(1L);

        verify(fournisseurRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testRetrieveFournisseur() {
        when(fournisseurRepository.findById(1L)).thenReturn(Optional.of(fournisseur));

        Fournisseur result = fournisseurService.retrieveFournisseur(1L);

        assertNotNull(result);
        assertEquals("Supplier1", result.getLibelle());
        // Add more assertions based on your specific implementation
    }

}